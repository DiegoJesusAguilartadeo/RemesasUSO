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

@WebServlet("/remesa/buscar")
public class RemesaBuscarCobroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pin = req.getParameter("pin");

        try {
            Remesa r = new RemesaDAO().buscarPorPin(pin);

            if (r == null) {
                req.setAttribute("error", "No existe una remesa con ese PIN.");
                req.getRequestDispatcher("/remesa/cobrar.jsp").forward(req, resp);
                return;
            }

            if (!"DISPONIBLE".equals(r.getEstado())) {
                req.setAttribute("error", "La remesa no está disponible: " + r.getEstado());
                req.getRequestDispatcher("/remesa/cobrar.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("remesa", r);
            req.getRequestDispatcher("/remesa/confirmarCobro.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
