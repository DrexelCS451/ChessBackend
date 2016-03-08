package com.group6chess.Models;

import javax.persistence.*;

/**
 * Created by Matt on 2/11/16.
 *
 * Singleton LobbyUser object to list out everyone waiting in the lobby
 */
@Entity
@Table(name = "lobby")
public class LobbyUser {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "username", nullable = false)
    private String username;

    public LobbyUser() {

    }

    public LobbyUser(int userId, String username) {
        this.userId = userId;
        this.username = username;
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

    @Override
    public String toString() {
        return Integer.toString(userId);
    }
}