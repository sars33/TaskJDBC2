package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getMyConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {


    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {

    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {

    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return null;
    }

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {

    }
}
