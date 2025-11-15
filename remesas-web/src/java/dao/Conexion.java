package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import utils.Config;

public class Conexion {

    private static final String URL  = Config.get("DB_URL");
    private static final String USER = Config.get("DB_USER");
    private static final String PASS = Config.get("DB_PASS");

    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}