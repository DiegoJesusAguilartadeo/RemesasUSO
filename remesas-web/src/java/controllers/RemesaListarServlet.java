/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author jeusu
 */

// API de servlets (Jakarta EE 9+, que tú estás usando).
import dao.RemesaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

// Utilidades de Java (I/O y colecciones).
import java.io.IOException;
import java.util.List;

// Logger para registrar errores en el servidor.
import java.util.logging.Logger;
import java.util.logging.Level;
import model.Remesa;


// ---------------------------------------------------------------
// @WebServlet mapea este servlet a la ruta /remesa/listar
//
// Cuando el usuario abra: 
//   http://localhost:8080/RemesasUSO/remesa/listar
// este servlet se ejecutará.
// ---------------------------------------------------------------
@WebServlet("/remesa/listar")
public class RemesaListarServlet extends HttpServlet {

    // -----------------------------------------------------------
    // Logger para registrar información y errores en consola.
    // getName() usa el nombre completo de la clase.
    // -----------------------------------------------------------
    private static final Logger LOGGER =
            Logger.getLogger(RemesaListarServlet.class.getName());


    // -----------------------------------------------------------
    // doGet() → se ejecuta cuando el usuario visita la URL
    // usando el método GET (lo normal al entrar a una página).
    //
    // request  -> datos que llegan desde el navegador
    // response -> lo que enviamos de regreso al navegador
    // -----------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lista donde almacenaremos las remesas traídas del DAO.
        List<Remesa> lista = null;

        try {
            // ---------------------------------------------------
            // Crear instancia del DAO (acceso a BD).
            // El DAO se encarga de ejecutar SELECT * FROM Remesa...
            // ---------------------------------------------------
            RemesaDAO dao = new RemesaDAO();

            // Obtenemos la lista completa de remesas.
            lista = dao.listar();

        } catch (Exception ex) {
            // ---------------------------------------------------
            // Si algo falla al obtener datos:
            //   - Se registra el error en los logs del servidor.
            //   - Se envía un mensaje al JSP para mostrarlo.
            // ---------------------------------------------------
            LOGGER.log(Level.SEVERE, "Error al obtener lista de remesas", ex);
            request.setAttribute("error", "No se pudo cargar la lista de remesas.");
        }

        // -----------------------------------------------------------
        // Si lista quedó en null (por excepción), enviamos lista vacía
        // para evitar NullPointerException en el JSP.
        // -----------------------------------------------------------
        request.setAttribute("remesas",
                lista != null ? lista : java.util.Collections.emptyList());

        // -----------------------------------------------------------
        // El JSP que mostrará la tabla se encuentra en:
        //   web/remesa/listar.jsp
        //
        // RequestDispatcher permite reenviar la request y response
        // al JSP (server-side). No cambia la URL del navegador.
        // -----------------------------------------------------------
        RequestDispatcher rd =
                request.getRequestDispatcher("/remesa/listar.jsp");

        // -----------------------------------------------------------
        // Enviamos control al JSP.
        // El JSP recibirá "remesas" y (si hubo error) "error".
        // -----------------------------------------------------------
        rd.forward(request, response);
    }
}