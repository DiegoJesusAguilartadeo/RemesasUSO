<%@ page import="java.util.*,dao.DestinatarioDAO,model.Destinatario" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
  List<Destinatario> lista = new ArrayList<>();
  try { lista = new DestinatarioDAO().listar(); } 
  catch(Exception e){ out.print("Error: "+e.getMessage()); }

  String modo = request.getParameter("modoSeleccion");
  String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Destinatarios</title>

<link rel="stylesheet" href="<%= ctx %>/plantillascss/listardestinatario.css">
</head>
<body>

<div class="contenedor">

<h2>Lista de Destinatarios</h2>

<!-- Botones -->
<a href="<%= ctx %>/destinatario/registrar.jsp" class="btn">➕ Nuevo</a>
<a href="<%= ctx %>/" class="btn btn-regresar">⬅ Regresar</a>

<table>
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Teléfono</th>
    <th>Dirección</th>

    <% if ("destinatario".equals(modo)) { %>
        <th>Seleccionar</th>
    <% } %>
  </tr>

  <% for (Destinatario d : lista) { %>
    <tr>
      <td><%= d.getIdDestinatario() %></td>
      <td><%= d.getNombre() %></td>
      <td><%= d.getApellido() %></td>
      <td><%= d.getTelefono() %></td>
      <td><%= d.getDireccion() %></td>

      <% if ("destinatario".equals(modo)) { %>
        <td>
          <a class="seleccionar" 
             href="<%= ctx %>/remesa/registrar.jsp?idDestinatario=<%= d.getIdDestinatario() %>&nombreDestinatario=<%= d.getNombre() %> <%= d.getApellido() %>">
            Seleccionar
          </a>
        </td>
      <% } %>
    </tr>
  <% } %>

</table>

</div>

</body>
</html>
