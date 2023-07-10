package org.example.util;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Данный класс предназначен для "грязной" работы, например, подключение к БД,
 * выполнение сторонних подключений и прочее
 */
public class Util {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String DB_LOGIN = "postgres";

    private static final String DB_PASSWORD = "password";

    public static Connection getConnection() {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD)) {
            System.out.println("Connection is open successfully!!!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection is uncorrected!!!");
            e.printStackTrace();
        }
        return null;
    }
}
