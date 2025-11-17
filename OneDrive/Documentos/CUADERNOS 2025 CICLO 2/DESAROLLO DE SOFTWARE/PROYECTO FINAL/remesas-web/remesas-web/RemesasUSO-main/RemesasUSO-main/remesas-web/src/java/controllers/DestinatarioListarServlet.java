/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author jeusu
 */
// ---------------------------------------------------------------
// Paquete al que pertenece esta clase.
// Debe coincidir con la carpeta física: src/java/controllers/
// ---------------------------------------------------------------


// ---------------------------------------------------------------
// IMPORTS: Clases que necesitamos para que este servlet funcione.
// ---------------------------------------------------------------

// Importa el DAO que contiene la lógica de acceso a la base de datos.
// DestinatarioDAO incluye métodos como listar(), insertar(), etc.
// Aquí lo usamos para obtener la lista desde SQL.
import dao.DestinatarioDAO;

// Importa la clase modelo Destinatario.
// Esta clase representa una fila de la tabla destinatarios en la BD.
import model.Destinatario;

// Importa las clases esenciales de Jakarta Servlet API.
// Nota: Si estuvieras usando una versión vieja de Java EE el paquete sería javax.servlet.*
// En tu proyecto moderno es jakarta.servlet.*

// Esta clase representa un error interno de los servlets.
import jakarta.servlet.ServletException;

// Permite mapear este servlet a una URL con @WebServlet.
import jakarta.servlet.annotation.WebServlet;

// Clase base de la cual heredan los servlets HTTP.
import jakarta.servlet.http.HttpServlet;

// Representa la petición HTTP que llega al servidor (parámetros, sesión, headers…).
import jakarta.servlet.http.HttpServletRequest;

// Representa la respuesta HTTP que enviaremos al navegador.
import jakarta.servlet.http.HttpServletResponse;

// Permite hacer un forward a un JSP (enviar los datos del request).
import jakarta.servlet.RequestDispatcher;

// Importa excepciones de entrada/salida.
// El método forward puede lanzar IOException.
import java.io.IOException;

// Importa List, que usaremos para almacenar múltiples destinatarios.
import java.util.List;

// Importa el logger estándar de Java para registrar errores.
import java.util.logging.Logger;

// Importa los niveles del logger (INFO, WARNING, SEVERE, etc.).
import java.util.logging.Level;


// ---------------------------------------------------------------
// Mapeo del servlet usando anotación.
// /destinatario/listar es la ruta que llamará el navegador.
// Ejemplo: http://localhost:8080/RemesasUSO/destinatario/listar
// ---------------------------------------------------------------
@WebServlet("/destinatario/listar")
public class DestinatarioListarServlet extends HttpServlet {

    // -----------------------------------------------------------
    // Logger para registrar eventos, advertencias y errores.
    // Se crea uno por clase.
    // getName() devuelve el nombre completo del servlet.
    // -----------------------------------------------------------
    private static final Logger LOGGER = Logger.getLogger(DestinatarioListarServlet.class.getName());


    // -----------------------------------------------------------
    // Método doGet: se ejecuta cuando el usuario entra a la URL
    // mediante un GET (click, escribir URL, redirección, etc.).
    //
    // request  = datos que llegan desde el navegador
    // response = datos que regresamos al navegador
    // -----------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lista donde guardaremos los datos que vienen del DAO.
        List<Destinatario> lista = null;

        try {
            // ---------------------------------------------------
            // 1) Instanciamos el DAO.
            // Este objeto sabe conectarse a la BD y ejecutar SQL.
            // ---------------------------------------------------
            DestinatarioDAO dao = new DestinatarioDAO();

            // ---------------------------------------------------
            // 2) Obtenemos la lista desde el método listar().
            // Este método hace SELECT * FROM Destinatarios;
            // y devuelve una List<Destinatario>.
            // ---------------------------------------------------
            lista = dao.listar();

        } catch (Exception ex) {
            // ---------------------------------------------------
            // 3) Si ocurre un error (BD caída, SQL mal escrito,
            //    conexión fallida, null pointer, etc.)
            //    lo registramos en los logs del servidor.
            // ---------------------------------------------------
            LOGGER.log(Level.SEVERE, "Error al obtener lista de destinatarios", ex);

            // ---------------------------------------------------
            // 4) Pasamos un mensaje al JSP para mostrar al usuario
            //    que ocurrió un problema (evita pantallas rojas).
            // ---------------------------------------------------
            request.setAttribute("error", "No se pudo cargar la lista.");
        }

        // -----------------------------------------------------------
        // Si ocurrió una excepción y 'lista' sigue en null,
        // enviamos una lista vacía para evitar NullPointerException
        // dentro del JSP.
        // -----------------------------------------------------------
        request.setAttribute("destinatarios",
                lista != null ? lista : java.util.Collections.emptyList());

        // -----------------------------------------------------------
        // 5) Obtenemos el JSP al que queremos enviar la información.
        // /destinatario/listar.jsp es donde está la tabla en HTML.
        // -----------------------------------------------------------
        RequestDispatcher rd = request.getRequestDispatcher("/destinatario/listar.jsp");

        // -----------------------------------------------------------
        // 6) Enviamos (forward) la request + response al JSP.
        // El JSP podrá acceder a los datos mediante:
        // request.getAttribute("destinatarios");
        //
        // Importante:
        // - El navegador NO cambia de URL (sigue siendo /listar)
        // - Es un reenvío interno del servidor, no un redirect.
        // -----------------------------------------------------------
        rd.forward(request, response);
    }
}
