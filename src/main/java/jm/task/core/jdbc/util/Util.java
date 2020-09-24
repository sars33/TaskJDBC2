package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
        }
    }

    // Configurate Hibernate connection:
    private static SessionFactory sessionFactory;

    static {
        Configuration config = new Configuration();
        Properties prop = new Properties();
        prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        prop.put(Environment.URL, "jdbc:mysql://localhost:3306/userbank?serverTimezone=UTC&useSSL=false");
        prop.put(Environment.USER, "root");
        prop.put(Environment.PASS, "root");
        prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        prop.put(Environment.SHOW_SQL, "true");
        prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        prop.put(Environment.HBM2DDL_AUTO, "create-drop");

        config.setProperties(prop)
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

        ServiceRegistry servReg = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();

        sessionFactory = config.buildSessionFactory(servReg);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

