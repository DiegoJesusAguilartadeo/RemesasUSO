package dao;

import java.sql.*;
import java.util.*;
import model.Remitente;

public class RemitenteDAO {

    public void insertar(Remitente r) throws Exception {
        String sql = "INSERT INTO Remitente(nombre,apellido,pais,telefono) VALUES (?,?,?,?)";
        try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getApellido());
            ps.setString(3, r.getPais());
            ps.setString(4, r.getTelefono());
            ps.executeUpdate();
        }
    }

    public List<Remitente> listar() throws Exception {
        List<Remitente> out = new ArrayList<>();
        String sql = "SELECT idRemitente,nombre,apellido,pais,telefono FROM Remitente ORDER BY idRemitente DESC";
        try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Remitente r = new Remitente();
                r.setIdRemitente(rs.getInt(1));
                r.setNombre(rs.getString(2));
                r.setApellido(rs.getString(3));
                r.setPais(rs.getString(4));
                r.setTelefono(rs.getString(5));
                out.add(r);
            }
        }
        return out;
    }
}
