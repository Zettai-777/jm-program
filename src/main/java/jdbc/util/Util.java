package jdbc.util;

import jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    // настройки MySQL
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_hibernate_test";
    private static final String USER_NAME = "zettai";
    private static final String PASSWORD = "zettai";
    private static final String MYSQL_DIALECT = "org.hibernate.dialect.MySQLDialect";

    private static SessionFactory sessionFactory;

    //SQL запросы
    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users" +
            "(id bigint not null auto_increment, " +
            " name varchar(40), " +
            " lastName varchar(40), " +
            " age int, " +
            " primary key( id ));";

    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS users;";

    public static Connection getJDBCConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Такого драйвера не существует");
        }

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        } catch (SQLException sqlException) {
            System.err.println("Ошибка в получении соединения.");
        }

        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER_CLASS_NAME);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER_NAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, MYSQL_DIALECT);
                settings.put(Environment.SHOW_SQL, true);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("Не удалось получить фабрику сессий");
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }


    public static void shutDown() {
        sessionFactory.close();
    }
}
