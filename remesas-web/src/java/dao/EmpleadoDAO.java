package dao;

import java.sql.*;
import java.util.*;
import model.Empleado;

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
     * Crea un empleado si no existe, usando nombre + correo,
     * y devuelve el idEmpleado creado o encontrado.
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


    /**
     * Lista todos los empleados ordenados por nombre.
     * Útil para llenar un <select> en los reportes.
     */
    public List<Empleado> listar() throws Exception {
        List<Empleado> lista = new ArrayList<>();

        String sql = "SELECT idEmpleado, nombre, correo FROM Empleado ORDER BY nombre";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setIdEmpleado(rs.getInt("idEmpleado"));
                e.setNombre(rs.getString("nombre"));
                e.setCorreo(rs.getString("correo"));
                lista.add(e);
            }
        }

        return lista;
    }


    /**
     * Obtiene los datos completos de un empleado por su ID.
     */
    public Empleado obtenerPorId(int idEmpleado) throws Exception {
        String sql = "SELECT idEmpleado, nombre, correo FROM Empleado WHERE idEmpleado = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado e = new Empleado();
                    e.setIdEmpleado(rs.getInt("idEmpleado"));
                    e.setNombre(rs.getString("nombre"));
                    e.setCorreo(rs.getString("correo"));
                    return e;
                }
            }
        }

        return null;
    }
}
