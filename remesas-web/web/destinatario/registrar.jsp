<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/plantillas/registro-estilos.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Destinatario</title>
</head>
<body>

<div class="contenedor-registro">

    <div class="titulo-registro">Registrar Destinatario</div>

    <form method="post" action="${pageContext.request.contextPath}/destinatario/registrar">

        <label>Nombre:</label>
        <input type="text" name="nombre" required>

        <label>Apellido:</label>
        <input type="text" name="apellido" required>

        <label>Teléfono:</label>
        <input type="text" name="telefono">

        <label>Dirección:</label>
        <input type="text" name="direccion">

        <button type="submit">Guardar</button>
    </form>

    <div class="volver">
        <a href="${pageContext.request.contextPath}/destinatario/listar.jsp">Ver lista</a>
    </div>

</div>

</body>
</html>
des