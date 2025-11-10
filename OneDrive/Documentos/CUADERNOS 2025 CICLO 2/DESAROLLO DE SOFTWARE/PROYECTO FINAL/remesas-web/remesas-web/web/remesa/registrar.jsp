<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Nueva Remesa</title></head>
<body>
<h2>Registrar Remesa</h2>
<form method="post" action="${pageContext.request.contextPath}/remesa/registrar">
  <label>ID Remitente:</label><input name="idRemitente" type="number" min="1" required><br>
  <label>ID Destinatario:</label><input name="idDestinatario" type="number" min="1" required><br>
  <label>Monto:</label><input name="monto" type="number" step="0.01" min="0" required><br>
  <label>Fecha envío:</label><input name="fechaEnvio" type="date" required><br>
  <label>Referencia:</label><input name="referencia"><br>
  <button type="submit">Guardar</button>
</form>
<p><a href="${pageContext.request.contextPath}/remesa/listar.jsp">Ver lista</a></p>
</body></html>
