<%@ include file="/plantillas/remitente-estilos.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registrar Remitente</title>
</head>
<body>

<div class="contenedor-remitente">

    <h2></h2>

    <form method="post" action="${pageContext.request.contextPath}/remitente/registrar">

    <div class="form-container">

        <h2 style="text-align:center; margin-bottom: 15px;">Registrar Remitente</h2>

        <label>Nombre:</label>
        <input name="nombre" required>

        <label>Apellido:</label>
        <input name="apellido" required>

        <label>País:</label>
        <input name="pais" required>

        <label>Teléfono:</label>
        <input name="telefono">

        <button type="submit" class="btn-guardar">Guardar</button>

    </div>

</form>
    <a href="${pageContext.request.contextPath}/remitente/listar.jsp" class="btn-enlace">
        Ver lista de remitentes
    </a>

</div>

</body>
</html>

