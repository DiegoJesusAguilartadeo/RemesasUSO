<%-- 
    Document   : confirmarCobro
    Created on : Nov 17, 2025, 1:53:59 AM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Remesa" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Confirmar Cobro</title></head>
<body>

<h2>Confirmación de Cobro</h2>

<p><strong>PIN:</strong> ${remesa.pin}</p>
<p><strong>Monto Total:</strong> ${remesa.montoTotal}</p>
<p><strong>ID Destinatario:</strong> ${remesa.idDestinatario}</p>
<p><strong>Estado:</strong> ${remesa.estado}</p>

<form action="../remesa/cobrar" method="post">
    <input type="hidden" name="pin" value="${remesa.pin}" />
    <button type="submit">COBRAR AHORA</button>
</form>

</body>
</html>