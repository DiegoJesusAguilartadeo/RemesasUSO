<%-- 
    Document   : procesandoCobro
    Created on : Nov 17, 2025, 8:24:16 PM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Procesando...</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.loader {
  border: 6px solid #eee;
  border-top: 6px solid #0d6efd;
  border-radius: 50%;
  width: 70px;
  height: 70px;
  animation: spin 1s linear infinite;
  margin: 40px auto;
}
@keyframes spin { to { transform: rotate(360deg); } }
</style>
</head>

<body class="bg-light">

<div class="container mt-5 text-center">
    <div class="card shadow p-4">
        <h3>Procesando cobro...</h3>
        <div class="loader"></div>
        <p class="text-muted">Espere un momento</p>
    </div>
</div>

<form id="autoForm" action="${pageContext.request.contextPath}/remesa/cobrar" method="post">
    <input type="hidden" name="pin" value="${param.pin}">
</form>

<script>
setTimeout(() => document.getElementById("autoForm").submit(), 1000);
</script>

</body>
</html>