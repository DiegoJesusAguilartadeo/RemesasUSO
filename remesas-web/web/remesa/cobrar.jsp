<%-- 
    Document   : cobrar
    Created on : Nov 17, 2025, 1:49:44 AM
    Author     : jeusu
--%>
<%@page contentType="text/html; charset=UTF-8" %>
<%
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cobrar Remesa</title>

    <!-- ENLACE AL CSS -->
    <link rel="stylesheet" href="<%= ctx %>/plantillascss/cobrarremesa.css">
</head>
<body>

<div class="contenedor-cobro">

    <div class="titulo-cobro">Cobrar Remesa</div>

    <% if (request.getAttribute("error") != null) { %>
        <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>

    <form action="${pageContext.request.contextPath}/remesa/buscarCobro" method="get">
        <label>PIN:</label>
        <input type="text" name="pin" required />
        <button type="submit">Buscar</button>
    </form>

    <a href="<%= ctx %>/index.jsp" class="btn-regresar">⬅ Regresar</a>

</div>

</body>
</html>
