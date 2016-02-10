package com.example;

import com.example.Models.DBUser;
import com.example.util.HibernateUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import com.google.gson.Gson;
import org.omg.CORBA.Object;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("user")
public class UserResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("username") String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();
        session.beginTransaction();
        System.out.println(username);
        try {
            List result = session.createCriteria(DBUser.class)
                    .add(Restrictions.eq("username", username)).list();
            if (!result.isEmpty()) {
                return gson.toJson(result);
            } else {
                return "{\"ERROR\" : \"Could not find user!\"}";
            }
        } catch (HibernateException e) {
            return e.getMessage();
        } finally {
            session.close();
        }
    }
    /**
    *  Posts a new user to the database
    */@POST
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String httpBody){
        Session session = HibernateUtil.getSessionFactory().openSession();
        JsonParser parser = new JsonParser();
        session.beginTransaction();
        JsonObject body = parser.parse(httpBody).getAsJsonObject();
        String username = body.get("username").getAsString();

        DBUser newUser = new DBUser(username);
        session.save(newUser);

        System.out.println(username);
        session.getTransaction().commit();
        session.close();
        return "{\"Success\":\"User "+username+" added to database\"}";

    }
}