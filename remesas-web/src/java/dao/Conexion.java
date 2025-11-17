package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    private static final String URL = 
        "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=RemesasDB;encrypt=false;trustServerCertificate=true;";
    
private static final String USER = "cliente_user";
private static final String PASS = "Prueba123!";

    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
