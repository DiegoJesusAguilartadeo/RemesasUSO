<%-- 
    Document   : Loginestilos
    Created on : Nov 17, 2025, 12:09:59 AM
    Author     : USER
--%>

<%@ page pageEncoding="UTF-8" %>

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

    .contenedor {
        background: white;
        width: 350px;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0px 0px 15px rgba(0,0,0,0.3);
        text-align: center;
        color: #000;
    }

    .titulo {
        font-size: 22px;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .subtexto {
        font-size: 14px;
        color: #555;
        margin-bottom: 20px;
    }

.boton-google {
    background: white;
    color: #444;
    border: 1px solid #ccc;
    padding: 12px 20px;
    font-size: 16px;
    border-radius: 8px;
    cursor: pointer;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;                /* Separación entre logo y texto */
    transition: 0.2s;
}

.boton-google:hover {
    background: #f7f7f7;
    border-color: #bbb;
}


    .error {
        color: red;
        margin-bottom: 15px;
        font-size: 14px;
        font-weight: bold;
    }
</style>
