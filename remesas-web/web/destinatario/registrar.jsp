<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Destinatario</title>

    <!-- ENLACE AL CSS -->
    <link rel="stylesheet" href="<%= ctx %>/plantillascss/registrardestinatario.css">
</head>

<body>

<div class="contenedor-destinatario">
    <div class="titulo">Registrar Destinatario</div>

    <form method="post" action="${pageContext.request.contextPath}/destinatario/registrar">

        <label>Nombre:</label>
        <input name="nombre" required>

        <label>Apellido:</label>
        <input name="apellido" required>

        <label>Teléfono:</label>
        <input name="telefono">

        <label>Dirección:</label>
        <input name="direccion">

        <button type="submit">Guardar</button>
    </form>
    
    <button type="button" onclick="history.back()">Regresar</button>

    <div class="volver">
        <a href="${pageContext.request.contextPath}/destinatario/listar.jsp">Ver Lista</a>
    </div>
</div>

</body>
</html>
