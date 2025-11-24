<%-- 
    Document   : remitente-estilos
    Created on : Nov 18, 2025, 11:42:50 AM
    Author     : USER
--%>

<%@ page contentType="text/html; charset=UTF-8" %>

<style>
    body {
        background: linear-gradient(135deg, #004e92, #000428);
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }
    
    .contenedor-campos {
    background: #f4f7fb;
    padding: 20px;
    border-radius: 10px;
    border: 1px solid #d9e3f0;
    margin-top: 10px;
}


    .contenedor-registro {
        background: white;
        width: 450px;
        margin: 40px auto;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0px 0px 15px rgba(0,0,0,0.3);
    }

    h2 {
        text-align: center;
        color: #003f7f;
        font-size: 26px;
        margin-bottom: 20px;
    }

    label {
        font-weight: bold;
        display: block;
        margin-top: 12px;
    }

    input {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border-radius: 8px;
        border: 1px solid #ccc;
        font-size: 15px;
    }

    .btn-guardar {
        background: #0066cc;
        color: white;
        width: 100%;
        border: none;
        padding: 12px;
        font-size: 16px;
        border-radius: 8px;
        cursor: pointer;
        margin-top: 20px;
        transition: 0.3s;
    }

    .btn-guardar:hover {
        background: #004999;
    }

     .btn-enlace {
        display: block;
        margin-top: 20px;
        text-align: center;
        font-weight: bold;
        color: white;
    }
    
    .form-container {
    background: #ffffff;
    width: 350px;
    padding: 25px;
    margin: 30px auto;
    border-radius: 12px;
    box-shadow: 0px 0px 15px rgba(0,0,0,0.3);
}

.form-container label {
    font-weight: bold;
    margin-top: 10px;
    display: block;
}

.form-container input {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border-radius: 6px;
    border: 1px solid #ccc;
    outline: none;
}

.form-container input:focus {
    border-color: #007bff;
}

.btn-guardar {
    background: #007bff;
    color: white;
    border: none;
    padding: 12px 20px;
    font-size: 16px;
    border-radius: 8px;
    cursor: pointer;
    width: 100%;
    margin-top: 15px;
    transition: 0.3s;
}

.btn-guardar:hover {
    background: #0056b3;
}

</style>