<%@ page import="java.util.*, dao.RemesaDAO, model.Remesa" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String tipo = request.getParameter("tipo"); // "envio" o "cobro"
    if (tipo == null || tipo.isEmpty()) {
        tipo = "cobro"; // por defecto reporte de caja (fechaCobro)
    }

    String f1 = request.getParameter("fechaInicio");
    String f2 = request.getParameter("fechaFin");

    List<Remesa> lista = new ArrayList<>();
    double totalMonto = 0.0;
    double totalFee = 0.0;
    double totalMontoTotal = 0.0;

    if (f1 != null && !f1.isEmpty()) {
        java.sql.Date d1 = java.sql.Date.valueOf(f1);
        java.sql.Date d2 = (f2 == null || f2.isEmpty())
                           ? d1
                           : java.sql.Date.valueOf(f2);

        RemesaDAO dao = new RemesaDAO();
        if ("envio".equals(tipo)) {
            lista = dao.listarPorRangoFechaEnvio(d1, d2);
        } else {
            lista = dao.listarPorRangoFechaCobro(d1, d2);
        }

        for (Remesa r : lista) {
            totalMonto      += r.getMonto();
            totalFee        += r.getFee();        // <-- sin null
            totalMontoTotal += r.getMontoTotal(); // <-- sin null
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Remesas por Fecha</title>

    <!-- Enlaza tu CSS general -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">

    <style>
        /* Ajustes suaves por si tu CSS no cubre esto */
        body {
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
            background-color: #f4f5fb;
        }
        .page-container {
            max-width: 1100px;
            margin: 30px auto;
            background: #ffffff;
            border-radius: 12px;
            padding: 20px 24px;
            box-shadow: 0 10px 25px rgba(15, 23, 42, 0.12);
        }
        .page-title {
            font-size: 1.4rem;
            font-weight: 600;
            margin-bottom: 10px;
        }
        .page-subtitle {
            font-size: 0.9rem;
            color: #6b7280;
            margin-bottom: 20px;
        }
        .filtros {
            display: flex;
            flex-wrap: wrap;
            gap: 12px;
            align-items: flex-end;
            margin-bottom: 18px;
        }
        .filtros label {
            font-size: 0.8rem;
            font-weight: 600;
            color: #374151;
        }
        .filtros input,
        .filtros select {
            padding: 6px 10px;
            border-radius: 6px;
            border: 1px solid #d1d5db;
            font-size: 0.9rem;
        }
        .btn-primary {
            padding: 7px 16px;
            border-radius: 999px;
            border: none;
            cursor: pointer;
            font-size: 0.9rem;
            font-weight: 600;
            background: linear-gradient(135deg, #2563eb, #1d4ed8);
            color: white;
        }
        .btn-primary:hover {
            opacity: 0.9;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 12px;
            font-size: 0.85rem;
        }
        th, td {
            padding: 8px 10px;
            border-bottom: 1px solid #e5e7eb;
            text-align: left;
        }
        th {
            background: #f3f4f6;
            font-weight: 600;
            font-size: 0.8rem;
            text-transform: uppercase;
            letter-spacing: 0.04em;
        }
        tr:nth-child(even) td {
            background: #fafafa;
        }
        .totales {
            margin-top: 14px;
            display: flex;
            gap: 18px;
            font-size: 0.9rem;
            font-weight: 600;
        }
        .tag {
            display: inline-flex;
            align-items: center;
            padding: 4px 10px;
            border-radius: 999px;
            background: #eff6ff;
            color: #1d4ed8;
            font-size: 0.75rem;
            font-weight: 600;
        }
        .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 14px;
        }
        .btn-back {
            font-size: 0.8rem;
            color: #4b5563;
            text-decoration: none;
        }
        .btn-back:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="page-container">
    <div class="toolbar">
        <div>
            <div class="page-title">Reporte de remesas por fecha</div>
            <div class="page-subtitle">
                Muestra las remesas por <strong><%= "envio".equals(tipo) ? "fecha de envío" : "fecha de cobro" %></strong>.
            </div>
        </div>
        <a class="btn-back" href="${pageContext.request.contextPath}/index.jsp">&#8592; Volver al menú</a>
    </div>

    <form method="get" class="filtros">
        <div>
            <label>Tipo de fecha</label><br>
            <select name="tipo">
                <option value="cobro" <%= "cobro".equals(tipo) ? "selected" : "" %>>Fecha de cobro (caja)</option>
                <option value="envio" <%= "envio".equals(tipo) ? "selected" : "" %>>Fecha de envío</option>
            </select>
        </div>

        <div>
            <label>Desde</label><br>
            <input type="date" name="fechaInicio" value="<%= (f1 != null ? f1 : "") %>">
        </div>

        <div>
            <label>Hasta</label><br>
            <input type="date" name="fechaFin" value="<%= (f2 != null ? f2 : "") %>">
        </div>

        <div>
            <button type="submit" class="btn-primary">Generar reporte</button>
        </div>

        <div style="margin-left:auto">
            <span class="tag">
                <%= lista.size() %> remesa(s)
            </span>
        </div>
    </form>

    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>ID</th>
            <th>Remitente</th>
            <th>Destinatario</th>
            <th>Monto</th>
            <th>Fee</th>
            <th>Total</th>
            <th>Fecha envío</th>
            <th>Fecha cobro</th>
            <th>Estado</th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Remesa r : lista) {
        %>
        <tr>
            <td><%= i++ %></td>
            <td><%= r.getIdRemesa() %></td>
            <td><%= r.getIdRemitente() %></td>
            <td><%= r.getIdDestinatario() %></td>
            <td>$ <%= String.format(Locale.US, "%.2f", r.getMonto()) %></td>
            <td>$ <%= String.format(Locale.US, "%.2f", r.getFee()) %></td>
            <td>$ <%= String.format(Locale.US, "%.2f", r.getMontoTotal()) %></td>
            <td><%= r.getFechaEnvio() != null ? r.getFechaEnvio() : "" %></td>
            <td><%= r.getFechaCobro() != null ? r.getFechaCobro() : "" %></td>
            <td><%= r.getEstado() %></td>
        </tr>
        <%
            }
            if (lista.isEmpty()) {
        %>
        <tr>
            <td colspan="10" style="text-align:center; color:#9ca3af; padding:14px;">
                No hay remesas en el rango seleccionado.
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="totales">
        <div>Total monto enviado: $ <%= String.format(Locale.US, "%.2f", totalMonto) %></div>
        <div>Total fee: $ <%= String.format(Locale.US, "%.2f", totalFee) %></div>
        <div>Total monto total: $ <%= String.format(Locale.US, "%.2f", totalMontoTotal) %></div>
    </div>
</div>

</body>
</html>
