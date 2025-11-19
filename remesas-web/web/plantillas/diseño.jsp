<%-- 
    Document   : diseño
    Created on : Nov 16, 2025, 11:05:50 PM
    Author     : USER
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style>
    /* Fondo general */
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;

        background: linear-gradient(135deg, #0f0f3d, #1b1b5e);
        min-height: 100vh;
        color: white;
        background-attachment: fixed;
    }

    /* Caja centrada */
    .contenedor {
        width: 90%;
        max-width: 900px;
        margin: 40px auto;
        padding: 30px;
        background: rgba(255,255,255,0.08);
        border-radius: 12px;
        box-shadow: 0 0 20px rgba(0,0,0,0.3);
        backdrop-filter: blur(6px);
          color: #000; /* ← ESTA LÍNEA SOLUCIONA EL PROBLEMA */
    }

    /* Títulos */
    h1, h2, h3 {
        text-align: center;
        margin-bottom: 20px;
        font-weight: 600;
        color: #ffffff;
    }

    /* Botones */
    .btn {
        display: inline-block;
        padding: 12px 18px;
        margin: 8px 0;
        background: #4d79ff;
        border-radius: 6px;
        text-decoration: none;
        color: white;
        font-size: 15px;
        transition: 0.2s;
    }

    .btn:hover {
        background: #3355cc;
    }

    /* Tablas */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 15px;
        background: rgba(255,255,255,0.1);
    }

    table th, table td {
        padding: 12px;
        border-bottom: 1px solid rgba(255,255,255,0.2);
        color: #fff;
        text-align: left;
    }

    table th {
        background: rgba(255,255,255,0.15);
        font-weight: bold;
    }

    table tr:hover {
        background: rgba(255,255,255,0.05);
    }

    /* Inputs */
    input, select {
        width: 100%;
        padding: 10px;
        margin: 6px 0 15px 0;
        border: none;
        border-radius: 6px;
    }

    button {
        padding: 12px 18px;
        border: none;
        border-radius: 6px;
        background: #4d79ff;
        color: white;
        font-size: 15px;
        cursor: pointer;
    }

    button:hover {
        background: #3355cc;
    }
</style>


