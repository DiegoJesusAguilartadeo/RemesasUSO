package dao;

// ---------------------------------------------------------------
// Importaciones JDBC necesarias para trabajar con base de datos.
// ---------------------------------------------------------------

// Connection      → conexión activa con la BD
// PreparedStatement → consulta SQL precompilada con parámetros (seguro contra SQL Injection)
// ResultSet       → resultado de un SELECT, permite recorrer filas
import java.sql.*;

// ---------------------------------------------------------------
// Importamos utilidades de Java. Aquí se usa List y ArrayList.
// ---------------------------------------------------------------
import java.util.*;

// ---------------------------------------------------------------
// Importamos la clase modelo Destinatario, que representa
// una fila de la tabla Destinatario en la base de datos.
// ---------------------------------------------------------------
import model.Destinatario;


// ---------------------------------------------------------------
// Clase DAO para la entidad Destinatario.
// DAO = Data Access Object.
// Se encarga de TODA la comunicación con la base de datos.
// Esta clase no tiene lógica de negocio ni HTML, solo SQL.
// ---------------------------------------------------------------
public class DestinatarioDAO {

    // -----------------------------------------------------------
    // MÉTODO insertar(Destinatario d)
    //
    // Este método guarda un destinatario en la tabla Destinatario.
    // Recibe un objeto Destinatario con nombre, apellido, etc.
    //
    // Uso típico:
    // Destinatario d = new Destinatario();
    // d.setNombre("Juan");
    // new DestinatarioDAO().insertar(d);
    //
    // throws Exception → si algo falla, el servlet puede atraparlo.
    // -----------------------------------------------------------
    public void insertar(Destinatario d) throws Exception {

        // -------------------------------------------------------
        // SQL parametrizado con ?
        //
        // Ventajas:
        // - Evita SQL Injection
        // - Evita errores de comillas
        // - Reutilizable
        // -------------------------------------------------------
        String sql = "INSERT INTO Destinatario(nombre,apellido,telefono,direccion) VALUES (?,?,?,?)";

        // -------------------------------------------------------
        // try-with-resources:
        //   - Cierra automáticamente Connection y PreparedStatement
        //   - Evita fugas de conexión (muy importante)
        //
        // Conexion.getConnection():
        //   - Tu clase Conexion devuelve un Connection activo (driver SQL Server)
        //
        // ps = c.prepareStatement(sql):
        //   - Prepara la consulta con parámetros
        // -------------------------------------------------------
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            // ---------------------------------------------------
            // Llenamos los parámetros del INSERT
            // ps.setString(posición, valor);
            //
            // IMPORTANTE:
            // Las posiciones empiezan en 1, no en 0.
            // ---------------------------------------------------
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellido());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getDireccion());

            // Ejecutamos el INSERT en la BD
            ps.executeUpdate(); // número de filas afectadas (1 si todo salió bien)
        }
    }


    // -----------------------------------------------------------
    // MÉTODO listar()
    //
    // Este método devuelve TODOS los destinatarios de la BD
    // como una List<Destinatario>.
    //
    // El servlet DestinatarioListarServlet usa este método.
    // -----------------------------------------------------------
    public List<Destinatario> listar() throws Exception {

        // -------------------------------------------------------
        // Creamos una lista vacía donde guardaremos los resultados.
        // -------------------------------------------------------
        List<Destinatario> out = new ArrayList<>();

        // -------------------------------------------------------
        // SQL SELECT:
        // Selecciona las columnas que necesitamos de la tabla.
        //
        // ORDER BY idDestinatario DESC:
        // Ordena desde el registro más reciente (ID más alto).
        // -------------------------------------------------------
        String sql = "SELECT idDestinatario,nombre,apellido,telefono,direccion "
                   + "FROM Destinatario ORDER BY idDestinatario DESC";

        // -------------------------------------------------------
        // try-with-resources para:
        // - abrir la conexión
        // - preparar el statement
        // - ejecutar el SELECT → devuelve ResultSet
        //
        // ResultSet rs:
        //   Contiene las filas del SELECT, que recorreremos con rs.next()
        // -------------------------------------------------------
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // ---------------------------------------------------
            // Recorrer el ResultSet fila por fila.
            // Cada rs.next() avanza una fila.
            // ---------------------------------------------------
            while (rs.next()) {

                // -----------------------------------------------
                // Creamos un objeto Destinatario vacío
                // y lo llenamos con los valores del ResultSet.
                // -----------------------------------------------
                Destinatario d = new Destinatario();

                // rs.getInt(1) → primera columna: idDestinatario
                // rs.getString(2) → segunda columna: nombre
                // ... etc.
                //
                // Nota: usar índices 1,2,3... es rápido pero menos legible.
                // Podríamos usar rs.getString("nombre") para más claridad.
                // -----------------------------------------------
                d.setIdDestinatario(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setApellido(rs.getString(3));
                d.setTelefono(rs.getString(4));
                d.setDireccion(rs.getString(5));

                // Añadimos el objeto lleno a la lista final
                out.add(d);
            }
        }

        // Devolvemos la lista, que puede estar vacía si no hay registros.
        return out;
    }
    // ==============================
    // BUSQUEDA POR NOMBRE (LISTA)
    // ==============================
    public List<Destinatario> buscarLista(String nombre) throws Exception {
        List<Destinatario> out = new ArrayList<>();

        String sql = "SELECT idDestinatario, nombre, apellido "
                   + "FROM Destinatario "
                   + "WHERE nombre LIKE ? OR apellido LIKE ? "
                   + "ORDER BY nombre ASC";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + nombre + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Destinatario d = new Destinatario();
                d.setIdDestinatario(rs.getInt("idDestinatario"));
                d.setNombre(rs.getString("nombre"));
                d.setApellido(rs.getString("apellido"));
                out.add(d);
            }
        }

        return out;
    }


    
}
