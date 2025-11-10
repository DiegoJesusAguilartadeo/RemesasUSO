package dao;

import java.sql.*;
import java.util.*;
import model.Destinatario;

public class DestinatarioDAO {

    public void insertar(Destinatario d) throws Exception {
        String sql = "INSERT INTO Destinatario(nombre,apellido,telefono,direccion) VALUES (?,?,?,?)";
        try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellido());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getDireccion());
            ps.executeUpdate();
        }
    }

    public List<Destinatario> listar() throws Exception {
        List<Destinatario> out = new ArrayList<>();
        String sql = "SELECT idDestinatario,nombre,apellido,telefono,direccion FROM Destinatario ORDER BY idDestinatario DESC";
        try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Destinatario d = new Destinatario();
                d.setIdDestinatario(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setApellido(rs.getString(3));
                d.setTelefono(rs.getString(4));
                d.setDireccion(rs.getString(5));
                out.add(d);
            }
        }
        return out;
    }
}
