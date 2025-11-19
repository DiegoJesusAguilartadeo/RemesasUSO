package dao;

import java.sql.*;
import java.util.*;
import model.Remesa;

public class RemesaDAO {

    private Remesa mapear(ResultSet rs) throws Exception {
        Remesa r = new Remesa();
        r.setIdRemesa(rs.getInt("idRemesa"));
        r.setIdRemitente(rs.getInt("idRemitente"));
        r.setIdDestinatario(rs.getInt("idDestinatario"));
        r.setMonto(rs.getDouble("monto"));
        r.setFechaEnvio(rs.getDate("fechaEnvio"));
        r.setReferencia(rs.getString("referencia"));
        r.setPin(rs.getString("pin"));
        r.setEstado(rs.getString("estado"));
        r.setFechaDisponible(rs.getDate("fechaDisponible"));
        r.setMetodoCobro(rs.getString("metodoCobro"));
        r.setMontoTotal(rs.getDouble("montoTotal"));
        r.setFee(rs.getDouble("fee"));
        r.setNumeroOrden(rs.getString("numeroOrden"));
        r.setFechaCobro(rs.getDate("fechaCobro"));
        // empleados (si existen columnas)
        try { r.setIdEmpleadoRegistro((Integer)rs.getObject("idEmpleadoRegistro")); } catch(Exception e) {}
        try { r.setIdEmpleadoCobro((Integer)rs.getObject("idEmpleadoCobro")); } catch(Exception e) {}
        try { r.setIdEmpleadoCreador((Integer)rs.getObject("idEmpleadoCreador")); } catch(Exception e) {}
        try { r.setIdEmpleadoCobrador((Integer)rs.getObject("idEmpleadoCobrador")); } catch(Exception e) {}
        return r;
    }

    public void insertar(Remesa r) throws Exception {

        String sql = "INSERT INTO Remesa " +
                "(idRemitente, idDestinatario, monto, fechaEnvio, referencia, " +
                "pin, estado, fechaDisponible, metodoCobro, montoTotal, fee, numeroOrden, fechaCobro, idEmpleadoRegistro) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, r.getIdRemitente());
            ps.setInt(2, r.getIdDestinatario());
            ps.setDouble(3, r.getMonto());
            ps.setDate(4, r.getFechaEnvio());
            ps.setString(5, r.getReferencia());
            ps.setString(6, r.getPin());
            ps.setString(7, r.getEstado());
            ps.setDate(8, r.getFechaDisponible());
            ps.setString(9, r.getMetodoCobro());
            ps.setDouble(10, r.getMontoTotal());
            ps.setDouble(11, r.getFee());
            ps.setString(12, r.getNumeroOrden());
            ps.setDate(13, r.getFechaCobro());
            if (r.getIdEmpleadoRegistro() != null) {
                ps.setInt(14, r.getIdEmpleadoRegistro());
            } else {
                ps.setNull(14, Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    r.setIdRemesa(rs.getInt(1));
                }
            }
        }
    }

    public void verificarDisponibilidad(Remesa r) throws Exception {
        if (r == null) return;
        long ahora = System.currentTimeMillis();
        long fechaDisponible = r.getFechaDisponible().getTime();
        boolean yaPaso24h = ahora >= fechaDisponible;

        if (yaPaso24h 
            && !"DISPONIBLE".equals(r.getEstado()) 
            && !"PAGADA".equals(r.getEstado()) 
            && !"CANCELADA".equals(r.getEstado())) {

            String sql = "UPDATE Remesa SET estado = 'DISPONIBLE' WHERE idRemesa = ?";

            try (Connection c = Conexion.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, r.getIdRemesa());
                ps.executeUpdate();
            }

            registrarHistorial(r.getIdRemesa(), "DISPONIBLE");
            r.setEstado("DISPONIBLE");
        }
    }

    public Remesa buscarPorPin(String pin) throws Exception {
        String sql = "SELECT * FROM Remesa WHERE pin = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pin);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Remesa r = mapear(rs);
                    verificarDisponibilidad(r);
                    return r;
                }
            }
        }
        return null;
    }

    public List<Remesa> listar() throws Exception {
        List<Remesa> out = new ArrayList<>();
        String sql = "SELECT * FROM Remesa ORDER BY idRemesa DESC";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                out.add(mapear(rs));
            }
        }
        return out;
    }

    /**
     * Cobra la remesa indicando el id del empleado que la cobra (idEmpleadoCobro).
     */
    public void cobrarRemesa(String pin, int idEmpleadoCobro) throws Exception {

        String sql = "UPDATE Remesa " +
                     "SET estado = 'PAGADA', fechaCobro = GETDATE(), idEmpleadoCobro = ? " +
                     "WHERE pin = ? AND estado = 'DISPONIBLE'";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idEmpleadoCobro);
            ps.setString(2, pin);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("La remesa no está disponible para cobro o el PIN no existe.");
            }
        }
    }

    public void registrarHistorial(int idRemesa, String estado) throws Exception {
        String sql = "INSERT INTO EstadoRemesaHistorial(idRemesa, estado) VALUES (?, ?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idRemesa);
            ps.setString(2, estado);
            ps.executeUpdate();
        }
    }
}
