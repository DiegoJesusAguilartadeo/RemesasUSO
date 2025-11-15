package utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jeusu
 */import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties props = new Properties();

    static {
        try {
            InputStream in = Config.class.getClassLoader()
                .getResourceAsStream("config/config.properties");

            if (in == null) {
                throw new RuntimeException("No se encontró config.properties en WEB-INF/config");
            }

            props.load(in);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error cargando config.properties");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}