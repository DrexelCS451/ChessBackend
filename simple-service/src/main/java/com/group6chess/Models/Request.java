package com.group6chess.Models;

import javax.persistence.*;

/**
 * Created by Matt on 3/4/16.
 */
@Entity
@Table(name = "request")
public class Request {

    public static enum State {requested, accepted, denied}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;

    @Column(name = "player1", nullable = false)
    private int player1;

    @Column(name = "player2", nullable = true)
    private int player2;

    @Column(name = "player1Name", nullable = false)
    private String player1Name;

    @Column(name = "player2name", nullable = false)
    private String getPlayer2Name;

    @Column(name = "state", nullable = false)
    private State state;


    public Request() {

    }

    public Request(int player1, int player2, String player1Name, String getPlayer2Name) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Name = player1Name;
        this.getPlayer2Name = getPlayer2Name;
        this.state = State.requested;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public State getState() {
        return state;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getGetPlayer2Name() {
        return getPlayer2Name;
    }

    public void setGetPlayer2Name(String getPlayer2Name) {
        this.getPlayer2Name = getPlayer2Name;
    }

    public void setState(State state) {
        this.state = state;
    }
}
