package jdbc;

import jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl impl = new UserDaoJDBCImpl();
//        impl.createUserTable();
//        impl.dropUserTable();
        impl.saveUser("Михаил", "Байанга", (byte) 29);
    }
}
