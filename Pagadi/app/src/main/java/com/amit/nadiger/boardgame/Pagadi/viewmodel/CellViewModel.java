package com.amit.nadiger.boardgame.Pagadi.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.LinkedList;

import androidx.lifecycle.MediatorLiveData;
import androidx.annotation.NonNull;
import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.BoardFSM;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player.Player;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;

public class CellViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private static final String TAG = "CellViewModel";
    private Game  mGame;
    private Board mBoard;
    private BoardFSM mFsm = null;
    private LinkedList<Player> mPlayerList = new LinkedList<>();
    private MediatorLiveData<LiveData<Cell>> mMediatorCellLiveData = new MediatorLiveData<>();



    CellViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(GameRequest request) {
        mGame =  Game.getInstance(request);
        mBoard = mGame.getBoard();
        mMediatorCellLiveData = mBoard.getCellMediator();
        mPlayerList = mGame.getPlayerList();
        mFsm = BoardFSM.getINSTANCE(mBoard);
        mFsm.setPlayer(mPlayerList.get(0));
    }


    public  MediatorLiveData<LiveData<Cell>> getCellMediator() {
        return mMediatorCellLiveData;
    }

    /**************************************************************************/
    /* *********************  Wrapper functions ********************************/
    /**************************************************************************/

    public Constants.CELL_TYPE cellType(int square) {
         return mBoard.getCell(square).getCellType();
    }

    public ArrayList<Integer> getPieceTypesList(int square) {
        ArrayList<Integer> pieceTypeList = new ArrayList<>();
        if(mBoard.getCell(square).getAllPieceOfCell() == null) {
            return null;
        }
        ArrayList<Piece> pieceList = mBoard.getCell(square).getAllPieceOfCell();

        for (Piece piece : pieceList) {
        Integer pieceType = new Integer(piece.getPieceType());
            pieceTypeList.add(pieceType);
        }
        return pieceTypeList;
    }

    public void clicked(int cellNo) {
        mFsm.sendEventCellClicked(cellNo);
    }

    private boolean movePiece(Piece pieceToMove, Integer destCellNo) {
       return mGame.getCurrentPlayer().doMove(pieceToMove,destCellNo);
    }
    public void move() {
        Log.e(TAG,"Size of player "+mPlayerList.size());
      //  mPlayerList.get(1).doMove(22,3);
    }

    public ArrayList<Piece> getResidents(int square) {
        return mBoard.getCell(square).getAllPieceOfCell();
    }

    // For testing of View model purpose


    public void setOccupied(int cellNo , boolean flag) {
        mBoard.setIsOccupied(cellNo,flag);
      //  mBoard.getCell(22).getResidents().get(1).moveToPosition(1);
    }

    public void DebugPrintGame() {
        mGame.DebugPrintGame();
    }
}
