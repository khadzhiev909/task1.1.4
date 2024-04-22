package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String query = "create table if not exists users(" +
                "id bigint AUTO_INCREMENT primary key, " +
                "name varchar(50), " +
                "lastName varchar(50), " +
                "age tinyint);";
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("drop table if exists users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        String name = "1asdas";
        String lastName = "asd3as2";
        byte age = 5;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            allUsers = session.createQuery("from User")
                    .getResultList();
            session.getTransaction().commit();

        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
