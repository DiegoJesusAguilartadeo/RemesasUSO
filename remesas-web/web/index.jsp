<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Remesas</title></head>
<body>
<h2>REMESAS USO</h2>
<ul>
  <li><a href="destinatario/registrar.jsp">Registrar Destinatario</a></li>
  <li><a href="destinatario/listar.jsp">Listar Destinatarios</a></li>
  <li><a href="remitente/registrar.jsp">Registrar Remitente</a></li>
  <li><a href="remitente/listar.jsp">Listar Remitentes</a></li>
  <li><a href="remesa/registrar.jsp">Registrar Remesa</a></li>
  <li><a href="remesa/listar.jsp">Listar Remesas</a></li>
  <li><a href="remesa/cobrar.jsp">cobrar</a></li>

</ul>
</body></html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/plantillas/estilos-menu.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remesas USO</title>
</head>

<body>


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
    
        <a class="card-menu" href="remesa/cobrar.jsp">
<i class="bi bi-wallet2 icon"></i>
        Cobrar Remesas
    </a>
    
    <a class="card-menu" href="remesa/cobrar.jsp">
    <i class="bi bi-door-closed icon"></i>
        Cerrar Sesion
    </a>
    
<a class="card-menu" href="${pageContext.request.contextPath}/remesa/remesas_registradas.jsp">
    <i class="bi bi-people icon"></i>
    Remesas Registradas
</a>

<a class="card-menu" href="${pageContext.request.contextPath}/remesa/remesas_cobradas.jsp">
    <i class="bi bi-person-check icon"></i>
    Remesas Cobradas
</a>




</div>

</body>
</html>
