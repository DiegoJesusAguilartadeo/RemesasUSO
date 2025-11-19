package dao;

import java.sql.*;
import java.util.*;
import model.Remesa;

public class RemesaDAO {

    // ============================================================
    // INSERTAR REMESA COMPLETA
    // ============================================================
    public void insertar(Remesa r) throws Exception {

        String sql = "INSERT INTO Remesa("
                   + "idRemitente, idDestinatario, monto, fechaEnvio, referencia, "
                   + "pin, estado, fechaDisponible, metodoCobro, montoTotal, fee, numeroOrden, fechaCobro"
                   + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

            ps.executeUpdate();

            // Recuperar ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    r.setIdRemesa(rs.getInt(1));
                }
            }
        }
    }

    // ============================================================
    // BUSCAR POR PIN
    // ============================================================
    public Remesa buscarPorPin(String pin) throws Exception {
        String sql = "SELECT * FROM Remesa WHERE pin = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
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

                return r;
            }
        }
        return null;
    }

    // ============================================================
    // LISTAR TODAS LAS REMESAS
    // ============================================================
    public List<Remesa> listar() throws Exception {
        List<Remesa> out = new ArrayList<>();

        String sql = "SELECT * FROM Remesa ORDER BY idRemesa DESC";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
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

                out.add(r);
            }
        }
        return out;
    }

    // ============================================================
    // ACTUALIZAR ESTADO + FECHA DE COBRO
    // ============================================================
    public void actualizarEstado(int idRemesa, String nuevoEstado, java.sql.Date fechaCobro) throws Exception {

        String sql = "UPDATE Remesa SET estado = ?, fechaCobro = ? WHERE idRemesa = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setDate(2, fechaCobro);
            ps.setInt(3, idRemesa);

            ps.executeUpdate();
        }
    }

    public void actualizarEstado(int idRemesa, String nuevoEstado) throws Exception {
        String sql = "UPDATE Remesa SET estado = ?, fechaCobro = NULL WHERE idRemesa = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idRemesa);

            ps.executeUpdate();
        }
    }

    // ============================================================
    // COBRAR REMESA POR PIN
    // ============================================================
    public void cobrarRemesa(String pin) throws Exception {
        String sql = "UPDATE Remesa " +
                     "SET estado = 'PAGADA', fechaCobro = GETDATE() " +
                     "WHERE pin = ? AND estado = 'DISPONIBLE'";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pin);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("La remesa no está disponible para cobro o el PIN no existe.");
            }
        }
    }

    // ============================================================
    // HISTORIAL DE ESTADOS
    // ============================================================
    public void registrarHistorial(int idRemesa, String estado) throws Exception {

        String sql = "INSERT INTO EstadoRemesaHistorial(idRemesa, estado) VALUES (?, ?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idRemesa);
            ps.setString(2, estado);

            ps.executeUpdate();
        }
    }

    public void verificarDisponibilidad(Remesa r) throws Exception {
        java.sql.Date hoy = new java.sql.Date(System.currentTimeMillis());

        if (r.getFechaDisponible() != null && hoy.compareTo(r.getFechaDisponible()) >= 0) {
            if (!"DISPONIBLE".equals(r.getEstado())) {
                actualizarEstado(r.getIdRemesa(), "DISPONIBLE");
                registrarHistorial(r.getIdRemesa(), "DISPONIBLE");
                r.setEstado("DISPONIBLE");
            }
        }
    }

    // ============================================================
    // 🔎 REPORTES POR FECHA (ENVÍO / COBRO)
    // ============================================================

    /** Lista remesas por rango de FECHA DE ENVÍO (incluye ambos extremos). */
    public List<Remesa> listarPorRangoFechaEnvio(java.sql.Date desde, java.sql.Date hasta) throws Exception {
        List<Remesa> out = new ArrayList<>();

        String sql = "SELECT * FROM Remesa " +
                     "WHERE fechaEnvio BETWEEN ? AND ? " +
                     "ORDER BY fechaEnvio, idRemesa";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, desde);
            ps.setDate(2, hasta);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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

                    out.add(r);
                }
            }
        }

        return out;
    }

    /** Lista remesas por rango de FECHA DE COBRO (para reportes de caja). */
    public List<Remesa> listarPorRangoFechaCobro(java.sql.Date desde, java.sql.Date hasta) throws Exception {
        List<Remesa> out = new ArrayList<>();

        String sql = "SELECT * FROM Remesa " +
                     "WHERE fechaCobro IS NOT NULL " +
                     "AND fechaCobro BETWEEN ? AND ? " +
                     "ORDER BY fechaCobro, idRemesa";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, desde);
            ps.setDate(2, hasta);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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

                    out.add(r);
                }
            }
        }

        return out;
    }
}
