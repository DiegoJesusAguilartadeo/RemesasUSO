<%@ page import="java.util.*,dao.DestinatarioDAO,model.Destinatario" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/plantillas/listar-estilos.jsp" %>

<%
  List<Destinatario> lista = new ArrayList<>();
  try { 
      lista = new DestinatarioDAO().listar(); 
  } catch(Exception e){ 
      out.print("Error: " + e.getMessage()); 
  }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Destinatarios</title>
</head>

<body>

<div class="container container-box">

    <h2 class="titulo-destinatarios">Listado de Destinatarios</h2>

    <a href="${pageContext.request.contextPath}/destinatario/registrar.jsp" 
       class="btn-nuevo-destinatario">
        <span class="icono">➕</span> Nuevo Destinatario
    </a>

    <table class="table table-striped table-bordered text-center">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Teléfono</th>
                <th>Dirección</th>
            </tr>
        </thead>

        <tbody>
        <% for (Destinatario d : lista) { %>
            <tr>
                <td><%= d.getIdDestinatario() %></td>
                <td><%= d.getNombre() %></td>
                <td><%= d.getApellido() %></td>
                <td><%= d.getTelefono() %></td>
                <td><%= d.getDireccion() %></td>
            </tr>
        <% } %>
        </tbody>
    </table>

</div>

</body>
</html>
