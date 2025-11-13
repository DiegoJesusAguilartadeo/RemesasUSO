<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Nuevo Destinatario</title></head>
<body>
<h2>Registrar Destinatario</h2>
<form method="post" action="${pageContext.request.contextPath}/destinatario/registrar">
  <label>Nombre:</label><input name="nombre" required><br>
  <label>Apellido:</label><input name="apellido" required><br>
  <label>Teléfono:</label><input name="telefono"><br>
  <label>Dirección:</label><input name="direccion"><br>
  <button type="submit">Guardar</button>
</form>
<p><a href="${pageContext.request.contextPath}/destinatario/listar.jsp">Ver lista</a></p>
</body></html>
