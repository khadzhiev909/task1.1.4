package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    static Connection connection = Util.getConnection();
    private static Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){
            String sql = "create table if not exists users(" +
                    "id bigint AUTO_INCREMENT primary key, " +
                    "user_name varchar(50), " +
                    "user_lastName varchar(50), " +
                    "user_age binary);";
            statement.execute(sql);
            logger.info("create table users");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.execute("drop table if exists users");
            logger.info("table users dropped");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (user_name, user_lastname, user_age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println(name);
            logger.info("user was added in table");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement();) {
            statement.execute("delete from users where id = " + (byte)id);
            logger.info("user with id = " + id + " was deleted");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery("select * from users")){

            while (res.next()) {
                long id = res.getLong("id");
                String name = res.getString("user_name");
                String lastName = res.getString("user_lastName");
                byte age = (byte) res.getInt("user_age");

                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);

            }
            logger.info("table users dropped");

        } catch (SQLException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from users");
            logger.info("all users was deleted");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
