package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece;

import java.util.ArrayList;


abstract public class Piece {
    private int mCurrentPosition;
    protected int mPieceType;
    private int mPawnId;
    final private int mHomeCellNo;

    private static final String TAG = "Piece";

    public Piece(int position, int type, int id) {
        mCurrentPosition = position;
        mHomeCellNo = position; // This is the initial position and its permanent home
        mPieceType = type;
        mPawnId = id;
    }

    abstract public int getPieceType() ;

    public int getHomeCellNo(int homeCellNo) {
        return mHomeCellNo;
    }

    public int getHomeCell() {
        return mHomeCellNo;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public ArrayList<Integer> nextPossiablePositions() {
        ArrayList<Integer> possiablePositions = new ArrayList<Integer>();
        return possiablePositions ; // This is wrong :) ;
    }

    public void moveToPosition(int steps) {
        mCurrentPosition = mCurrentPosition + steps ; // This is wrong :) ;
        // get next position
        // Rules.getPosition();
    }


    public int getPawnId() { return mPawnId; }

    public void debugPrintPawn() {
        System.out.println(" PieceType" +mPieceType);
        System.out.println(" PawnId" +mPawnId);
        System.out.println(" Caurrent Position" +mCurrentPosition);
        System.out.println(" mHomeCellNo" +mHomeCellNo);
    }
}
