<%-- 
    Document   : comrpobante
    Created on : Nov 17, 2025, 1:59:06 AM
    Author     : jeusu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remesa Cobrada</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h3 class="text-success">¡Cobro Exitoso!</h3>

        <p><strong>PIN:</strong> ${pin}</p>
        <p>La remesa ha sido pagada correctamente.</p>

        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary w-100 mt-3">
            Volver al Inicio
        </a>
    </div>
</div>

</body>
</html>

