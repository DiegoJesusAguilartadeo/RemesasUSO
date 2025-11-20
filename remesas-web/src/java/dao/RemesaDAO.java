package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Remesa;

public class RemesaDAO {

    /* ============================================================
                     MAPEO DE RESULTSET → REMESA
       ============================================================ */
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

        try { r.setIdEmpleadoRegistro((Integer) rs.getObject("idEmpleadoRegistro")); } catch (Exception e) {}
        try { r.setIdEmpleadoCobro((Integer) rs.getObject("idEmpleadoCobro")); } catch (Exception e) {}

        // 🔹 Si el SELECT trae alias de nombres, los llenamos (si no, quedan null)
        try { r.setNombreRemitente(rs.getString("nombreRemitente")); } catch (Exception e) {}
        try { r.setNombreDestinatario(rs.getString("nombreDestinatario")); } catch (Exception e) {}

        return r;
    }

    /* ============================================================
                         INSERTAR REMESA
       ============================================================ */
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

    /* ============================================================
                         METODOS DE ESTADO
       ============================================================ */
    public void actualizarEstado(int idRemesa, String nuevoEstado) throws Exception {
        String sql = "UPDATE Remesa SET estado = ?, fechaCobro = NULL WHERE idRemesa = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idRemesa);
            ps.executeUpdate();
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

    /** 
     * Verifica si ya pasó la fechaDisponible y cambia a DISPONIBLE
     */
    public void verificarDisponibilidad(Remesa r) throws Exception {
        if (r == null || r.getFechaDisponible() == null) return;

        Date hoy = new Date(System.currentTimeMillis());

        if (hoy.compareTo(r.getFechaDisponible()) >= 0
                && !"DISPONIBLE".equals(r.getEstado())
                && !"PAGADA".equals(r.getEstado())
                && !"CANCELADA".equals(r.getEstado())) {

            actualizarEstado(r.getIdRemesa(), "DISPONIBLE");
            registrarHistorial(r.getIdRemesa(), "DISPONIBLE");
            r.setEstado("DISPONIBLE");
        }
    }

    /* ============================================================
                         CONSULTAS BÁSICAS
       ============================================================ */
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

    /* ============================================================
             CONSULTAS POR RANGO DE FECHA (SIN JOIN)
       ============================================================ */

    public List<Remesa> listarPorRangoFechaEnvio(Date inicio, Date fin) throws Exception {
        List<Remesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM Remesa WHERE fechaEnvio BETWEEN ? AND ? ORDER BY fechaEnvio, idRemesa";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, inicio);
            ps.setDate(2, fin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public List<Remesa> listarPorRangoFechaCobro(Date inicio, Date fin) throws Exception {
        List<Remesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM Remesa WHERE fechaCobro BETWEEN ? AND ? ORDER BY fechaCobro, idRemesa";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, inicio);
            ps.setDate(2, fin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /* ============================================================
       CONSULTA PARA REPORTE: RANGO FECHA + EMPLEADO + NOMBRES
       ============================================================ */

    public List<Remesa> listarPorRangoYEmpleado(
            Date inicio,
            Date fin,
            String tipoFecha,          // "envio" o "cobro"
            Integer idEmpleadoRegistro // null = todos
    ) throws Exception {

        String campoFecha = "envio".equals(tipoFecha) ? "r.fechaEnvio" : "r.fechaCobro";

        StringBuilder sql = new StringBuilder(
            "SELECT r.*, " +
            "       rem.nombre AS nombreRemitente, " +
            "       des.nombre AS nombreDestinatario " +
            "FROM Remesa r " +
            "JOIN Remitente rem ON r.idRemitente = rem.idRemitente " +
            "JOIN Destinatario des ON r.idDestinatario = des.idDestinatario " +
            "WHERE " + campoFecha + " BETWEEN ? AND ?"
        );

        if (idEmpleadoRegistro != null) {
            sql.append(" AND r.idEmpleadoRegistro = ?");
        }

        sql.append(" ORDER BY ").append(campoFecha).append(", r.idRemesa");

        List<Remesa> lista = new ArrayList<>();

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql.toString())) {

            ps.setDate(1, inicio);
            ps.setDate(2, fin);

            if (idEmpleadoRegistro != null) {
                ps.setInt(3, idEmpleadoRegistro);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }

        return lista;
    }

    /* ============================================================
                          COBRAR REMESA
       ============================================================ */
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

public Remesa obtenerPorIdConDetalles(int idRemesa) throws Exception {
    String sql =
        "SELECT r.*, " +
        "       rem.nombre + ' ' + rem.apellido AS nombreRemitente, " +
        "       des.nombre + ' ' + des.apellido AS nombreDestinatario " +
        "FROM Remesa r " +
        "JOIN Remitente rem ON r.idRemitente = rem.idRemitente " +
        "JOIN Destinatario des ON r.idDestinatario = des.idDestinatario " +
        "WHERE r.idRemesa = ?";

    try (Connection c = Conexion.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, idRemesa);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return mapear(rs); // mapear ya llena nombreRemitente y nombreDestinatario
            }
        }
    }

    return null;
}

}