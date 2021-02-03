package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao{
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private Session session;

    public UserDaoHibernateImpl(){}

    @Override
    public void createUserTable() {
        try {
            session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(Util.CREATE_TABLE_SQL);
            sqlQuery.executeUpdate();
            transaction.commit();
            System.out.println("HIBERNATE: была создана таблица users");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void dropUserTable() {
        try{
            session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(Util.DROP_TABLE_SQL);
            sqlQuery.executeUpdate();
            transaction.commit();
            System.out.println("HIBERNATE: была удалена таблица users");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            User newUser = new User(name, lastName, age);
            session.save(newUser);
            transaction.commit();
            System.out.println("HIBERNATE: в таблицу users был добавлен новый пользователь: " + name);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            User newUser = (User) session.get(User.class, id);
            session.delete(newUser);
            transaction.commit();
            System.out.println("HIBERNATE: из таблицы users был удалён пользователь: " + newUser.getName());
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            transaction.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            System.out.println("HIBERNATE: таблица users была очищена");
            transaction.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
