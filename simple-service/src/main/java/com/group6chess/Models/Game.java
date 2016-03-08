package com.group6chess.Models;

import javax.persistence.*;

/**
 * Created by Matt on 3/5/16.
 */
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected int id;

    @Column(name = "player1")
    protected int playerOneId;

    @Column(name = "player2")
    protected int playerTwoId;

    @Column(name = "gameBoard")
    protected String encodedGameBoard;

    @Column(name = "state")
    protected String state;

    public final static String INITIAL_GAME_BOARD =
            "0,0,BLACK_ROOK\n" +
            "0,1,BLACK_PAWN\n" +
            "0,2,EMPTY\n" +
            "0,3,EMPTY\n" +
            "0,4,EMPTY\n" +
            "0,5,EMPTY\n" +
            "0,6,WHITE_PAWN\n" +
            "0,7,WHITE_ROOK\n" +
            "1,0,BLACK_KNIGHT\n" +
            "1,1,BLACK_PAWN\n" +
            "1,2,EMPTY\n" +
            "1,3,EMPTY\n" +
            "1,4,EMPTY\n" +
            "1,5,EMPTY\n" +
            "1,6,WHITE_PAWN\n" +
            "1,7,WHITE_KNIGHT\n" +
            "2,0,BLACK_BISHOP\n" +
            "2,1,BLACK_PAWN\n" +
            "2,2,EMPTY\n" +
            "2,3,EMPTY\n" +
            "2,4,EMPTY\n" +
            "2,5,EMPTY\n" +
            "2,6,WHITE_PAWN\n" +
            "2,7,WHITE_BISHOP\n" +
            "3,0,BLACK_QUEEN\n" +
            "3,1,BLACK_PAWN\n" +
            "3,2,EMPTY\n" +
            "3,3,EMPTY\n" +
            "3,4,EMPTY\n" +
            "3,5,EMPTY\n" +
            "3,6,WHITE_PAWN\n" +
            "3,7,WHITE_QUEEN\n" +
            "4,0,BLACK_KING\n" +
            "4,1,BLACK_PAWN\n" +
            "4,2,EMPTY\n" +
            "4,3,EMPTY\n" +
            "4,4,EMPTY\n" +
            "4,5,EMPTY\n" +
            "4,6,WHITE_PAWN\n" +
            "4,7,WHITE_KING\n" +
            "5,0,BLACK_BISHOP\n" +
            "5,1,BLACK_PAWN\n" +
            "5,2,EMPTY\n" +
            "5,3,EMPTY\n" +
            "5,4,EMPTY\n" +
            "5,5,EMPTY\n" +
            "5,6,WHITE_PAWN\n" +
            "5,7,WHITE_BISHOP\n" +
            "6,0,BLACK_KNIGHT\n" +
            "6,1,BLACK_PAWN\n" +
            "6,2,EMPTY\n" +
            "6,3,EMPTY\n" +
            "6,4,EMPTY\n" +
            "6,5,EMPTY\n" +
            "6,6,WHITE_PAWN\n" +
            "6,7,WHITE_KNIGHT\n" +
            "7,0,BLACK_ROOK\n" +
            "7,1,BLACK_PAWN\n" +
            "7,2,EMPTY\n" +
            "7,3,EMPTY\n" +
            "7,4,EMPTY\n" +
            "7,5,EMPTY\n" +
            "7,6,WHITE_PAWN\n" +
            "7,7,WHITE_ROOK";

    public Game(int playerOneId, int playerTwoId) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.encodedGameBoard = INITIAL_GAME_BOARD;
        this.state = "WHITE_TURN";
    }

    public Game() {

    }

    public String getState() {
        return state;
    }

    public void setState(String turn) {
        this.state = turn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerOneId() {
        return playerOneId;
    }

    public void setPlayerOneId(int playerOneId) {
        this.playerOneId = playerOneId;
    }

    public int getPlayerTwoId() {
        return playerTwoId;
    }

    public void setPlayerTwoId(int playerTwoId) {
        this.playerTwoId = playerTwoId;
    }

    public String getEncodedGameBoard() {
        return encodedGameBoard;
    }

    public void setEncodedGameBoard(String encodedGameBoard) {
        this.encodedGameBoard = encodedGameBoard;
    }
}
