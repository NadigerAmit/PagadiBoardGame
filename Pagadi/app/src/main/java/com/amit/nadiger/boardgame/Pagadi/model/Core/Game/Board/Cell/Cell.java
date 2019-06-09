package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;
import android.util.Log;

import java.util.ArrayList;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

abstract public class Cell {
    private static final String TAG = "Cell";
    final private int mCellNo;
    private boolean mIsOccupied ;
    private Constants.CELL_TYPE mCellType ;
    protected  Cell(int cellNo, Constants.CELL_TYPE CELLTYPE) {
        mCellNo = cellNo;
        mIsOccupied = false;
        mCellType = CELLTYPE;
    }

    public int getCellNo() {
        return mCellNo;
    }
    public boolean isOccupied() {
        return mIsOccupied;
    }

    public Piece getPiece() {return null;}

    public ArrayList<Piece> getResidents() {return null;}

    public Constants.CELL_TYPE getCellType() {
        return mCellType;
    }

    public final static boolean darkSquare(int x, int y) {
        return (x & 1) == (y & 1);
    }

    public void setIsOccupied(boolean isOccupied) {
        Log.e(TAG," Before mIsOccupied = "+mIsOccupied);
        mIsOccupied = isOccupied;
        Log.e(TAG," After mIsOccupied = "+mIsOccupied);
    }

}
