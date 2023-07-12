package org.example.util;

import org.example.model.Child;
import org.example.model.Toys;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, DB_URL);
            settings.put(Environment.USER, DB_LOGIN);
            settings.put(Environment.PASS, DB_PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
            settings.put(Environment.DEFAULT_SCHEMA, "hibernet_test");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            settings.put(Environment.HBM2DDL_AUTO, "create");

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(Child.class);
            configuration.addAnnotatedClass(Toys.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
