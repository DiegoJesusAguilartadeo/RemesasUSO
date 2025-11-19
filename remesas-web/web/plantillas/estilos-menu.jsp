<%-- 
    Document   : estilos-menu
    Created on : Nov 16, 2025, 11:42:43 PM
    Author     : USER
--%>
<%@ page pageEncoding="UTF-8" %>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

<style>
    body {
        font-family: Arial, sans-serif;
        color: #fff;
        background: linear-gradient(120deg, #0b1f3a, #1e3c72);
        background-size: cover;
        background-attachment: fixed;
        margin: 0;
        padding: 30px;
    }

    h2 {
        text-align: center;
        margin-bottom: 30px;
    }

    .grid-menu {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
        gap: 25px;
        max-width: 900px;
        margin: 0 auto;
    }

    .card-menu {
        background: #ffffffee;
        color: #000;
        padding: 25px 10px;
        border-radius: 14px;
        text-decoration: none;
        box-shadow: 0 0 12px rgba(0, 0, 0, 0.15);
        transition: transform 0.2s, box-shadow 0.2s;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        font-weight: bold;
        font-size: 16px;
    }

    .card-menu:hover {
        transform: translateY(-6px);
        box-shadow: 0 0 18px rgba(0, 0, 0, 0.25);
    }

    .icon {
        font-size: 42px;
        margin-bottom: 12px;
        color: #1e3c72;
    }
</style>
