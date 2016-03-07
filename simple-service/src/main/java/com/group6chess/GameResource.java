package com.group6chess;

import com.group6chess.Models.Game;
import com.group6chess.Models.LobbyUser;
import com.group6chess.util.HibernateUtil;
import com.google.gson.JsonParser;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import com.google.gson.Gson;
import org.hibernate.criterion.Restrictions;
import java.util.List;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Matt on 3/7/16.
 */

@Path("game")
public class GameResource {


    @GET
    public Game get(@QueryParam("gameId") String game){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();

        try {
            session.beginTransaction();
            List result = session.createCriteria(Game.class)
                    .add(Restrictions.eq("id",game)).list();
            
            return (Game) result.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

}
