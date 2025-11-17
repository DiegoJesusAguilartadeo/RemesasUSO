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
// IMPORTS NECESARIOS
// ---------------------------------------------------------------

// DAO que obtiene los remitentes desde la base de datos
import dao.RemitenteDAO;

// Modelo que representa una fila de la tabla Remitente
import model.Remitente;

// API de Servlets (versión Jakarta en tu proyecto)
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

// Utilidades Java
import java.io.IOException;
import java.util.List;

// Logger clásico de Java para registrar errores
import java.util.logging.Logger;
import java.util.logging.Level;


// ---------------------------------------------------------------
// Mapeo del servlet a la URL /remitente/listar
// Cuando el usuario entre a esa ruta, se ejecutará este servlet.
// ---------------------------------------------------------------
@WebServlet("/remitente/listar")
public class RemitenteListarServlet extends HttpServlet {

    // Logger para registrar información y errores
    private static final Logger LOGGER =
            Logger.getLogger(RemitenteListarServlet.class.getName());


    // ---------------------------------------------------------------
    // Método doGet: se ejecuta cuando el usuario visita la URL
    // /remitente/listar (HTTP GET).
    // ---------------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Remitente> lista = null; // lista donde guardaremos los remitentes

        try {
            // Instanciamos el DAO
            RemitenteDAO dao = new RemitenteDAO();

            // Obtenemos los datos desde la BD
            lista = dao.listar();

        } catch (Exception ex) {
            // Registramos el error en los logs del servidor
            LOGGER.log(Level.SEVERE, "Error al obtener lista de remitentes", ex);

            // Enviamos un mensaje al JSP para mostrarlo en pantalla
            request.setAttribute("error", "No se pudo cargar la lista de remitentes.");
        }

        // Si lista es null (error), enviamos una lista vacía
        request.setAttribute("remitentes",
                lista != null ? lista : java.util.Collections.emptyList());

        // Enviamos la request al JSP remitente/listar.jsp
        RequestDispatcher rd =
                request.getRequestDispatcher("/remitente/listar.jsp");

        rd.forward(request, response); // Devolvemos el HTML generado por el JSP
    }
}