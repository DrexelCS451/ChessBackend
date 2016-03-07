package com.example.Models;

import javax.persistence.*;

/**
 * Created by Matt on 3/4/16.
 */
@Entity
@Table(name = "request")
public class Request {

    @Id
    private int Id;

    @Column(name = "player1", nullable = false)
    private int player1;

    @Column(name = "player2", nullable = true)
    private int player2;

    @Column(name = "accepted", nullable = false)
    private boolean accepted;

    public Request() {
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Request(int player1, int player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.accepted = false;
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
}
