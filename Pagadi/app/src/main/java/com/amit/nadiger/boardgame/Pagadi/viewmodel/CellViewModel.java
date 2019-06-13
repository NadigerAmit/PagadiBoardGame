package com.amit.nadiger.boardgame.Pagadi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.ArrayList;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.CustomMutableLiveData;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;

import java.util.ArrayList;

public class CellViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private static final String TAG = "CellViewModel";
    private Game  mGame;
    private Board mBoard;
    private LiveData<ArrayList<Cell>> mCells ;
   //private MutableLiveData< ArrayList<MutableLiveData<Cell>>>  mCells;

    //private CustomMutableLiveData<Cell> mCells ;


    CellViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(GameRequest request) {
        mGame =  Game.getInstance(request);
        mBoard = mGame.getBoard();
        mCells =  mBoard.getAllCells();
    }

    public LiveData<ArrayList<Cell>> getAllCells() { return mCells; }
   // public MutableLiveData< ArrayList<MutableLiveData<Cell>>> getAllCells() { return mCells; }


    /**************************************************************************/
    // *********************  Wrapper functions ********************************
    /**************************************************************************/

    public Constants.CELL_TYPE cellType(int square) {
         return mBoard.getCell(square).getCellType();
    }

    public int getPieceTypes(int square) {
        if(mBoard.getCell(square).getPiece() == null) {
            return Constants.EMPTY;
        }
        return mBoard.getCell(square).getPiece().getPieceType();
    }

    public ArrayList<Piece> getResidents(int square) {
        return mBoard.getCell(square).getResidents();
    }

    // For testing of Viewmodel purpose
    public void setOccupied(int cellNo , boolean flag) {
        //mBoard.setIsOccupied(cellNo,flag);

        mBoard.getCell(22).getResidents().get(1).moveToPosition(1);
    }

    public void DebugPrintGame() {
        mGame.DebugPrintGame();
    }
}
