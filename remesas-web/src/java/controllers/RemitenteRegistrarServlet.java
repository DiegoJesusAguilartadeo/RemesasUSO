package controllers;

import dao.RemitenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Remitente;

@WebServlet(name="RemitenteRegistrarServlet", urlPatterns={"/remitente/registrar"})
public class RemitenteRegistrarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String pais = req.getParameter("pais");
        String telefono = req.getParameter("telefono");
        try {
            new RemitenteDAO().insertar(new Remitente(nombre, apellido, pais, telefono));
            resp.sendRedirect(req.getContextPath() + "/remitente/listar.jsp?ok=1");
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
