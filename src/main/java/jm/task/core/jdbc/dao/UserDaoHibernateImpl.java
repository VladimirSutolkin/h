package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Transactional

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory factory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
        session = factory.getCurrentSession();
     transaction=   session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS mydptest.users" +
                " (id mediumint not null auto_increment, name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age tinyint, " +
                "PRIMARY KEY (id))").executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица создана");
    } catch (Exception e) {
        e.printStackTrace();

    } finally {
        if (session != null)
            session.close();
    }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
   session = factory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("Drop table if exists mydptest.users").executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица удалена");
    } catch (Exception e) {
        e.printStackTrace();

    } finally {
        if (session != null)
            session.close();
    }
    }



    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            User user = new User(name, lastName, age);
            session = factory.getCurrentSession();
            transaction= session.beginTransaction();
            session.save(user);
           transaction.commit();
        } catch (Exception e ) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }

        finally {
            if (session != null)
             session.close();
        }
        }







    @Override
    public void removeUserById(long id) {
        Session session= null;
        Transaction transaction = null;
try {
        session = factory.getCurrentSession();
        session.beginTransaction();
        User us = session.get(User.class, id);
        session.delete(us);
        session.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
    if (transaction != null) {
        transaction.rollback();
    }

    } finally {
        if (session != null)
            session.close();
    }
}


    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List <User> users = new ArrayList<>();
        Transaction transaction = null;
        try {
        session = factory.getCurrentSession();
       transaction= session.beginTransaction();
       users =  session.createQuery("from User")
                .getResultList();
       session.getTransaction().commit();
       transaction.commit();

    } catch (Exception e) {
        e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

    } finally {
        if (session != null)
            session.close();
    }
        return users;

}



    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
         session=   factory.getCurrentSession();
        transaction= session.beginTransaction();
        List <User> users =  session.createQuery("from User")
                .getResultList();
        for (User u: users){
            session.delete(u);
        }
        session.getTransaction().commit();
        System.out.println("Таблица очищена");

    } catch (Exception e) {
        e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

    } finally {
        if (session != null)
            session.close();
    }
    }
}
