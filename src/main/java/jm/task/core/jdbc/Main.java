package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        // Создание таблицы User(ов)
        userService.createUsersTable();
        // Добавление 4 User(ов) в таблицу с данными на свой выбор
        userService.saveUser("Maxim1", "Curbanow1", (byte) 1);
        userService.saveUser("Maxim2", "Curbanow2", (byte) 1);
        userService.saveUser("Maxim3", "Curbanow3", (byte) 1);
        userService.saveUser("Maxim4", "Curbanow4", (byte) 1);
        // Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        
        // Очистка таблицы User(ов)
        userService.cleanUsersTable();
        // Удаление таблицы
        userService.dropUsersTable();

    }
}
