package ru.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataSource {

    private static final String ORACLE_JDBC_DRIVER_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static Connection connection;

    public static Connection getConnetction() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName(ORACLE_JDBC_DRIVER_ORACLE_DRIVER);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.4.0.119:1521/xe", "gladkikh13204", "gladkikh13204");
            return connection;
        } else {
            return connection;
        }
    }
}
