package controllers;

import dao.RemesaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import model.Remesa;

@WebServlet(name="RemesaRegistrarServlet", urlPatterns={"/remesa/registrar"})
public class RemesaRegistrarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            int idRemitente = Integer.parseInt(req.getParameter("idRemitente"));
            int idDestinatario = Integer.parseInt(req.getParameter("idDestinatario"));
            double monto = Double.parseDouble(req.getParameter("monto"));
            Date fechaEnvio = Date.valueOf(req.getParameter("fechaEnvio")); // yyyy-MM-dd
            String referencia = req.getParameter("referencia");

            new RemesaDAO().insertar(new Remesa(idRemitente, idDestinatario, monto, fechaEnvio, referencia));
            resp.sendRedirect(req.getContextPath() + "/remesa/listar.jsp?ok=1");
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
