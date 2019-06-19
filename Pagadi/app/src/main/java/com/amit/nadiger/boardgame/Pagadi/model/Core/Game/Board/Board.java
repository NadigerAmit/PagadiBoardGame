package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.amit.nadiger.boardgame.Pagadi.etc.Utility;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.FinalDestinationCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.NormalCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Board {
    static private Board _singleton = null;
   private MediatorLiveData<LiveData<Cell>> mCellMediator = new MediatorLiveData<>();

    private ArrayList<Cell> cellList = new ArrayList<>();
    private Map <Integer,Cell> mCellMap = new HashMap<Integer,Cell>(); // Map of cell no and Actual cell it self
    private Cell mArrOfCells[][];
    private int mTotalNumOfCells = 0;

    public static Board getInstance() {
        if (_singleton == null) {
            _singleton = new Board();
        }
        return _singleton;
    }

    public MediatorLiveData<LiveData<Cell>> getCellMediator() {
        return mCellMediator;
    }

    public boolean isRestingCell(int cellNo) {
        if(cellNo != Constants.BOTTOM_RESTING_CELL &&
                cellNo != Constants.RIGHT_RESTING_CELL &&
                cellNo != Constants.TOP_RESTING_CELL &&
                cellNo != Constants.LEFT_RESTING_CELL) {
            return false;
        }
        return true;
    }

    public Board() {  // I know should be private for it be singleton.
        int count = 0;

        mArrOfCells = new Cell[Constants.ROW][Constants.COL];
        for(int i = 0;i<Constants.ROW;i++) {
            for(int j = 0;j<Constants.COL;j++) {
                if((i == 0 && j == 2 )||  // Resting and final destination seats are pre-decided.
                        (i == 2 && j == 0) ||
                        (i == 4 && j == 2) ||
                        (i == 2 && j == 4)) {
                    mArrOfCells[i][j] = new RestingCell(count);
                } else if(i == 2 && j == 2) {
                    mArrOfCells[i][j] = new FinalDestinationCell(count);
                } else {
                    mArrOfCells[i][j] = new NormalCell(count);
                }
                mCellMap.put(count,mArrOfCells[i][j]);  // Insert it in to map
                mCellMediator.addSource(mArrOfCells[i][j], new Observer<Cell>() {
                    @Override
                    public void onChanged(@Nullable Cell cell) {
                        mCellMediator.setValue(cell);
                    }
                });
                count++;
            }
        }
        mTotalNumOfCells = count;

    }

    final public Cell getCell(int x, int y) {
        return mCellMap.get(Utility.getSquare(x,y));
    }

    public void setIsOccupied(int cellNo, boolean isOccupied) {
        Cell cell = mCellMap.get(cellNo);
        cell.setIsOccupied(isOccupied);
    }


    public Cell getCell(int cellNo) { return mCellMap.get(cellNo); }

    public int getCellCount() {return mTotalNumOfCells;}

    public void printBoard() {
        for(int i= 0;i<Constants.ROW;i++) {
            for(int j = 0; j< Constants.COL; j++) {
                if(mArrOfCells[i][j] instanceof RestingCell) {
                    System.out.println( "=> is resting cell"+mArrOfCells[i][j].getCellNo());
                } else if(mArrOfCells[i][j] instanceof FinalDestinationCell) {
                    System.out.println( "=> is FinalDestinationCell cell"+mArrOfCells[i][j].getCellNo());
                } else {
                    System.out.println( "=> is Normal Cell cell"+mArrOfCells[i][j].getCellNo());
                }
                //System.out.println(mArr[i][j].getCellNo());
            }
        }
    }

    public void printCellMap() {
        for(Object entryObj :mCellMap.entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            Object key = entry.getKey();
            Cell value = (Cell) entry.getValue();
            System.out.println("Key ="+key+" Value = "+value.getCellType());
            if(value instanceof RestingCell) {
                ((RestingCell) value).debugPrintResidents();
                ((RestingCell) value).debugImmegrants();
            }
        }
    }
}

