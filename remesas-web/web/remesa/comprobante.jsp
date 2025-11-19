<%-- 
    Document   : comrpobante
    Created on : Nov 17, 2025, 1:59:06 AM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Remesa" %>
<%
    Remesa r = (Remesa) request.getAttribute("remesa");
%>

<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Comprobante</title></head>
<body>

<h2>Comprobante de Cobro</h2>

<table border="1" cellpadding="8">
    <tr><th>PIN</th><td><%= r.getPin() %></td></tr>
    <tr><th>Monto</th><td><%= r.getMonto() %></td></tr>
    <tr><th>Fee</th><td><%= r.getFee() %></td></tr>
    <tr><th>Total</th><td><%= r.getMontoTotal() %></td></tr>
    <tr><th>Fecha Cobro</th><td><%= java.time.LocalDate.now() %></td></tr>
    <tr><th>Estado</th><td>PAGADA</td></tr>
</table>

</body>
</html>
