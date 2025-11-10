package dao;

import java.sql.*;
import java.util.*;
import model.Remesa;

public class RemesaDAO {

    public void insertar(Remesa r) throws Exception {
        String sql = "INSERT INTO Remesa(idRemitente,idDestinatario,monto,fechaEnvio,referencia) VALUES (?,?,?,?,?)";
        try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, r.getIdRemitente());
            ps.setInt(2, r.getIdDestinatario());
            ps.setDouble(3, r.getMonto());
            ps.setDate(4, r.getFechaEnvio());
            ps.setString(5, r.getReferencia());
            ps.executeUpdate();
        }
    }

    public List<Remesa> listar() throws Exception {
        List<Remesa> out = new ArrayList<>();
        String sql = "SELECT idRemesa,idRemitente,idDestinatario,monto,fechaEnvio,referencia FROM Remesa ORDER BY idRemesa DESC";
        try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Remesa r = new Remesa();
                r.setIdRemesa(rs.getInt(1));
                r.setIdRemitente(rs.getInt(2));
                r.setIdDestinatario(rs.getInt(3));
                r.setMonto(rs.getDouble(4));
                r.setFechaEnvio(rs.getDate(5));
                r.setReferencia(rs.getString(6));
                out.add(r);
            }
        }
        return out;
    }
}
