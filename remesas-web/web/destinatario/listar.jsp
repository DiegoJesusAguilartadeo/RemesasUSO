<%@ page import="java.util.*,dao.DestinatarioDAO,model.Destinatario" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  List<Destinatario> lista = new ArrayList<>();
  try { lista = new DestinatarioDAO().listar(); } catch(Exception e){ out.print("Error: "+e.getMessage()); }
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Destinatarios</title></head>
<body>
<h2>Destinatarios</h2>
<p><a href="${pageContext.request.contextPath}/destinatario/registrar.jsp">Nuevo</a></p>
<table border="1" cellpadding="6">
  <tr><th>ID</th><th>Nombre</th><th>Apellido</th><th>Teléfono</th><th>Dirección</th></tr>
  <% for (Destinatario d : lista) { %>
    <tr>
      <td><%= d.getIdDestinatario() %></td>
      <td><%= d.getNombre() %></td>
      <td><%= d.getApellido() %></td>
      <td><%= d.getTelefono() %></td>
      <td><%= d.getDireccion() %></td>
    </tr>
  <% } %>
</table>
</body></html>
