package com.group6chess;

import com.group6chess.Models.DBUser;
import com.group6chess.Models.Game;
import com.group6chess.Models.LobbyUser;
import com.group6chess.Models.Request;
import com.group6chess.util.HibernateUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import com.google.gson.Gson;
import sun.java2d.pipe.NullPipe;

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
                    .add(Restrictions.eq("player2", Integer.parseInt(userId)))
                    .add(Restrictions.eq("state", Request.State.requested)).list();
            if (result.isEmpty())
            {
                session.close();
                return gson.toJson("Fail: player not requested for match");
            }
            else {

                return gson.toJson(result).toString();
            }
        }catch (Exception e)
        {
                        session.close();
            return gson.toJson("Fail: invalid user id");
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
                    Integer.parseInt(opponentId),
                    ((DBUser) session.get(DBUser.class, Integer.parseInt(userid))).getUsername(),
                    ((DBUser) session.get(DBUser.class, Integer.parseInt(opponentId))).getUsername()
            );
            session.beginTransaction();

            session.save(request);
            session.getTransaction().commit();
            session.close();

            return gson.toJson("Success: request sent");

        }catch (NumberFormatException e) {
            return gson.toJson("Fail: invalid user id");
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String put(@QueryParam("requestId") String requestId, @QueryParam("accept") String accept){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();
        try {
            session.beginTransaction();
            Request request = (Request) session.get(Request.class, Integer.parseInt(requestId));
            if(request == null){
                //Its null soooo
                return new Gson().toJson("Fail: request not found");
            }
            else
            {
                if (Boolean.parseBoolean(accept) == true) {
                    //Create a game with the two players
                    final Game newGame = new Game(request.getPlayer1(), request.getPlayer2());

                    request.setState(Request.State.accepted);

                    session.save(request);
                    session.save(newGame);
                    session.flush();

                    Map<String, String> jsonThing = new HashMap<String, String>() {{
                        put("gameId", Integer.toString(newGame.getId()));
                        put("player1", Integer.toString(newGame.getPlayerOneId()));
                        put("player2", Integer.toString(newGame.getPlayerTwoId()));
                        put("state", newGame.getTurn().name());
                        put("board", newGame.getEncodedGameBoard());
                    }};

                    String test = gson.toJson(jsonThing);
                    session.getTransaction().commit();
                    return gson.toJson(jsonThing.toString());
                }
                else
                {
                    //The request was denied
                    request.setState(Request.State.denied);
                    session.save(request);
                    session.getTransaction().commit();
                    session.close();
                    return new Gson().toJson("Success: request denied");
                }

            }

        }catch (NumberFormatException e)
        {
            return gson.toJson("Fail: invalid input");
        }
    }

}
