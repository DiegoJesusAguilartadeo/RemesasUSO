<%@ page import="dao.RemesaDAO, model.Remesa" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/plantillas/estilos-menu.jsp" %>

<%
    String idStr = request.getParameter("id");
    Remesa r = null;
    String error = null;

    if (idStr != null && !idStr.isEmpty()) {
        try {
            int idRemesa = Integer.parseInt(idStr);
            RemesaDAO dao = new RemesaDAO();
            r = dao.obtenerPorIdConDetalles(idRemesa);
            if (r == null) {
                error = "No se encontró la remesa con ID " + idRemesa;
            }
        } catch (Exception ex) {
            error = "Error al obtener la remesa: " + ex.getMessage();
        }
    } else {
        error = "No se especificó el ID de la remesa.";
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recibo de remesa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">

    <style>
        body {
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
            background-color: #f3f4f6;
        }
        .recibo-container {
            max-width: 700px;
            margin: 30px auto;
            background: #ffffff;
            border-radius: 12px;
            padding: 24px 28px;
            box-shadow: 0 10px 25px rgba(15, 23, 42, 0.15);
            color: #000;
        }
        .recibo-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #e5e7eb;
            padding-bottom: 12px;
            margin-bottom: 16px;
        }
        .recibo-title {
            font-size: 1.4rem;
            font-weight: 700;
        }
        .recibo-subtitle {
            font-size: 0.85rem;
            color: #6b7280;
        }
        .recibo-section-title {
            font-size: 0.9rem;
            font-weight: 600;
            margin-top: 14px;
            margin-bottom: 6px;
            text-transform: uppercase;
            letter-spacing: 0.06em;
            color: #374151;
        }
        .recibo-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 6px 16px;
            font-size: 0.9rem;
        }
        .label {
            font-weight: 600;
            color: #4b5563;
        }
        .value {
            color: #111827;
        }
        .recibo-footer {
            border-top: 1px solid #e5e7eb;
            margin-top: 18px;
            padding-top: 10px;
            font-size: 0.8rem;
            color: #6b7280;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .btn-area {
            margin-top: 16px;
            display: flex;
            justify-content: flex-end;
            gap: 8px;
        }
        .btn-primary {
            padding: 7px 16px;
            border-radius: 999px;
            border: none;
            cursor: pointer;
            font-size: 0.9rem;
            font-weight: 600;
            background: linear-gradient(135deg, #2563eb, #1d4ed8);
            color: #ffffff;
        }
        .btn-secondary {
            padding: 7px 16px;
            border-radius: 999px;
            border: 1px solid #d1d5db;
            cursor: pointer;
            font-size: 0.9rem;
            font-weight: 500;
            background: #ffffff;
            color: #374151;
        }
        .btn-secondary a {
            text-decoration: none;
            color: inherit;
        }

        @media print {
            body {
                background: #ffffff;
            }
            .btn-area,
            .btn-secondary {
                display: none;
            }
            .recibo-container {
                box-shadow: none;
                margin: 0;
                border-radius: 0;
            }
        }
    </style>
</head>
<body>

<div class="recibo-container">
    <%
        if (error != null) {
    %>
        <p style="color:red;"><%= error %></p>
    <%
        } else if (r != null) {
    %>
        <div class="recibo-header">
            <div>
                <div class="recibo-title">Recibo de Remesa</div>
                <div class="recibo-subtitle">REMESAS USO - Comprobante de operación</div>
            </div>
            <div style="text-align:right; font-size:0.85rem; color:#6b7280;">
                <div><strong>ID Remesa:</strong> <%= r.getIdRemesa() %></div>
                <div><strong>N° Orden:</strong> <%= r.getNumeroOrden() != null ? r.getNumeroOrden() : "-" %></div>
            </div>
        </div>

        <div class="recibo-section-title">Datos de la operación</div>
        <div class="recibo-grid">
            <div class="label">Referencia:</div>
            <div class="value"><%= r.getReferencia() != null ? r.getReferencia() : "-" %></div>

            <div class="label">PIN:</div>
            <div class="value"><%= r.getPin() != null ? r.getPin() : "-" %></div>

            <div class="label">Estado:</div>
            <div class="value"><%= r.getEstado() %></div>

            <div class="label">Fecha de envío:</div>
            <div class="value"><%= r.getFechaEnvio() != null ? r.getFechaEnvio() : "-" %></div>

            <div class="label">Fecha disponible:</div>
            <div class="value"><%= r.getFechaDisponible() != null ? r.getFechaDisponible() : "-" %></div>

            <div class="label">Fecha de cobro:</div>
            <div class="value"><%= r.getFechaCobro() != null ? r.getFechaCobro() : "-" %></div>

            <div class="label">Método de cobro:</div>
            <div class="value"><%= r.getMetodoCobro() != null ? r.getMetodoCobro() : "-" %></div>
        </div>

        <div class="recibo-section-title">Montos</div>
        <div class="recibo-grid">
            <div class="label">Monto enviado:</div>
            <div class="value">$ <%= String.format(java.util.Locale.US, "%.2f", r.getMonto()) %></div>

            <div class="label">Fee:</div>
            <div class="value">$ <%= String.format(java.util.Locale.US, "%.2f", r.getFee()) %></div>

            <div class="label">Monto total:</div>
            <div class="value">$ <%= String.format(java.util.Locale.US, "%.2f", r.getMontoTotal()) %></div>
        </div>

        <div class="recibo-section-title">Personas</div>
        <div class="recibo-grid">
            <div class="label">Remitente:</div>
            <div class="value"><%= r.getNombreRemitente() != null ? r.getNombreRemitente() : ("ID " + r.getIdRemitente()) %></div>

            <div class="label">Destinatario:</div>
            <div class="value"><%= r.getNombreDestinatario() != null ? r.getNombreDestinatario() : ("ID " + r.getIdDestinatario()) %></div>
        </div>

        <div class="btn-area">
            <button type="button" class="btn-secondary">
                <a href="${pageContext.request.contextPath}/remesa/listar.jsp">Volver a listado</a>
            </button>
            <button type="button" class="btn-primary" onclick="window.print();">
                Imprimir recibo
            </button>
        </div>

        <div class="recibo-footer">
            <div>
                Este comprobante es generado por el sistema Remesas USO.
            </div>
            <div>
                Fecha de emisión: <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date()) %>
            </div>
        </div>
    <%
        }
    %>
</div>

</body>
</html>
