package com.amit.nadiger.boardgame.Pagadi.model;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

import java.io.Serializable;

public class GameRequest implements Serializable{
    private Constants.GAME_PLAYER_MODE mPlayer;
    // Player1 properties
    private Integer mGameId = -1;
    private String mPlayer1Name;
    private int mPlayer1Age;
    private int mPlayer1HomeCellNum;
    private int mPlayer1Type;

    // Player2 properties
    private String mPlayer2Name;
    private int mPlayer2Age;
    private int mPlayer2HomeCellNum;
    private int mPlayer2Type;

    // Player3 properties
    private String mPlayer3Name;
    private int mPlayer3Age;
    private int mPlayer3HomeCellNum;
    private int mPlayer3Type;

    // Player4 properties
    private String mPlayer4Name;
    private int mPlayer4Age;
    private int mPlayer4HomeCellNum;
    private int mPlayer4Type;

    public void setGamePlayerMode(Constants.GAME_PLAYER_MODE playerMode) {
        mPlayer = playerMode;
    }

    public Constants.GAME_PLAYER_MODE getGamePlayerMode() {
        return mPlayer ;
    }

    public void setGameId(Integer gameId) {
        mGameId = gameId;
    }

    public Integer getGameId() {
        return mGameId ;
    }

    public void setPlayer1Name(String player1Name) {
        mPlayer1Name = player1Name;
    }

    public String getPlayer1Name() {
        return mPlayer1Name;
    }

    public void setPlayer1Age(int player1Age) {
        mPlayer1Age = player1Age;
    }

    public int getPlayer1Age() {
        return mPlayer1Age;
    }

    public void setPlayer1HomeCellNum(int player1HomeCellNum) {
        mPlayer1HomeCellNum = player1HomeCellNum;
    }

    public int getPlayer1HomeCellNum() {
        return mPlayer1HomeCellNum;
    }

    public void setPlayer1Type(int player1Type) {
        mPlayer1Type = player1Type;
    }

    public int getPlayer1Type() {
        return mPlayer1Type;
    }

    public void setPlayer2Name(String player2Name) {
        mPlayer2Name = player2Name;
    }

    public String getPlayer2Name() {
        return mPlayer2Name;
    }

    public void setPlayer2Age(int player2Age) {
        mPlayer2Age = player2Age;
    }

    public int getPlayer2Age() {
        return mPlayer2Age;
    }

    public void setPlayer2HomeCellNum(int player2HomeCellNum) {
        mPlayer2HomeCellNum = player2HomeCellNum;
    }

    public int getPlayer2HomeCellNum() {
        return mPlayer2HomeCellNum;
    }

    public void setPlayer2Type(int player2Type) {
        mPlayer2Type = player2Type;
    }

    public int getPlayer2Type() {
        return mPlayer2Type;
    }

    public void setPlayer3Name(String player3Name) {
        mPlayer3Name = player3Name;
    }

    public String getPlayer3Name() {
        return mPlayer3Name;
    }

    public void setPlayer3Age(int player3Age) {
        mPlayer3Age = player3Age;
    }

    public int getPlayer3Age() {
        return mPlayer3Age;
    }

    public void setPlayer3HomeCellNum(int player3HomeCellNum) {
        mPlayer3HomeCellNum = player3HomeCellNum;
    }

    public int getPlayer3HomeCellNum() {
        return mPlayer3HomeCellNum;
    }

    public void setPlayer3Type(int player3Type) {
        mPlayer3Type = player3Type;
    }

    public int getPlayer3Type() {
        return mPlayer3Type;
    }

    public void setPlayer4Name(String player4Name) {
        mPlayer4Name = player4Name;
    }

    public String getPlayer4Name() {
        return mPlayer4Name;
    }

    public void setPlayer4Age(int player4Age) {
        mPlayer4Age = player4Age;
    }

    public int getPlayer4Age() {
        return mPlayer4Age;
    }

    public void setPlayer4HomeCellNum(int player4HomeCellNum) {
        mPlayer4HomeCellNum = player4HomeCellNum;
    }

    public int getPlayer4HomeCellNum() {
        return mPlayer4HomeCellNum;
    }

    public void setPlayer4Type(int player4Type) {
        mPlayer4Type = player4Type;
    }

    public int getPlayer4Type() {
        return mPlayer4Type;
    }

}

