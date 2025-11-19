<%-- 
    Document   : confirmarCobro
    Created on : Nov 17, 2025, 1:53:59 AM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmar Cobro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">

        <h3>Confirmación de Cobro</h3>

        <p><strong>PIN:</strong> ${remesa.pin}</p>
        <p><strong>Monto Total:</strong> $${remesa.montoTotal}</p>
        <p><strong>ID Destinatario:</strong> ${remesa.idDestinatario}</p>

  <form action="procesando.jsp" method="post">
    <input type="hidden" name="pin" value="${remesa.pin}">
    <button type="submit" class="btn btn-success w-100 mt-3">COBRAR AHORA</button>
</form>


    </div>
</div>

</body>
</html>
