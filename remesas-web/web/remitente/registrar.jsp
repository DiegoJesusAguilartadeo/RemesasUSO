<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Remitente</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plantillascss/registrarremitente.css">
</head>

<body>

<div class="contenedor">

<h2>Registrar Remitente</h2>

<form method="post" action="${pageContext.request.contextPath}/remitente/registrar">
  <label>Nombre:</label><input name="nombre" required>
  <label>Apellido:</label><input name="apellido" required>
  <label>País:</label><input name="pais" required>
  <label>Teléfono:</label><input name="telefono">
  <button type="submit">Guardar</button>
    <button type="button" class="btn-regresar" onclick="history.back()">Regresar</button>
</form>
  
<a class="btn-regresar" href="${pageContext.request.contextPath}/remitente/listar.jsp">Ver Lista</a>
</div>


</body>
</html>
