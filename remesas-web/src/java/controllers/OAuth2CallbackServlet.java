/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author jeusu
 */
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import utils.Config;

@WebServlet("/oauth2callback")
public class OAuth2CallbackServlet extends HttpServlet {

private static final String CLIENT_ID = Config.get("GOOGLE_CLIENT_ID");
private static final String CLIENT_SECRET = Config.get("GOOGLE_CLIENT_SECRET");
private static final String REDIRECT_URI = Config.get("GOOGLE_REDIRECT_URI");

    // ⛔ SOLO PARA DESARROLLO — IGNORA SSL EN LOCALHOST
    private static void disableSslVerification() {
        try {
            TrustManager[] trustAll = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string) {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string) {
                    }
                }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAll, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        disableSslVerification(); // ← FIX SSL PARA LOCALHOST

        String code = request.getParameter("code");

        if (code == null) {
            response.sendRedirect("login/login.jsp?error=google_no_code");
            return;
        }

        // ================================
        // 1) Intercambiar "code" por tokens
        // ================================
        URL url = new URL("https://oauth2.googleapis.com/token");

        String params
                = "code=" + code
                + "&client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET
                + "&redirect_uri=" + REDIRECT_URI
                + "&grant_type=authorization_code";

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(params.getBytes(StandardCharsets.UTF_8));
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
        );

        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

        if (!json.has("id_token")) {
            response.sendRedirect("login/login.jsp?error=google_login_failed");
            return;
        }

        String idToken = json.get("id_token").getAsString();

        // ================================
        // 2) Decodificar ID TOKEN (JWT)
        // ================================
        String[] parts = idToken.split("\\.");
        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
        JsonObject payload = JsonParser.parseString(payloadJson).getAsJsonObject();

        String email = payload.get("email").getAsString();
        String nombre = payload.get("name").getAsString();
        String foto = payload.get("picture").getAsString();

        // ================================
        // 3) Validar whitelist
        // ================================
        String[] permitidos = {
            "jeususlanz@gmail.com",
            "ariastepas@gmail.com",
            "sopaslocas25@gmail.com"
        };

        boolean autorizado = false;
        for (String permitido : permitidos) {
            if (email.equalsIgnoreCase(permitido)) {
                autorizado = true;
                break;
            }
        }

        if (!autorizado) {
            response.sendRedirect("login/login.jsp?error=not_authorized");
            return;
        }

        // ================================
        // 4) Crear sesión
        // ================================
        HttpSession session = request.getSession(true);
        session.setAttribute("usuario_email", email);
        session.setAttribute("usuario_nombre", nombre);
        session.setAttribute("usuario_foto", foto);

        // ================================
        // 5) Redirigir al home
        // ================================
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
