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
    public String get(@QueryParam("userId") String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new Gson();

        try {
            session.beginTransaction();
            List result = session.createCriteria(Game.class)
                    .add(Restrictions.or(Restrictions.eq("playerOneId", id), Restrictions.eq("playerTwoId", id))).list();
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

}
