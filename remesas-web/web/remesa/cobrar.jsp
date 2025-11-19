<%-- 
    Document   : cobrar
    Created on : Nov 17, 2025, 1:49:44 AM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cobrar Remesa</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h3>Cobrar Remesa</h3>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/remesa/buscar" method="get">
            <label class="form-label">Ingrese PIN:</label>
            <input type="text" class="form-control" name="pin" required>
            <button class="btn btn-primary w-100 mt-3">Buscar</button>
        </form>
    </div>
</div>

</body>
</html>

