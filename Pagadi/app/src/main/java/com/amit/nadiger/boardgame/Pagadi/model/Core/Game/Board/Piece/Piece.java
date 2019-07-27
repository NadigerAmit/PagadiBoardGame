package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece;

import androidx.lifecycle.MutableLiveData;
import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path.Path;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player.Player;

import java.util.ArrayList;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.INVALID_PIECE_MOVE;


abstract public class Piece extends MutableLiveData {
    private int mCurrentPosition;
    protected int mPieceType;
    private int mPawnId;
    final private int mHomeCellNo;
    final private Path  mPath;
    private Player mOwner = null;

    private static final String TAG = "Piece";

    public Piece(int position, int type, int id,Path path) {
        mCurrentPosition = position;
        mHomeCellNo = position; // This is the initial position and its permanent home
        mPieceType = type;
        mPawnId = id;
        mPath = path;
    }

    abstract public int getPieceType() ;

    public void setOwner(Player owner) {
        if(mOwner == null) {
            mOwner = owner;
        } else {
            Log.e(TAG,"Owner should not be changed");
        }
    }

    public Player getOwner() {
        return mOwner;
    }


    public int getHomeCellNo() {
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

    public Integer getSteps(Integer destCellNo) {
        return mPath.getSteps(mCurrentPosition,destCellNo);
    }
    public boolean moveToPosition(int steps) {
        Log.e(TAG,"moveToPosition " + steps );

        int nextPosition = mPath.getSteps(mCurrentPosition,steps);
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
        Log.e(TAG,"notifyUiPieceUpdate called");
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
