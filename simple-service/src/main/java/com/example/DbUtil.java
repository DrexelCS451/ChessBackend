package com.example;

import com.example.user.DBUser;
import com.example.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by tomer on 2/5/16.
 */
public class DbUtil {

    public DbUtil()
    {}

    public void create()
    {
        System.out.println("Maven + Hibernate Annotation + Oracle");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        DBUser user = new DBUser();

        user.setUserId(100);
        user.setFirstname("Hibernate101");
        user.setLastname("system");

        session.save(user);
        session.getTransaction().commit();
    }

}
