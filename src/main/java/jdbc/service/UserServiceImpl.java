package jdbc.service;

import jdbc.dao.UserDao;
import jdbc.dao.UserDaoHibernateImpl;
import jdbc.dao.UserDaoJDBCImpl;
import jdbc.model.User;

import java.util.List;

public class UserServiceImpl  implements UserService{
//    private UserDao daoImpl = new UserDaoJDBCImpl();
    private UserDao daoImpl = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        daoImpl.createUserTable();
    }

    @Override
    public void dropUsersTable() {
        daoImpl.dropUserTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        daoImpl.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        daoImpl.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return daoImpl.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        daoImpl.cleanUsersTable();
    }
}
