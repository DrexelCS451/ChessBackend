package com.group6chess;

/**
 * Created by Matt on 3/4/16.
 */
import com.group6chess.Models.DBUser;
import com.group6chess.Models.LobbyUser;
import com.group6chess.util.HibernateUtil;
import com.google.gson.JsonParser;
import org.hibernate.classic.Session;
import com.google.gson.Gson;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.Object;

import javax.persistence.Lob;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        try {
            JsonParser parser = new JsonParser();
            session.beginTransaction();
            Gson gson = new Gson();

            return gson.toJson(session.createCriteria(LobbyUser.class).list());
        }finally {
            session.close();
        }
    }
    /**
     *  Posts a new user to the database
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String post(@QueryParam("userId") String userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();
        try {
            JsonParser parser = new JsonParser();
            session.beginTransaction();
            DBUser user = (DBUser) session.get(DBUser.class, Integer.parseInt(userId));
            session.save(new LobbyUser(Integer.parseInt(userId), user.getUsername()));
            session.getTransaction().commit();
            return gson.toJson("Success: User added to lobby");

        }catch (Exception e){
            return gson.toJson("Fail: Invalid userId");
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@QueryParam("userId") String userId){
        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List result = session.createCriteria(LobbyUser.class)
                    .add( Restrictions.eq("userId" , Integer.parseInt(userId))).list();

            for(int i = 0; i < result.size(); i++){
                session.delete(result.get(i));
            }
            session.getTransaction().commit();

            session.close();
            return new Gson().toJson("Success: user deleted");
        }
        catch (Exception e){
            e.printStackTrace();
            return new Gson().toJson("Fail: User not found");
        }
    }


}
