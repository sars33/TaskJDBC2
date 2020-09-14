package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("userbank?").
                    append("user=root&").
                    append("password=root").
                    append("&serverTimezone=UTC");

           // System.out.println("URL: " + url + "\n");

            Connection conn = DriverManager.getConnection(url.toString());
            return conn;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } finally {

        }
    }
}
