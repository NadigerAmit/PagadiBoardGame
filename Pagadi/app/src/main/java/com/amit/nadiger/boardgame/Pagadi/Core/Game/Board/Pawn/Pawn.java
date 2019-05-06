package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

abstract public class Pawn {
    private int mCurrentPosition;
    protected Constants.PAWNCOLOR mColor;
    private int mPawnId;
    final private int mHomeCellNo;

    public Pawn(int position,Constants.PAWNCOLOR color,int id) {
        mCurrentPosition = position;
        mHomeCellNo = position; // This is the initial position and its permanent home
        mColor = color;
        mPawnId = id;
    }

    abstract public Constants.PAWNCOLOR getColor() ;

    public int getHomeCellNo(int homeCellNo) {
        return mHomeCellNo;
    }

    public int getHomeCell() {
        return mHomeCellNo;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public int nextPossiablePosition(int steps) {
        return mCurrentPosition + steps ; // This is wrong :) ;
    }

    public void moveToPosition(int steps) {
        mCurrentPosition = mCurrentPosition + steps ; // This is wrong :) ;
    }


    public int getPawnId() { return mPawnId; }

    public void debugPrintPawn() {
        System.out.println(" PawnColor" +mColor);
        System.out.println(" PawnId" +mPawnId);
        System.out.println(" Caurrent Position" +mCurrentPosition);
        System.out.println(" mHomeCellNo" +mHomeCellNo);
    }
}
