package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection(){
        Connection connection = null;
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_hibernate_test";
        String userName = "zettai";
        String password = "zettai";
        try {
            Class.forName(driverClassName);
        }catch (ClassNotFoundException cnfe){
            System.err.println("Такого драйвера не существует");

        }
        try {
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException sqlException) {
            System.err.println("Ошибка в получении соединения.");
        }
        return connection;
    }

}
