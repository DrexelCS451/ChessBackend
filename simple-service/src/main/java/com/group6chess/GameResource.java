package com.group6chess;

import com.group6chess.Models.Game;
import com.group6chess.util.HibernateUtil;
import org.hibernate.classic.Session;
import com.google.gson.Gson;
import org.hibernate.criterion.Restrictions;
import java.util.List;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Matt on 3/7/16.
 */

@Path("game")
public class GameResource {




    @GET
    public String get(@QueryParam("userId") String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();

        try {
            session.beginTransaction();
            List result = session.createCriteria(Game.class)
                    .add(Restrictions.or(Restrictions.eq("playerOneId", Integer.parseInt(id)), Restrictions.eq("playerTwoId", Integer.parseInt(id)))).list();
            if(!result.isEmpty())
            {
                Gson g = new Gson();
               return g.toJson(result.get(0));
            }
            else
            {
                return gson.toJson("Fail: Failed");
            }

        }catch (Exception e){
            return gson.toJson("Fail: Failed");
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String post(
            @QueryParam("gameId")     String gameId,
            @QueryParam("board")      String board,
            @QueryParam("boardState") String boardState)
    {

        System.out.println(gameId + "\n" + board + "\n" + boardState);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Game game = (Game) session.get(Game.class, Integer.parseInt(gameId));

        if (game == null)
        {
            //Bad game board
            return new Gson().toJson("Fail: bad board input");
        }
        else
        {
            //Got a good game board
            game.setEncodedGameBoard(board);
            if (!boardState.equals(null)) {
                if      (game.getTurn() == Game.State.player1) game.setTurn(Game.State.player2);
                else if (game.getTurn() == Game.State.player2) game.setTurn(Game.State.player1);
            }



            session.save(game);
            return new Gson().toJson("Success: game board updated");
        }



    }



}
