package com.group6chess;

import com.group6chess.Models.Game;
import com.group6chess.Models.LobbyUser;
import com.group6chess.Models.Request;
import com.group6chess.util.HibernateUtil;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String get(@QueryParam("userId") String userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();
        try {
            session.beginTransaction();
            List result = session.createCriteria(Request.class)
                    .add(Restrictions.eq("player2", Integer.parseInt(userId))).list();
            if (result.isEmpty())
            {
                session.close();
                return gson.toJson("Fail: player not requested for match");
            }
            else {
                return gson.toJson(result).toString();
            }
        }catch (NumberFormatException e)
        {
                        session.close();
            return gson.toJson("Fail: invalid user id");
        }
        finally
        {
//            session.close();
//            HibernateUtil.getSessionFactory().close();
        }

    }
    /**
     *  Posts a new user to the database
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String post(
            @QueryParam("userId") String userid,
            @QueryParam("opponentId") String opponentId) {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();
        try {
            Request request = new Request(
                    Integer.parseInt(userid),
                    Integer.parseInt(opponentId)
            );
            session.beginTransaction();

            session.save(request);
            session.getTransaction().commit();
            session.close();

            return gson.toJson("Success: test");

        }catch (NumberFormatException e) {
            return gson.toJson("Fail: invalid user id");
        }finally {
//            session.close();
//            HibernateUtil.getSessionFactory().close();
        }


    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String put(@QueryParam("userId") String userid){
        System.out.println("test");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();
        try {
            session.beginTransaction();
            List result = session.createCriteria(Request.class)
                    .add(Restrictions.eq("player2", Integer.parseInt(userid))).list();
            if (result.isEmpty())
            {
                return gson.toJson("Failure: user has no requests");
            }
            else
            {
                //Create a game with the two
                Request request = (Request) result.get(0);
                final Game newGame = new Game(request.getPlayer1() , request.getPlayer2());

                request.setState(Request.State.accepted);

                session.save(request);
                session.save(newGame);
                session.flush();

                Map<String,String> jsonThing = new HashMap<String, String>() {{
                    put("gameId"  , Integer.toString(newGame.getId()));
                    put("player1" , Integer.toString(newGame.getPlayerOneId()));
                    put("player2" , Integer.toString(newGame.getPlayerTwoId()));
                    put("state"   , newGame.getTurn().name());
                    put("board"   , newGame.getEncodedGameBoard());
                }};

                String test = gson.toJson(jsonThing);
                session.getTransaction().commit();
                return gson.toJson(jsonThing.toString());

            }

        }catch (NumberFormatException e)
        {
            return gson.toJson("Fail: invalid input");
        }
    }

}
