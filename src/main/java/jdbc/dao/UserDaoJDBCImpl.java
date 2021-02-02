package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
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
            System.out.println("Была создана таблица 'users'");
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
            System.out.println("Была удалена таблица 'users'");
        } catch (SQLException e) {
            System.err.println("Не получилось удалить базу данных.");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users VALUES (default, ?,?,?)", Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("В таблицу 'users' был добавлен пользователь: " + name);
        } catch (SQLException sqlException) {
            System.err.println("Не удалось сохранить объект в базу данных.");
            System.err.println(sqlException.getMessage());
        }
    }


    @Override
    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from users where id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Из таблицы 'users' был удалён пользователь ID#" + id);
        }catch (SQLException sqlException){
            System.err.println("Не удалось удалить объект из базы данных.");
            System.err.println(sqlException.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                users.add(user);
            }
        }catch (SQLException sqlException){
            System.err.println("Не удалось получить список пользователей");
            System.err.println(sqlException.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "Delete from users")) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица 'users' была очищена");
        } catch (SQLException sqlException) {
            System.err.println("Не удалось очистить таблицу от пользователей");
            System.err.println(sqlException.getMessage());
        }
    }
}
