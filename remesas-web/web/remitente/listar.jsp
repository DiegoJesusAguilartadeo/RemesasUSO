<%@ page import="java.util.*,dao.RemitenteDAO,model.Remitente" %>
<<<<<<< HEAD
<%@ include file="/plantillas/remitente-lista-estilo.jsp" %>

<%
  List<Remitente> lista = new ArrayList<>();
  try {
    lista = new RemitenteDAO().listar();
  } catch(Exception e){
    out.print("Error: " + e.getMessage());
  }
=======
<%@ page contentType="text/html; charset=UTF-8" %>

<%
  List<Remitente> lista = new ArrayList<>();
  try { lista = new RemitenteDAO().listar(); } 
  catch(Exception e){ out.print("Error: "+e.getMessage()); }

  String modo = request.getParameter("modoSeleccion");
  String ctx = request.getContextPath();
>>>>>>> marlon-disenio
%>

<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
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
      <th>Pa�s</th>
      <th>Tel�fono</th>
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
=======
<meta charset="UTF-8">
<title>Remitentes</title>

<link rel="stylesheet" href="<%= ctx %>/plantillascss/listarremitente.css">

</head>
<body>

<div class="contenedor">

    <h2 class="titulo">Remitentes</h2>

    <div class="acciones">
        <a class="btn" href="<%= ctx %>/remitente/registrar.jsp">➕ Nuevo</a>
        <button class="btn-secondary" onclick="history.back()">⟵ Regresar</button>
    </div>

    <table class="tabla">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>País</th>
            <th>Teléfono</th>

            <% if ("remitente".equals(modo)) { %>
                <th>Seleccionar</th>
            <% } %>
        </tr>

        <% for (Remitente r : lista) { %>
        <tr>
            <td><%= r.getIdRemitente() %></td>
            <td><%= r.getNombre() %></td>
            <td><%= r.getApellido() %></td>
            <td><%= r.getPais() %></td>
            <td><%= r.getTelefono() %></td>

            <% if ("remitente".equals(modo)) { %>
                <td>
                    <a class="seleccionar"
                       href="<%= ctx %>/remesa/registrar.jsp?idRemitente=<%= r.getIdRemitente() %>&nombreRemitente=<%= r.getNombre() %> <%= r.getApellido() %>">
                       Seleccionar
                    </a>
                </td>
            <% } %>
        </tr>
        <% } %>
    </table>
>>>>>>> marlon-disenio

</div>

</body>
</html>
