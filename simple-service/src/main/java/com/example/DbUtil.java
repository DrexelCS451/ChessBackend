package com.example;

import com.example.Models.DBUser;
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
        DBUser user = new DBUser("testymctestersonsonson");

//      Test user stuff

        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

}
