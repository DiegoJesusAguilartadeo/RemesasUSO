<%@ page import="java.util.*,dao.RemitenteDAO,model.Remitente" %>
<%@ include file="/plantillas/remitente-lista-estilo.jsp" %>


<%
  List<Remitente> lista = new ArrayList<>();
  try {
    lista = new RemitenteDAO().listar();
  } catch(Exception e){
    out.print("Error: " + e.getMessage());
  }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Remitentes</title>
  <link rel="stylesheet" type="text/css" href="../plantillas/remitente-lista-estilos.jsp">
</head>

<body>

<div class="container">

  <h2>Listado de Remitentes</h2>

  <p>
    <a class="boton-nuevo" href="${pageContext.request.contextPath}/remitente/registrar.jsp">+ Nuevo Remitente</a>
  </p>

  <table>
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Apellido</th>
      <th>País</th>
      <th>Teléfono</th>
    </tr>

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

</div>

</body>
</html>
