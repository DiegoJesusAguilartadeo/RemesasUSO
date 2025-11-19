/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author jeusu
 */
import dao.RemesaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Remesa;

@WebServlet("/remesa/buscarCobro")
public class RemesaBuscarCobroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pin = req.getParameter("pin");

        try {
            RemesaDAO dao = new RemesaDAO();
            Remesa r = dao.buscarPorPin(pin);

            if (r == null) {
                req.setAttribute("error", "No existe una remesa con ese PIN.");
                req.getRequestDispatcher("/remesa/cobrar.jsp").forward(req, resp);
                return;
            }

            // ⭐⭐⭐ PASO NUEVO: Verificar si ya debe estar disponible ⭐⭐⭐
            dao.verificarDisponibilidad(r);

            // ⭐⭐ Después de verificar disponibilidad, YA TIENE EL ESTADO VERDADERO ⭐⭐
            if (!"DISPONIBLE".equals(r.getEstado())) {
                req.setAttribute("error",
                    "La remesa no está disponible para cobro. Estado actual: " + r.getEstado());
                req.getRequestDispatcher("/remesa/cobrar.jsp").forward(req, resp);
                return;
            }

            // Si está DISPONIBLE → mostrar pantalla de confirmación
            req.setAttribute("remesa", r);
            req.getRequestDispatcher("/remesa/confirmarCobro.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
