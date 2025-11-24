<%-- 
    Document   : listar-estilos
    Created on : Nov 18, 2025, 11:17:38 AM
    Author     : USER
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<style>

    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: linear-gradient(135deg, #004e92, #000428);
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        padding-top: 40px;
    }
    
    .titulo-destinatarios {
    color: white;
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 20px;
    text-align: center;
}


    .contenedor-listado {
        width: 85%;
        max-width: 900px;
        background: white;
        padding: 25px;
        border-radius: 12px;
        box-shadow: 0 0 18px rgba(0,0,0,0.25);
        animation: aparecer 0.5s ease-in-out;
    }

    @keyframes aparecer {
        from { opacity: 0; transform: translateY(20px); }
        to   { opacity: 1; transform: translateY(0); }
    }

    .titulo-listado {
        font-size: 26px;
        font-weight: bold;
        margin-bottom: 20px;
        text-align: center;
        color: #003d73;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 15px;
    }

    th {
        background: #003d73;
        color: white;
        padding: 12px;
        text-align: left;
        font-size: 15px;
    }

    td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
        font-size: 14px;
    }

    tr:hover {
        background: #f1f1f1;
        transition: 0.3s;
    }

    .btn-nuevo-destinatario {
    display: inline-flex;
    align-items: center;
    gap: 10px;
    background: linear-gradient(135deg, #007bff, #0056d2);
    color: white;
    padding: 14px 26px;
    font-size: 18px;
    font-weight: bold;
    border-radius: 10px;
    text-decoration: none;
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.25);
    transition: all 0.25s ease;
    cursor: pointer;
}

.btn-nuevo-destinatario:hover {
    transform: translateY(-4px) scale(1.03);
    background: linear-gradient(135deg, #268dff, #0047b2);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
}

.btn-nuevo-destinatario:active {
    transform: scale(0.98);
}

.btn-nuevo-destinatario .icono {
    font-size: 22px;
    transition: 0.3s;
}

.btn-nuevo-destinatario:hover .icono {
    transform: rotate(20deg) scale(1.1);
}

</style>
