<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String ctx = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%= ctx %>/plantillas/registrarremesas_styles.jsp">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Remesa</title>

</head>
<body>

<h2>Registrar Remesa</h2>

<form method="post" action="<%= ctx %>/remesa/registrar">

    <!-- REMITENTE -->
    <label>Buscar Remitente:</label>
    <input type="text" id="buscarRemitente">
    <button type="button" id="btnBuscarRemitente">Buscar</button>

    <label>Coincidencias:</label>
    <select id="comboRemitente" style="display:none;"></select>

    <input type="hidden" id="idRemitente" name="idRemitente">

    <br><br>

    <!-- DESTINATARIO -->
    <label>Buscar Destinatario:</label>
    <input type="text" id="buscarDestinatario">
    <button type="button" id="btnBuscarDestinatario">Buscar</button>

    <label>Coincidencias:</label>
    <select id="comboDestinatario" style="display:none;"></select>

    <input type="hidden" id="idDestinatario" name="idDestinatario">

    <br><br>

    <!-- DATOS DE REMESA -->
    <label>Monto:</label>
    <input type="number" name="monto" step="0.01" required>

    <label>Fecha Envío:</label>
    <input type="date" name="fechaEnvio" required>

    <label>Referencia:</label>
    <input type="text" name="referencia">

    <br><br>
    <button type="submit">Guardar Remesa</button>
</form>


<script>
// ---------------------- REMITENTE ---------------------------
function buscarRemitente() {
    const q = document.getElementById("buscarRemitente").value.trim();
    if(q === "") return alert("Ingrese un nombre");

    fetch("<%= ctx %>/api/remitente/buscar?q=" + encodeURIComponent(q))
    .then(r => r.json())
    .then(lista => {

        let combo = document.getElementById("comboRemitente");
        combo.innerHTML = "";

        if(lista.length === 0){
            combo.style.display = "none";
            return alert("No hay coincidencias");
        }

        lista.forEach(item => {
            let opt = document.createElement("option");
            opt.value = item.id;
            opt.textContent = item.nombre;
            combo.appendChild(opt);
        });

        combo.style.display = "inline-block";
        combo.selectedIndex = 0;
        document.getElementById("idRemitente").value = combo.value;
    });
}

// actualiza id cuando seleccionas
document.getElementById("comboRemitente").addEventListener("change", function(){
    document.getElementById("idRemitente").value = this.value;
});

// ---------------------- DESTINATARIO ------------------------
function buscarDestinatario() {
    const q = document.getElementById("buscarDestinatario").value.trim();
    if(q === "") return alert("Ingrese un nombre");

    fetch("<%= ctx %>/api/destinatario/buscar?q=" + encodeURIComponent(q))
    .then(r => r.json())
    .then(lista => {

        let combo = document.getElementById("comboDestinatario");
        combo.innerHTML = "";

        if(lista.length === 0){
            combo.style.display = "none";
            return alert("No hay coincidencias");
        }

        lista.forEach(item => {
            let opt = document.createElement("option");
            opt.value = item.id;
            opt.textContent = item.nombre;
            combo.appendChild(opt);
        });

        combo.style.display = "inline-block";
        combo.selectedIndex = 0;
        document.getElementById("idDestinatario").value = combo.value;
    });
}

// actualiza id cuando seleccionas
document.getElementById("comboDestinatario").addEventListener("change", function(){
    document.getElementById("idDestinatario").value = this.value;
});

// botones
document.getElementById("btnBuscarRemitente").onclick = buscarRemitente;
document.getElementById("btnBuscarDestinatario").onclick = buscarDestinatario;
</script>

</body>
</html>

