package jdbc;

import jdbc.dao.UserDaoJDBCImpl;
import jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Михаил", "Байанга", (byte) 29);
        userService.saveUser("Елена", "Байанга", (byte) 61);
        userService.saveUser("Виктор", "Чирков", (byte) 28);
        userService.saveUser("Иван", "Гончаров", (byte) 29);
        System.out.println("Выводим текущий список пользователей.");
        System.out.println(userService.getAllUsers());
        System.out.println("Сейчас будет удалён пользователь");
        userService.removeUserById(3);
        System.out.println("Выводим текущий список пользователей.");
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println("Выводим текущий список пользователей.");
        System.out.println(userService.getAllUsers());
    }
}
