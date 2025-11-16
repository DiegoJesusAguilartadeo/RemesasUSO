<%@ page import="java.util.*,dao.RemitenteDAO,model.Remitente" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  List<Remitente> lista = new ArrayList<>();
  try { lista = new RemitenteDAO().listar(); } catch(Exception e){ out.print("Error: "+e.getMessage()); }
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Remitentes</title></head>
<body>
<h2>Remitentes</h2>
<p><a href="${pageContext.request.contextPath}/remitente/registrar.jsp">Nuevo</a></p>
<table border="1" cellpadding="6">
  <tr><th>ID</th><th>Nombre</th><th>Apellido</th><th>País</th><th>Teléfono</th></tr>
  <% for (Remitente r : lista) { %>
    <tr>
      <td><%= r.getIdRemitente() %></td>
      <td><%= r.getNombre() %></td>
      <td><%= r.getApellido() %></td>
      <td><%= r.getPais() %></td>
      <td><%= r.getTelefono() %></td>
    </tr>
  <% } %>
</table>
</body></html>
