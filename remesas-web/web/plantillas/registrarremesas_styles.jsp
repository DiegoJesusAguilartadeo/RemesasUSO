<%-- 
    Document   : registrarremesas_styles
    Created on : Nov 19, 2025, 3:24:31 PM
    Author     : jeusu
--%>

<%@ page contentType="text/css; charset=UTF-8" %>

<%
   // Puedes cambiar la paleta acá si quieres
   String colorPrimario = "#2b4f81";
   String colorSecundario = "#1d3d63";
   String fondo = "#f6f7fb";
   String texto = "#333";
%>

/* ============ ESTILOS DEL SISTEMA DE REMESAS ============== */

body {
    font-family: "Segoe UI", Tahoma, sans-serif;
    background: <%= fondo %>;
    margin: 0;
    padding: 0;
    color: <%= texto %>;
}

.contenedor {
    width: 90%;
    max-width: 900px;
    margin: 30px auto;
    background: #fff;
    padding: 25px 35px;
    border-radius: 10px;
    box-shadow: 0 4px 15px rgba(0,0,0,0.08);
}

h1, h2, h3 {
    color: <%= colorPrimario %>;
    margin-bottom: 15px;
    font-weight: 600;
}

label {
    display: block;
    margin-top: 15px;
    font-weight: 600;
}

input, select, textarea {
    width: 100%;
    padding: 10px;
    margin-top: 6px;
    border: 1px solid #d0d4da;
    border-radius: 6px;
    font-size: 15px;
}

input:focus, textarea:focus, select:focus {
    border-color: <%= colorPrimario %>;
    outline: none;
}

button {
    background: <%= colorPrimario %>;
    color: #fff;
    padding: 10px 18px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    margin-top: 20px;
    font-size: 15px;
}

button:hover {
    background: <%= colorSecundario %>;
}

.btn-mini {
    padding: 6px 12px;
    font-size: 13px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

th {
    background: <%= colorPrimario %>;
    color: white;
    padding: 10px;
}

td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
    background: #f0f3f7;
}
