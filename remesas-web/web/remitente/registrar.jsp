<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Nuevo Remitente</title></head>
<body>
<h2>Registrar Remitente</h2>
<form method="post" action="${pageContext.request.contextPath}/remitente/registrar">
  <label>Nombre:</label><input name="nombre" required><br>
  <label>Apellido:</label><input name="apellido" required><br>
  <label>País:</label><input name="pais" required><br>
  <label>Teléfono:</label><input name="telefono"><br>
  <button type="submit">Guardar</button>
</form>
<p><a href="${pageContext.request.contextPath}/remitente/listar.jsp">Ver lista</a></p>
</body></html>
