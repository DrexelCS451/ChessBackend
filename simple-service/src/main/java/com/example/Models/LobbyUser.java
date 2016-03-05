package com.example.Models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Matt on 2/11/16.
 *
 * Singleton LobbyUser object to list out everyone waiting in the lobby
 */
@Entity
@Table(name = "lobby")
public class LobbyUser {

    @Id
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    public LobbyUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}