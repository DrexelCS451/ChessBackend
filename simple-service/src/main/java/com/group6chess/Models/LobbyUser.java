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

    private DBUser user;

    public LobbyUser() {
    }

    public LobbyUser(int userId) {
        this.userId = userId;
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