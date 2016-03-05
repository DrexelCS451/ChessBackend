package com.example;

/**
 * Created by Matt on 3/4/16.
 */
import com.example.Models.DBUser;
import com.example.Models.LobbyUser;
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

@Path("lobby")
public class LobbyResource {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        JsonParser parser = new JsonParser();
        session.beginTransaction();
        Gson gson = new Gson();
       ;


        return gson.toJson( session.createCriteria(LobbyUser.class).list() ).toString();
    }
    /**
     *  Posts a new user to the database
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String post(@PathParam("userId") String userId){

        return "hello world";
    }

}
