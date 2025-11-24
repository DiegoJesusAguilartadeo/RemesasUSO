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
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Remesas</title>

    <!-- Enlace al CSS -->
    <link rel="stylesheet" href="<%= ctx %>/plantillascss/listarremesas.css">
</head>

<body>

    <div class="contenedor">
        <h2 class="titulo">Listado de Remesas</h2>

        <button class="btn-nuevo" onclick="window.location.href='<%= ctx %>/remesa/registrar.jsp'">
            + Nueva Remesa
        </button>

        <% if (pinGenerado != null) { %>
            <div class="alerta-exito">
                ✔ Remesa creada correctamente. PIN:
                <b><%= pinGenerado %></b>
            </div>
        <% } %>

        <table class="tabla">
            <tr>
                <th>PIN</th>
                <th>Estado</th>
                <th>Monto</th>
                <th>Fee</th>
                <th>Total</th>
                <th>Fecha Envío</th>
                <th>Disponible</th>
                <th>Cobro</th>
                <th>Orden</th>
                <th>Recibo</th>
            </tr>

            <% for (Remesa r : lista) { %>
                <tr>
                    <td><%= r.getPin() %></td>
                    <td><%= r.getEstado() %></td>
                    <td>$<%= r.getMonto() %></td>
                    <td>$<%= r.getFee() %></td>
                    <td>$<%= r.getMontoTotal() %></td>
                    <td><%= r.getFechaEnvio() %></td>
                    <td><%= r.getFechaDisponible() %></td>
                    <td><%= (r.getFechaCobro() != null ? r.getFechaCobro() : "-") %></td>
                    <td><%= r.getNumeroOrden() %></td>
                    <td>
                        <a class="link" href="<%= ctx %>/remesa/recibo.jsp?id=<%= r.getIdRemesa() %>">
                            Ver recibo
                        </a>
                    </td>
                </tr>
            <% } %>
        </table>

        <button class="btn-regresar" onclick="history.back()">⟵ Regresar</button>

    </div>

</body>
</html>
