package controllers;

import dao.RemesaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/remesa/cobrar")
public class RemesaCobrarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pin = req.getParameter("pin");

        try {
            HttpSession session = req.getSession(false);
            Integer idEmpleadoCobro = null;
            if (session != null && session.getAttribute("usuario_idEmpleado") != null) {
                idEmpleadoCobro = (Integer) session.getAttribute("usuario_idEmpleado");
            } else {
                // Si no hay sesión válida -> error (no autorizado)
                req.setAttribute("error", "Sesión no válida. Inicie sesión con Google.");
                req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
                return;
            }

            RemesaDAO dao = new RemesaDAO();
            dao.cobrarRemesa(pin, idEmpleadoCobro);

            req.setAttribute("pin", pin);
            req.getRequestDispatcher("/remesa/comprobante.jsp").forward(req, resp);

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
