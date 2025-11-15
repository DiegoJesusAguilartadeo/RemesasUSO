<%-- 
    Document   : login
    Created on : Nov 13, 2025, 5:23:53 PM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar sesión - RemesasUSO</title>
</head>
<body>

<h2>Iniciar sesión</h2>

<%-- Mostrar errores enviados desde servlets (opcional) --%>
<%
    String error = request.getParameter("error");
    if (error != null) {
%>
    <p style="color:red;">
        <%
            switch (error) {
                case "google_no_code":
                    out.print("Error: Google no envió el código de autenticación.");
                    break;
                case "google_login_failed":
                    out.print("Error: Falló la autenticación con Google.");
                    break;
                case "not_authorized":
                    out.print("Tu correo no está autorizado para entrar al sistema.");
                    break;
                default:
                    out.print("Ocurrió un error desconocido.");
            }
        %>
    </p>
<%
    }
%>

<hr>

<a href="<%= request.getContextPath() %>/login/google">
    <button style="padding:12px 25px; font-size:16px; cursor:pointer;">
        Iniciar sesión con Google
    </button>
</a>


</body>
</html>