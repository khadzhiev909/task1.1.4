package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL = "jdbc:mysql://localhost:3306/base1";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    private static final Logger logger = Logger.getLogger(Util.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Загрузка драйвера JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Установка соединения с базой данных
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("connect db");
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
