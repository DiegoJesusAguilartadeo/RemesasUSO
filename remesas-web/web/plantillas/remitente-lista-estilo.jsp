<%-- 
    Document   : remitente-lista-estilo
    Created on : Nov 18, 2025, 12:20:58 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: linear-gradient(135deg, #004e92, #000428);
        min-height: 100vh;
    }

    h2 {
        text-align: center;
        color: white;
        margin-top: 30px;
        font-size: 28px;
    }

    .btn-nuevo {
        display: block;
        width: 180px;
        margin: 20px auto;
        padding: 12px;
        text-align: center;
        background: #28a745;
        color: white;
        font-weight: bold;
        text-decoration: none;
        border-radius: 8px;
        transition: 0.3s;
    }

    .btn-nuevo:hover {
        background: #1e7e34;
    }

    .tabla-contenedor {
        width: 90%;
        max-width: 900px;
        margin: 20px auto;
        background: white;
        padding: 20px;
        border-radius: 12px;
        box-shadow: 0px 0px 15px rgba(0,0,0,0.3);
    }

    table {
        width: 100%;
        border-collapse: collapse;
        text-align: left;
    }

    th {
        background: #004e92;
        color: white;
        padding: 10px;
        font-size: 16px;
    }

    td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }

    tr:hover td {
        background: #f0f0f0;
    }
</style>

