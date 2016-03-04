package com.example.Models;

import java.util.List;

/**
 * Created by Matt on 2/11/16.
 *
 * Singleton Lobby object to list out everyone waiting in the lobby
 */
public class LobbySession{

    private LobbySession lobbyInstance;


    public LobbySession getCurrentLobby(){
        if(lobbyInstance.equals(null)) {
            return new LobbySession();
        }
        else
        {
            return lobbyInstance;
        }
    }

    protected LobbySession(){

    }

    public List<DBUser> listUsers(){
        //TODO create a new session then list out the DB users who are waiting.
    }

}