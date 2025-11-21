<%@ page import="java.util.*,dao.RemesaDAO,model.Remesa" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    List<Remesa> lista = new ArrayList<>();
    try { 
        lista = new RemesaDAO().listar(); 
    } catch(Exception e){ 
        out.print("Error: "+e.getMessage()); 
    }

    String pinGenerado = request.getParameter("pin");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Remesas</title>
</head>
<body>

<h2>Remesas</h2>

<p><a href="${pageContext.request.contextPath}/remesa/registrar.jsp">Nueva</a></p>

<% if (pinGenerado != null) { %>
    <div style="padding:10px; background:#d4edda; border:1px solid #c3e6cb; color:#155724;">
        ✔ Remesa creada correctamente. PIN: <b><%= pinGenerado %></b>
    </div>
<% } %>

<table border="1" cellpadding="6">
  <tr style="background:#f0f0f0;">
    <th>PIN</th>
    <th>Estado</th>
    <th>Monto</th>
    <th>Fee</th>
    <th>Total</th>
    <th>Fecha Envío</th>
    <th>Disponible</th>
    <th>Cobro</th>
    <th>Orden</th>
    <th>Recibo</th> <!-- ✅ NUEVA COLUMNA -->
  </tr>

  <% for (Remesa r : lista) { %>
    <tr>
      <td><%= r.getPin() %></td>
      <td><%= r.getEstado() %></td>
      <td><%= r.getMonto() %></td>
      <td><%= r.getFee() %></td>
      <td><%= r.getMontoTotal() %></td>
      <td><%= r.getFechaEnvio() %></td>
      <td><%= r.getFechaDisponible() %></td>
      <td><%= (r.getFechaCobro() != null ? r.getFechaCobro() : "-") %></td>
      <td><%= r.getNumeroOrden() %></td>
      <td>
        <a href="${pageContext.request.contextPath}/remesa/recibo.jsp?id=<%= r.getIdRemesa() %>">
            Ver recibo
        </a>
      </td>
    </tr>
  <% } %>

</table>

</body>
</html>
