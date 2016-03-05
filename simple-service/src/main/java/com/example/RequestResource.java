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
 * Created by Matt on 3/4/16.
 */
@Path("request")
public class RequestResource {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("id") String username) {


        return "hello world";
    }
    /**
     *  Posts a new user to the database
     */@POST
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String httpBody){
        return "hello world";
    }
}
