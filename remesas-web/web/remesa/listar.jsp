<%@ page import="java.util.*,dao.RemesaDAO,model.Remesa" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  List<Remesa> lista = new ArrayList<>();
  try { lista = new RemesaDAO().listar(); } catch(Exception e){ out.print("Error: "+e.getMessage()); }
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Remesas</title></head>
<body>
<h2>Remesas</h2>
<p><a href="${pageContext.request.contextPath}/remesa/registrar.jsp">Nueva</a></p>
<table border="1" cellpadding="6">
  <tr><th>ID</th><th>ID Remitente</th><th>ID Destinatario</th><th>Monto</th><th>Fecha</th><th>Referencia</th></tr>
  <% for (Remesa r : lista) { %>
    <tr>
      <td><%= r.getIdRemesa() %></td>
      <td><%= r.getIdRemitente() %></td>
      <td><%= r.getIdDestinatario() %></td>
      <td><%= r.getMonto() %></td>
      <td><%= r.getFechaEnvio() %></td>
      <td><%= r.getReferencia() %></td>
    </tr>
  <% } %>
</table>
</body></html>
