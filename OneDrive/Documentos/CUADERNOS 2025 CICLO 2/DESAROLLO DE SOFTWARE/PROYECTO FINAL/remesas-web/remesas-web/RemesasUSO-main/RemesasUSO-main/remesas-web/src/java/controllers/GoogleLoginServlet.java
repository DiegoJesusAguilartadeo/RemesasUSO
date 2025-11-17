/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author jeusu
 */

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import utils.Config;


@WebServlet("/login/google")
public class GoogleLoginServlet extends HttpServlet {

    // ⚠ Usa tus propios valores del Google Cloud Console
private static final String CLIENT_ID = Config.get("GOOGLE_CLIENT_ID");
private static final String REDIRECT_URI = Config.get("GOOGLE_REDIRECT_URI");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String scope = URLEncoder.encode("openid email profile", "UTF-8");

        String url = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=" + scope
                + "&access_type=offline"
                + "&prompt=consent";

        response.sendRedirect(url);
    }
}