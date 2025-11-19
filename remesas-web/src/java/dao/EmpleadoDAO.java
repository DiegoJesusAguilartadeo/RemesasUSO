/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpleadoDAO {

    /**
     * Devuelve el idEmpleado si existe; o null si no existe.
     */
    public Integer obtenerIdPorEmail(String correo) throws Exception {
        String sql = "SELECT idEmpleado FROM Empleado WHERE correo = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idEmpleado");
                }
            }
        }
        return null;
    }

    /**
     * Crea un empleado mínimo con correo y nombre si no existe,
     * y devuelve su idEmpleado.
     * Útil si quieres auto-crear la cuenta del empleado al hacer login por Google.
     */
    public Integer crearSiNoExisteYObtenerId(String nombre, String correo) throws Exception {
        Integer id = obtenerIdPorEmail(correo);
        if (id != null) return id;

        String sql = "INSERT INTO Empleado (nombre, correo) VALUES (?, ?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return null;
    }
}
