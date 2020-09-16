package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        addUserToDatabase();
        showAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

    public static void addUserToDatabase() throws SQLException, ClassNotFoundException {

        UserService userService = new UserServiceImpl();

        User user = new User("John", "Black", (byte) 45);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем " + user.getName() + " добавлен в базу данных");

        User user2 = new User("Peter", "Krog", (byte) 28);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем " + user2.getName() + " добавлен в базу данных");

        User user3 = new User("Maria", "Garcia", (byte) 35);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем " + user3.getName() + " добавлен в базу данных");

        User user4 = new User("Andrei", "Matveev", (byte) 44);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем " + user4.getName() + " добавлен в базу данных");
    }

    public static void showAllUsers() throws SQLException, ClassNotFoundException {

        UserService userService = new UserServiceImpl();

        userService.getAllUsers();
        List<User> users = userService.getAllUsers();
        System.out.println(users);
    }

}






