/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import dao.Conexion;
import model.Remitente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/remitente/buscar")
public class RemitenteSearchServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json;charset=UTF-8");

        String q = req.getParameter("q");
        if (q == null || q.trim().isEmpty()) {
            resp.getWriter().write("[]");
            return;
        }

        try (Connection cn = Conexion.getConnection()) {

            String sql = "SELECT idRemitente, nombre, apellido FROM Remitente "
                       + "WHERE nombre LIKE ? OR apellido LIKE ?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + q + "%");
            ps.setString(2, "%" + q + "%");

            ResultSet rs = ps.executeQuery();

            StringBuilder json = new StringBuilder("[");
            boolean primero = true;

            while (rs.next()) {
                if (!primero) json.append(",");
                primero = false;

                json.append("{")
                    .append("\"id\":").append(rs.getInt("idRemitente")).append(",")
                    .append("\"nombre\":\"")
                    .append(rs.getString("nombre")).append(" ").append(rs.getString("apellido"))
                    .append("\"}")
                ;
            }

            json.append("]");
            resp.getWriter().write(json.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().write("[]");
        }
    }
}

