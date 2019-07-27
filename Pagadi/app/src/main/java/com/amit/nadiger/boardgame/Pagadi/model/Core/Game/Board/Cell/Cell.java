package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.databinding.BaseObservable;
import android.util.Log;

import java.util.ArrayList;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

abstract public class Cell extends MutableLiveData<Cell> {
//abstract public class Cell {
    private static final String TAG = "Cell";
    final private int mCellNo;
    private boolean mIsOccupied ;
    private Constants.CELL_TYPE mCellType ;
    private ArrayList<Piece> mPieceList;
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

    public Constants.CELL_TYPE getCellType() {
        return mCellType;
    }

    public ArrayList<Piece> getAllPieceOfCell() {
        return null;
    }

    public final static boolean darkSquare(int x, int y) {
        return (x & 1) == (y & 1);
    }

    public void notifyCellChanged() {
        setValue(this);
    }

    public void setIsOccupied(boolean isOccupied) {
        Log.e(TAG," Before mIsOccupied = "+mIsOccupied);
        mIsOccupied = isOccupied;
        setValue(this);
        Log.e(TAG," After mIsOccupied = "+mIsOccupied);
        //notifyChange();
    }

}
