<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/plantillas/estilos-menu.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Remesas</title>
</head>

<body>

<h2>Sistema de Remesas</h2>

<div class="grid-menu">

    <a class="card-menu" href="destinatario/registrar.jsp">
        <i class="bi bi-person-plus icon"></i>
        Registrar Destinatario
    </a>

    <a class="card-menu" href="destinatario/listar.jsp">
        <i class="bi bi-people icon"></i>
        Listar Destinatarios
    </a>

    <a class="card-menu" href="remitente/registrar.jsp">
        <i class="bi bi-person-badge icon"></i>
        Registrar Remitente
    </a>

    <a class="card-menu" href="remitente/listar.jsp">
        <i class="bi bi-card-list icon"></i>
        Listar Remitentes
    </a>

    <a class="card-menu" href="remesa/registrar.jsp">
        <i class="bi bi-cash-coin icon"></i>
        Registrar Remesa
    </a>

    <a class="card-menu" href="remesa/listar.jsp">
        <i class="bi bi-folder2-open icon"></i>
        Listar Remesas
    </a>

    </a>
</div>

</body>
</html>
