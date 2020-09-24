package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            String sql =
                    "CREATE table if not exists user_info" +
                            " (id BIGINT auto_increment," +
                            " name varchar(256), " +
                            "lastname varchar(256), " +
                            "age TINYINT," +
                            "PRIMARY KEY(id))";
            session.createSQLQuery(sql).executeUpdate();
            trans.commit();
            System.out.println("Created table " + sql + "in th database");
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            String sql = "DROP TABLE  IF EXISTS user_info";
            session.createSQLQuery(sql).executeUpdate();
            trans.commit();
            System.out.println("Table " + sql + "is deleted from database");
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            trans.commit();
            System.out.println("User with" + name + lastName + age + " is added");
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                System.out.println("user was deleted");
                trans.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            userList = session.createQuery("FROM user_info").list();
            trans.commit();
            System.out.println(userList.toString());
        } catch (Exception e) {
            if (null != session.getTransaction()) {
                System.out.println("Transaction is Rolled Back");
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            String sql = "DELETE FROM user_info";
            session.createSQLQuery(sql).executeUpdate();
            trans.commit();
            session.close();
        }
    }
}

