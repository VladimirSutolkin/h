package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    private static final UserService userService =  new UserServiceImpl();
    private static final User user1 = new User("Vladimir", "Sutolkin", (byte) 99);
    private static final User user2 = new User("Vladimir", "Sutolkin", (byte) 98);
    private static final User user3 = new User("Vladimir", "Sutolkin", (byte) 97);
    private static final User user4 = new User("Vladimir", "Sutolkin", (byte) 96);
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> strings = userService.getAllUsers();
        for (User s : strings)
            System.out.println(s);

        userService.removeUserById(3);

        userService.cleanUsersTable();

        userService.dropUsersTable();


        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
      Util.closeConnection();


    }
}
