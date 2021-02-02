package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUserTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users" +
                    "(id int not null auto_increment, " +
                    " name varchar(40), " +
                    " lastName varchar(40), " +
                    " age int, " +
                    " primary key( id ));";
            statement.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            System.err.println("Не получилось создать базу данных.");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void dropUserTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String SQL_CREATE_TABLE = "DROP TABLE users;";
            statement.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            System.err.println("Не получилось удалить базу данных.");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users VALUES (?,?,?,?)")){

            preparedStatement.setInt(1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.err.println("Не удалось сохранить объект в базу данных");
            System.err.println(sqlException.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
