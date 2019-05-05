package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell;
import java.util.ArrayList;
import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.Pawn;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

abstract public class Cell {
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
    public boolean getisOccupied() {
        return mIsOccupied;
    }

    public Pawn getPawn() {return null;}

    public ArrayList<Pawn> getResidents() {return null;}

    public Constants.CELL_TYPE getCellType() {
        return mCellType;
    }

    public void setIsOccupied(boolean isOccupied) {
        mIsOccupied = isOccupied;
    }

}
