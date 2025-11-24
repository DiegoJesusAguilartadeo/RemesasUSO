<%-- 
    Document   : registro-estilos
    Created on : Nov 17, 2025, 9:13:14 AM
    Author     : USER
--%>

<%@ page contentType="text/html; charset=UTF-8" %>

<style>

    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: linear-gradient(135deg, #004e92, #000428);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .contenedor-registro {
        background: white;
        width: 420px;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0px 0px 15px rgba(0,0,0,0.3);
    }

    .titulo-registro {
        font-size: 22px;
        font-weight: bold;
        text-align: center;
        margin-bottom: 20px;
        color: #003366;
    }

    label {
        font-size: 14px;
        color: #333;
        font-weight: bold;
    }

    input {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        margin-bottom: 15px;
        border: 1px solid #aaa;
        border-radius: 6px;
        font-size: 14px;
    }

    input:focus {
        border-color: #004e92;
        outline: none;
    }

    button {
        width: 100%;
        padding: 12px;
        background: #004e92;
        color: white;
        font-size: 16px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: .3s;
    }

    button:hover {
        background: #003366;
    }

    .volver {
        text-align: center;
        margin-top: 15px;
    }

    .volver a {
        color: #004e92;
        text-decoration: none;
        font-size: 15px;
    }

    .volver a:hover {
        text-decoration: underline;
    }

</style>

