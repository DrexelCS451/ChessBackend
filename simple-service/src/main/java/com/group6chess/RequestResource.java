package com.group6chess;

import com.group6chess.Models.DBUser;
import com.group6chess.Models.Game;
import com.group6chess.Models.JsonStatus;
import com.group6chess.Models.Request;
import com.group6chess.util.HibernateUtil;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
                return gson.toJson(new JsonStatus("Fail","Player not requested"));
            }
            else {

                return gson.toJson(result).toString();
            }
        }catch (Exception e)
        {
                        session.close();
            return gson.toJson(new JsonStatus("Fail","Invalid Id"));
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

            return gson.toJson(new JsonStatus("Success","request sent"));

        }catch (NumberFormatException e) {
            return gson.toJson(new JsonStatus("Fail","invalid id"));
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
                return new Gson().toJson(new JsonStatus("Fail","request not found"));
            }
            else
            {
                if (Boolean.parseBoolean(accept) == true) {
                    //Create a game with the two players
                    final Game newGame = new Game(request.getPlayer1(), request.getPlayer2());
                    newGame.setState("WHITE_TURN");
                    newGame.setEncodedGameBoard(Game.INITIAL_GAME_BOARD);

                    request.setState(Request.State.accepted);

                    session.save(request);
                    session.save(newGame);
                    session.flush();
                    session.getTransaction().commit();

                    return gson.toJson(newGame);
                }
                else
                {
                    //The request was denied
                    request.setState(Request.State.denied);
                    session.save(request);
                    session.getTransaction().commit();
                    session.close();
                    return new Gson().toJson(new JsonStatus("Success","request denied"));
                }

            }

        }catch (NumberFormatException e)
        {
            return gson.toJson(new JsonStatus("Fail","invalid input"));
        }
    }

}
