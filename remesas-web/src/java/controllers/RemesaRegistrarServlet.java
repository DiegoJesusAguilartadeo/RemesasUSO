package controllers;

import dao.RemesaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import model.Remesa;
import java.util.UUID;

@WebServlet(name="RemesaRegistrarServlet", urlPatterns={"/remesa/registrar"})
public class RemesaRegistrarServlet extends HttpServlet {

    // Método para generar PIN único estilo RIA/Intermex
    private String generarPin() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }

    // Método para generar número de orden
    private String generarNumeroOrden() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {
            // 1. Capturar parámetros desde el formulario
            int idRemitente = Integer.parseInt(req.getParameter("idRemitente"));
            int idDestinatario = Integer.parseInt(req.getParameter("idDestinatario"));
            double monto = Double.parseDouble(req.getParameter("monto"));
            Date fechaEnvio = Date.valueOf(req.getParameter("fechaEnvio")); 
            String referencia = req.getParameter("referencia");

            // 2. Crear objeto Remesa
            Remesa r = new Remesa(idRemitente, idDestinatario, monto, fechaEnvio, referencia);

            // 3. Generar datos automáticos
            r.setPin(generarPin());
            r.setEstado("PENDIENTE");
            r.setFechaDisponible(fechaEnvio);        // disponible mismo día (lo puedes cambiar)
            r.setMetodoCobro("VENTANILLA");          // o del formulario si lo agregas
            r.setFee(10.00);                         // EJEMPLO: fee fijo de 10 (lo ajustamos luego)
            r.setMontoTotal(monto + r.getFee());     // cálculo real
            r.setNumeroOrden(generarNumeroOrden());
            r.setFechaCobro(null);                   // aún no se ha cobrado

            // 4. Guardar en BD
            RemesaDAO dao = new RemesaDAO();
            dao.insertar(r);

            // 5. Registrar historial inicial
            dao.registrarHistorial(r.getIdRemesa(), "PENDIENTE");

            // 6. Redirigir mostrando el PIN generado
            resp.sendRedirect(req.getContextPath() + "/remesa/listar.jsp?ok=1&pin="+r.getPin());

        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}