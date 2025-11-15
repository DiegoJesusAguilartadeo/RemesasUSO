/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

/**
 *
 * @author jeusu
 */
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();

        // ------------------------------------------------------
        // 1. Rutas públicas que NO requieren autenticación
        // ------------------------------------------------------
        boolean publicPath =
                path.contains("login") ||          // /login/login.jsp
                path.contains("login-google") ||   // /login-google
                path.contains("oauth2callback") || // /oauth2callback
                path.endsWith(".css") ||           // recursos
                path.endsWith(".js");              // recursos

        // ------------------------------------------------------
        // 2. Si NO es ruta pública → verificar si existe sesión
        // ------------------------------------------------------
        if (!publicPath) {

            HttpSession sesion = request.getSession(false);

            // OJO: el callback guarda usuario_email, no "email"
            if (sesion == null || sesion.getAttribute("usuario_email") == null) {
                response.sendRedirect(request.getContextPath() + "/login/login.jsp");
                return;
            }
        }

        // ------------------------------------------------------
        // 3. Continuar con el procesamiento normal
        // ------------------------------------------------------
        chain.doFilter(req, res);
    }
}