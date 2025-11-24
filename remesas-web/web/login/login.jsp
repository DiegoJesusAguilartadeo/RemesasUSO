<%-- 
    Document   : login
    Created on : Nov 13, 2025, 5:23:53 PM
    Author     : jeusu
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/plantillas/Loginestilos.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Iniciar sesión - RemesasUSO</title>
</head>

<body>

<div class="contenedor">

    <div class="titulo">Iniciar sesión</div>
    <div class="subtexto">Usa tu cuenta de Google para iniciar sesión</div>

    <% 
        String error = request.getParameter("error");
        if (error != null) { 
    %>
        <div class="error">
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
        </div>
    <% 
        } 
    %>

    <a href="<%= request.getContextPath() %>/login/google" style="text-decoration:none;">
<<<<<<< HEAD
        <button class="boton-google">
            <img src="https://www.google.com/favicon.ico" 
                 style="width:20px; height:20px;" />
            Iniciar sesión con Google
        </button>
    </a>
=======
    <button class="boton-google">
        <img src="https://www.google.com/favicon.ico" 
             style="width:20px; height:20px;" />
        Iniciar sesión con Google
    </button>
</a>
>>>>>>> marlon-disenio

</div>

</body>
</html>
