<%-- 
    Document   : cobrar
    Created on : Nov 17, 2025, 1:49:44 AM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Cobrar Remesa</title></head>
<body>

<h2>Buscar Remesa por PIN</h2>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="${pageContext.request.contextPath}/remesa/buscarCobro" method="get">
    <label>PIN:</label>
    <input type="text" name="pin" required />
    <button type="submit">Buscar</button>
</form>

</body>
</html>
