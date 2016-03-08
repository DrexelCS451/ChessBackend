package com.group6chess;

import com.group6chess.Models.DBUser;
import com.group6chess.util.HibernateUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        if(username == null){
            return "{\"status\" : false}";
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();
        session.beginTransaction();
        System.out.println(username);
        try {
            List result = session.createCriteria(DBUser.class)
                    .add(Restrictions.eq("username", username)).list();
            if (!result.isEmpty()) {
                return "{\"status\" : true}";
            } else {
                return  "{\"status\" : false}";
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
        return "{\"userId\":"+ newUser.getUserId()+ "}";
    }
}
