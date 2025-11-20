/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.EmpleadoDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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

    private static void disableSslVerification() {
        try {
            TrustManager[] trustAll = new TrustManager[]{ new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string) {}
                public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string) {}
            }};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAll, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        disableSslVerification();

        String code = request.getParameter("code");
        if (code == null) {
            response.sendRedirect("login/login.jsp?error=google_no_code");
            return;
        }

        try {
            // intercambiar code por tokens
            URL url = new URL("https://oauth2.googleapis.com/token");
            String params = "code=" + code
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

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {

                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                if (!json.has("id_token")) {
                    response.sendRedirect("login/login.jsp?error=google_login_failed");
                    return;
                }

                String idToken = json.get("id_token").getAsString();

                // decodificar id_token
                String[] parts = idToken.split("\\.");
                String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
                JsonObject payload = JsonParser.parseString(payloadJson).getAsJsonObject();

                String email = payload.get("email").getAsString();
                String nombre = payload.has("name") ? payload.get("name").getAsString() : email;
                String foto = payload.has("picture") ? payload.get("picture").getAsString() : null;

                // VALIDAR en la base de datos: obtener idEmpleado por email
                EmpleadoDAO edao = new EmpleadoDAO();
                Integer idEmpleado = edao.obtenerIdPorEmail(email);

                if (idEmpleado == null) {
                    // Si deseas crear automáticamente el empleado, usa:
                    // idEmpleado = edao.crearSiNoExisteYObtenerId(nombre, email);
                    // Por ahora, rechazamos el acceso si no existe:
                    response.sendRedirect("login/login.jsp?error=not_authorized");
                    return;
                }

                // crear sesión y guardar email + idEmpleado
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario_email", email);
                session.setAttribute("usuario_nombre", nombre);
                session.setAttribute("usuario_foto", foto);
                session.setAttribute("usuario_idEmpleado", idEmpleado);

                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login/login.jsp?error=google_login_failed");
        }
    }
}
