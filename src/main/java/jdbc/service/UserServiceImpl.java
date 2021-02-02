package jdbc.service;

import jdbc.dao.UserDaoJDBCImpl;
import jdbc.model.User;

import java.util.List;

public class UserServiceImpl  implements UserService{
    private UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        daoJDBC.createUserTable();
    }

    @Override
    public void dropUsersTable() {
        daoJDBC.dropUserTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        daoJDBC.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        daoJDBC.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return daoJDBC.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        daoJDBC.cleanUsersTable();
    }
}
