package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path.Path;

import java.util.ArrayList;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.INVALID_PIECE_MOVE;


abstract public class Piece extends MutableLiveData {
    private int mCurrentPosition;
    protected int mPieceType;
    private int mPawnId;
    final private int mHomeCellNo;
    final private Path  mPath;

    private static final String TAG = "Piece";

    public Piece(int position, int type, int id,Path path) {
        mCurrentPosition = position;
        mHomeCellNo = position; // This is the initial position and its permanent home
        mPieceType = type;
        mPawnId = id;
        mPath = path;
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

    public boolean moveToPosition(int steps) {
        Log.e(TAG,"moveToPosition " + steps );

//        mCurrentPosition = mCurrentPosition ; // This is wrong :) ;
        int nextPosition = mPath.getNextPosition(mCurrentPosition,steps);
        if(nextPosition == INVALID_PIECE_MOVE) {
            Log.e(TAG,steps+" steps are more than required steps for FD");
            return false;
        }
        mCurrentPosition = nextPosition;
        // get next position
        // Rules.getPosition();
        return true;
    }

    public void notifyUiPieceUpdate() {
        this.setValue(this);
    }


    public int getPieceId() { return mPawnId; }

    public void debugPrintPawn() {
        System.out.println(" PieceType" +mPieceType);
        System.out.println(" PawnId" +mPawnId);
        System.out.println(" Caurrent Position" +mCurrentPosition);
        System.out.println(" mHomeCellNo" +mHomeCellNo);
    }
}
