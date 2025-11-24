package controllers;

import dao.Conexion;
import dao.EmpleadoDAO;
import dao.RemesaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import model.Remesa;
import java.util.UUID;

@WebServlet(name="RemesaRegistrarServlet", urlPatterns={"/remesa/registrar"})
public class RemesaRegistrarServlet extends HttpServlet {

    private String generarPin() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }

    private String generarNumeroOrden() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {
            int idRemitente = Integer.parseInt(req.getParameter("idRemitente"));
            int idDestinatario = Integer.parseInt(req.getParameter("idDestinatario"));
            double monto = Double.parseDouble(req.getParameter("monto"));
            Date fechaEnvio = Date.valueOf(req.getParameter("fechaEnvio")); 
            String referencia = req.getParameter("referencia");

            Remesa r = new Remesa(idRemitente, idDestinatario, monto, fechaEnvio, referencia);

            r.setPin(generarPin());
            r.setEstado("PENDIENTE");

            long unDia = 24L * 60 * 60 * 1000;
            Date fechaDisponible = new Date(fechaEnvio.getTime() + unDia);
            r.setFechaDisponible(fechaDisponible);

            r.setMetodoCobro("VENTANILLA");     
            r.setFee(10.00);                   
            r.setMontoTotal(monto + r.getFee());
            r.setNumeroOrden(generarNumeroOrden());
            r.setFechaCobro(null);

            // Obtener idEmpleadoRegistro desde sesión (establecido por OAuth2CallbackServlet)
            HttpSession session = req.getSession(false);
            Integer idEmpleadoRegistro = null;
            if (session != null && session.getAttribute("usuario_idEmpleado") != null) {
                idEmpleadoRegistro = (Integer) session.getAttribute("usuario_idEmpleado");
            }

            r.setIdEmpleadoRegistro(idEmpleadoRegistro);

            RemesaDAO dao = new RemesaDAO();
            dao.insertar(r);

            dao.registrarHistorial(r.getIdRemesa(), "PENDIENTE");

            resp.sendRedirect(req.getContextPath() + "/remesa/listar.jsp?ok=1&pin=" + r.getPin());

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
