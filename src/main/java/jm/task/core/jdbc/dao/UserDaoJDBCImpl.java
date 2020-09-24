package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getMyConnection();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE table if not exists user_info (id Int auto_increment PRIMARY KEY , name varchar(256), lastname varchar(256), age int)";
        try (PreparedStatement prepStm = getMyConnection().prepareStatement(sql)) {
            prepStm.execute(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE  IF EXISTS userbank.user_info";
        try (PreparedStatement prepStm = getMyConnection().prepareStatement(sql)) {
            prepStm.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO userbank.user_info(name, lastName, age) VALUES (?,?,?)";

        try (PreparedStatement prepStat = getMyConnection().prepareStatement(sql)) {
            prepStat.setString(1, name);
            prepStat.setString(2, lastName);
            prepStat.setInt(3, age);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {

        String sql = "DELETE FROM userbank.user_info WHERE id = ?";

        try (PreparedStatement prepState = getMyConnection().prepareStatement(sql)) {
            prepState.setLong(1, id);
            prepState.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM userbank.user_info";

        try (PreparedStatement stm = getMyConnection().prepareStatement(sql)) {
            ResultSet resultSet = stm.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();

                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE  FROM userbank.user_info";
        try (PreparedStatement prepStat = getMyConnection().prepareStatement(sql)) {
            prepStat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
