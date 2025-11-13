<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Error</title></head>
<body>
<h3>Error</h3>
<p><%= request.getAttribute("error") %></p>
<p><a href="<%= request.getContextPath() %>/index.jsp">Volver</a></p>
</body></html>
