package controllers;

import dao.DestinatarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Destinatario;

@WebServlet(name="DestinatarioRegistrarServlet", urlPatterns={"/destinatario/registrar"})
public class DestinatarioRegistrarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");
        try {
            new DestinatarioDAO().insertar(new Destinatario(nombre, apellido, telefono, direccion));
            resp.sendRedirect(req.getContextPath() + "/destinatario/listar.jsp?ok=1");
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
