package dao;

// ---------------------------------------------------------------
// Importaciones JDBC necesarias para trabajar con base de datos.
// Estas clases permiten crear conexiones, ejecutar SQL y procesar resultados.
// ---------------------------------------------------------------
import java.sql.*;

// ---------------------------------------------------------------
// Librerías utilitarias para manejar listas dinámicas en Java.
// List: interfaz de lista
// ArrayList: implementación concreta usada para almacenar múltiples remesas.
// ---------------------------------------------------------------
import java.util.*;

// ---------------------------------------------------------------
// Importamos el modelo Remesa, que representa una fila de la tabla Remesa.
// Cada objeto Remesa contiene: id, remitente, destinatario, monto, fecha, referencia.
// ---------------------------------------------------------------
import model.Remesa;


// ---------------------------------------------------------------
// CLASE RemesaDAO (Data Access Object)
//
// Su única responsabilidad es ACCEDER a la base de datos.
// Aquí NO va lógica de negocio, NI HTML, NI servlets, solo SQL.
// ---------------------------------------------------------------
public class RemesaDAO {

    // -----------------------------------------------------------
    // MÉTODO insertar(Remesa r)
    //
    // Recibe un objeto Remesa y lo guarda en la tabla Remesa.
    //
    // throws Exception → permitimos que el servlet maneje el error.
    // -----------------------------------------------------------
    public void insertar(Remesa r) throws Exception {

        // -------------------------------------------------------
        // SQL parametrizado para evitar SQL Injection.
        //
        // Cada ? es un valor que se asignará con ps.setXXX().
        // -------------------------------------------------------
        String sql = "INSERT INTO Remesa(idRemitente,idDestinatario,monto,fechaEnvio,referencia) "
                   + "VALUES (?,?,?,?,?)";

        // -------------------------------------------------------
        // try-with-resources:
        //   - Abre conexión (Connection)
        //   - Prepara sentencia SQL (PreparedStatement)
        //   - Cierra todo automáticamente al salir del bloque.
        //
        // Conexion.getConnection():
        //   - Es tu clase personalizada para conectarse a SQL Server.
        // -------------------------------------------------------
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            // ---------------------------------------------------
            // Asignación de valores al INSERT.
            // El orden debe coincidir EXACTAMENTE con los ? del SQL.
            // ---------------------------------------------------
            ps.setInt(1, r.getIdRemitente());     // idRemitente (FK)
            ps.setInt(2, r.getIdDestinatario());  // idDestinatario (FK)
            ps.setDouble(3, r.getMonto());        // monto enviado
            ps.setDate(4, r.getFechaEnvio());     // fecha SQL (java.sql.Date)
            ps.setString(5, r.getReferencia());   // referencia o código interno

            // Ejecutamos el INSERT en la base de datos.
            ps.executeUpdate(); // Debe afectar 1 fila.
        }
    }


    // -----------------------------------------------------------
    // MÉTODO listar()
    //
    // Obtiene TODAS las remesas almacenadas en la BD.
    // Devuelve List<Remesa>, nunca null.
    //
    // Este método lo usa RemesaListarServlet.
    // -----------------------------------------------------------
    public List<Remesa> listar() throws Exception {

        // -------------------------------------------------------
        // Lista vacía donde agregaremos cada remesa encontrada.
        // -------------------------------------------------------
        List<Remesa> out = new ArrayList<>();

        // -------------------------------------------------------
        // SQL SELECT que obtiene todas las columnas necesarias.
        //
        // ORDER BY idRemesa DESC:
        //   Muestra primero las remesas más recientes.
        // -------------------------------------------------------
        String sql = "SELECT idRemesa,idRemitente,idDestinatario,monto,fechaEnvio,referencia "
                   + "FROM Remesa ORDER BY idRemesa DESC";

        // -------------------------------------------------------
        // try-with-resources para:
        //   - Abrir conexión con la BD
        //   - Preparar el SELECT
        //   - Ejecutarlo y obtener ResultSet
        //
        // ResultSet rs:
        //   Representa una tabla virtual con filas y columnas.
        // -------------------------------------------------------
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // ---------------------------------------------------
            // Recorremos el resultado fila por fila.
            // Cada rs.next() mueve el cursor a la siguiente fila.
            // ---------------------------------------------------
            while (rs.next()) {

                // -----------------------------------------------
                // Creamos un objeto Remesa vacío.
                // -----------------------------------------------
                Remesa r = new Remesa();

                // -----------------------------------------------
                // Llenamos sus campos desde las columnas SQL.
                //
                // rs.getInt(1) → columna 1 → idRemesa
                // rs.getInt(2) → columna 2 → idRemitente
                // rs.getInt(3) → columna 3 → idDestinatario
                // rs.getDouble(4) → monto
                // rs.getDate(5) → fechaEnvio
                // rs.getString(6) → referencia
                //
                // Usar índices es rápido, pero requiere mantener
                // el mismo orden que en el SELECT.
                // -----------------------------------------------
                r.setIdRemesa(rs.getInt(1));
                r.setIdRemitente(rs.getInt(2));
                r.setIdDestinatario(rs.getInt(3));
                r.setMonto(rs.getDouble(4));
                r.setFechaEnvio(rs.getDate(5));
                r.setReferencia(rs.getString(6));

                // -----------------------------------------------
                // Agregamos la remesa a la lista final.
                // -----------------------------------------------
                out.add(r);
            }
        }

        // -------------------------------------------------------
        // Devolvemos la lista (vacía si no hubo datos).
        // Nunca devolvemos null (buena práctica).
        // -------------------------------------------------------
        return out;
    }
}
