package com.amit.nadiger.boardgame.Pagadi.model.Core.Game;

import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player.Player;

import java.util.ArrayList;

public class BoardFSM {
    private static final String TAG = "BoardFSM";
    private Player mCurrentPlayer = null;
    private Integer mSelectedCellNo = null;
    private Piece mSelectedPiece;
    private Board mBoard = null;
    Integer mSteps = -1;

    enum BoardState {
        INIT,
        CELL_CLICKED,
        PIECE_SELECTED,
        VALID_DEST_SELECTED,
        INVALID_DEST_SELECTED,
        PLAYER_CHANGED
    };
    BoardState mBoardState = null;

    private static BoardFSM INSTANCE = null;
    private BoardFSM() {

    }

    public static BoardFSM getINSTANCE(Board board) {
        if(INSTANCE == null) {
            INSTANCE = new BoardFSM();
            INSTANCE.mBoardState = BoardState.INIT;
            INSTANCE.mBoard = board;
        }
        return INSTANCE;
    }

    public void setPlayer(Player currentPlayer) {
        if(mCurrentPlayer != currentPlayer) {
            mCurrentPlayer = currentPlayer;
            sendEventInternalCurrentPlayerChanged();
        }
    }

// *************************************************************************************************
    /*
       Event Functions
    */
    public void sendEventCellClicked(Integer cellNo) {
        executeEventCellClicked(cellNo);
    }
    private void sendInternalInvalidSourceCellSelected() {
        executeEventInvalidSourceCellClicked();
    }
    private void sendEventInternalPieceSelected() {
        executeEventPieceSelected();
    }
    private void sendEventInternalValidDestSelected() {
        executeEventValidDestSelected();
    }
    private void sendEventInternalPieceMoved() {
        executeEventPieceMoved();
    }
    private void sendEventInternalInvalidDestSelected() {
        executeEventInvalidDestSelected();
    }
    private void sendEventInternalCurrentPlayerChanged() { }
    private void sendEventInternalPieceUnSelected() {
        executeEventPieceUnselected();
    }


//******************************************************************************************************
 /*
       Execute Event Functions
 */
    private void executeEventCellClicked(Integer cellNo) {
        switch(mBoardState) {
            case INIT:
                Log.i(TAG,"BFSM INIT ->CELL_CLICKED");
                mBoardState = BoardState.CELL_CLICKED;
                entryActionSourceCellSelected(cellNo);
                break;
            case PIECE_SELECTED:
                Log.i(TAG,"BFSM PIECE_SELECTED ->CELL_CLICKED");
                mBoardState = BoardState.CELL_CLICKED;
                entryActionDestinationCellSelected(cellNo);
                break;
            case CELL_CLICKED:
            case VALID_DEST_SELECTED:
            case INVALID_DEST_SELECTED:
            case PLAYER_CHANGED:
                Log.e(TAG,"executeEventCellClicked event "+ mBoardState);
                break;
        }
    }

    private void executeEventInvalidSourceCellClicked() {
        switch(mBoardState) {
            case CELL_CLICKED:
                Log.i(TAG,"BFSM CELL_CLICKED -> INIT");
                mBoardState =  BoardState.INIT;
                entryActionInit();
            case INIT:
            case PIECE_SELECTED:
            case VALID_DEST_SELECTED:
            case INVALID_DEST_SELECTED:
            case PLAYER_CHANGED:
                Log.e(TAG,"Invalid src selected event "+ mBoardState);
        }
    }

    private  void executeEventPieceSelected() {
        switch(mBoardState) {
            case CELL_CLICKED:
                Log.i(TAG,"BFSM CELL_CLICKED -> PIECE_SELECTED");
                mBoardState = BoardState.PIECE_SELECTED;
                entryActionPieceSelected();
                break;
            case INIT:
            case PIECE_SELECTED:
            case VALID_DEST_SELECTED:
            case INVALID_DEST_SELECTED:
            case PLAYER_CHANGED:
                Log.e(TAG,"Invalid event state(PieceSelected) ="+ mBoardState);
                break;
        }
    }

    private  void executeEventValidDestSelected() {
        switch(mBoardState) {
            case CELL_CLICKED:
                Log.i(TAG,"BFSM CELL_CLICKED -> VALID_DEST_SELECTED");
                mBoardState =  BoardState.VALID_DEST_SELECTED;
                entryActionValidDestinationCellSelected();
            case INIT:
            case PIECE_SELECTED:
            case VALID_DEST_SELECTED:
            case INVALID_DEST_SELECTED:
            case PLAYER_CHANGED:
                Log.e(TAG,"Invalid src selected event "+ mBoardState);
        }
    }
    private  void executeEventPieceMoved() {
        switch(mBoardState) {
            case VALID_DEST_SELECTED:
                Log.i(TAG,"BFSM  VALID_DEST_SELECTED -> INIT");
                mBoardState =  BoardState.INIT;
                entryActionInit();
            case INIT:
            case CELL_CLICKED:
            case PIECE_SELECTED:
            case INVALID_DEST_SELECTED:
            case PLAYER_CHANGED:
                Log.e(TAG,"Invalid src selected event "+ mBoardState);
        }
    }

    private  void executeEventInvalidDestSelected() {
        switch(mBoardState) {
            case CELL_CLICKED:
            case VALID_DEST_SELECTED:
                Log.i(TAG,"BFSM CELL_CLICKED -> INVALID_DEST_SELECTED");
                mBoardState =  BoardState.INVALID_DEST_SELECTED;
                entryActionInValidDestinationCellSelected();
                break;
            case INIT:
            case PIECE_SELECTED:
            case INVALID_DEST_SELECTED:
            case PLAYER_CHANGED:
                Log.e(TAG,"Invalid src selected event "+ mBoardState);
        }
    }

    private  void executeEventPieceUnselected() {
        switch(mBoardState) {
            case CELL_CLICKED:
            case INVALID_DEST_SELECTED:
                Log.i(TAG,"BFSM CELL_CLICKED -> INIT");
                mBoardState =  BoardState.INIT;
                entryActionInit();
            case INIT:
            case PIECE_SELECTED:
            case VALID_DEST_SELECTED:
            case PLAYER_CHANGED:
                Log.e(TAG,"Invalid src selected event "+ mBoardState);
        }
    }

   //******************************************************************************************************
  /*
    Entry Action functions
  */
    private  void entryActionInit() {
        if(mCurrentPlayer == null) {
            Log.e(TAG,"Null currentPlayer not allowed in state INIT");
        }
        mSelectedCellNo = null;
        mSelectedPiece  = null;
        mSteps          = -1;
    }

    private  void entryActionPieceSelected() {
        // filter the moves based on the piece .
        ArrayList<Integer> moves = mCurrentPlayer.getPossibleMoves(mSelectedPiece);
        // show to user all possible moves for selected piece
        // send callback to UI via viewModel
        // wait for next cell click i.e destination cell
    }

    private void entryActionSourceCellSelected(Integer selectedCellNo) {
        mSelectedCellNo = selectedCellNo;
        mSelectedPiece = mCurrentPlayer.getPieceAtCell(selectedCellNo);
        if(mSelectedPiece == null) {
            sendInternalInvalidSourceCellSelected();
            return;
        }
        boolean isPieceMovePossible = mCurrentPlayer.isPieceMovePossible(mSelectedPiece); // this calculates steps required .
        if(isPieceMovePossible){
            sendEventInternalPieceSelected();
        } else {
            sendInternalInvalidSourceCellSelected();
        }
        return;
    }

    private void entryActionDestinationCellSelected(Integer cellNo) {
        mSelectedCellNo = cellNo;
         mSteps = mCurrentPlayer.getStepsForDestination(mSelectedPiece,cellNo);
        if(mSteps == -1) {
            sendEventInternalValidDestSelected();
        } else {
            sendEventInternalInvalidDestSelected();
        }
    }

    private void entryActionValidDestinationCellSelected() {
        boolean isPieceMoved = mCurrentPlayer.doMove(mSelectedPiece,mSteps);
        // Update the UI
        if(isPieceMoved) {
            sendEventInternalPieceMoved();
        } else {
            sendEventInternalInvalidDestSelected(); // handle this in
        }

    }

    private void entryActionInValidDestinationCellSelected() {
        boolean isSourceCellClickedAgain = false ;
        // if the selected cell is same as source cell
        if(isSourceCellClickedAgain) {
            sendEventInternalPieceUnSelected();
        }
    }
}
