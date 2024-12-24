package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public static final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {}


    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100), " +
                    "lastName VARCHAR(100), " +
                    "age INT" +
                    ");";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users;";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            session.getTransaction().commit();
            return users;
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users;";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}