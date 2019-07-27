package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player;

import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.FinalDestinationCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.NormalCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.EXTRA_MOVE_REQUIRED;
import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.FINAL_DESTIONATION;
import static com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.NormalCell.*;

public class Player {
    //Properties .
    private static final String TAG = "Player";
    final private String mName;
    final private int mAge ;
    final private Board mBoard;
    final private RestingCell mHome;
    private ArrayList<Piece> mPiece = new ArrayList<Piece>(); // Map of cell no and Piece // This should be multi map .
    private ArrayList<Integer> mMoves = new ArrayList<>();
    private Map<Piece,Integer> mBackupPieceMap = new HashMap<Piece,Integer>();
    private int mTotalSteps = 0;

    // Methods
    public Player(String name,int age,
                  RestingCell cell ,ArrayList<Piece> pieces,Board board) {
        mName = name;
        mAge = age;
        mHome = cell;
        mPiece = pieces;
        mHome.InitializeWithResidents(pieces);
        // Associate piece with Player.

        mBoard = board;
        mTotalSteps = 0;
    }
    public String getName() { return mName;}
    public int getAge() { return mAge;}
    public RestingCell getHomeCell() { return mHome;}

    public ArrayList<Piece> getPieces() { return mPiece; }

    public Integer getStepsForDestination(Piece piece,Integer cellNo) {
        Integer steps = piece.getSteps(cellNo);
        for(Integer move:mMoves) {
            if(move == steps) return steps;
        }
        return -1;
    }

    public void addMoves(int num) {
        if(num == 5 || num == 6 || num == 7 || num>8) {
            Log.e(TAG,"Invalid moves");
        }
        mMoves.add(num);
        mTotalSteps+=num;
    }

    private void associateOwnerToPiece() {
        for(Piece piece:mPiece) {
            piece.setOwner(this);
        }
    }

    public ArrayList<Integer> getMoves() {return mMoves;}
    private void resetMoves() {
        mMoves.clear(); // Clear all the moves once given to
        mTotalSteps = 0;
    }

    public Piece getPieceAtCell(int cellNo) {
        for(Piece piece :mPiece) {
            if(piece.getCurrentPosition() == cellNo) {
                return piece;
            }
        }
        return null;
    }

    public boolean isPieceMovePossible(Piece piece) {
        if(piece.getOwner() != this) return false;
        Integer steps = piece.getSteps(FINAL_DESTIONATION);
        return validateStepsWithMovesAvailable(steps);
    }

    private boolean validateStepsWithMovesAvailable(Integer steps) {
        if(mTotalSteps<steps) return false;
        // This is complex problem , need to solve carefully.
        //TODO: Jugga need to taken care
        return true;
    }
/*
    public boolean doMove(Piece pieceToMove, Integer destCellNo) {
        if(pieceToMove == null || destCellNo == null) return false;
        Integer srcCellNo = pieceToMove.getCurrentPosition();
        boolean ret = false;
        Integer steps  =  pieceToMove.getSteps(destCellNo);
        if(steps >25) {
            return false;
        }
        return doMoveInSteps(pieceToMove,steps);
    }
*/
    public boolean doMove(Piece pieceToMove, Integer steps) {
        if(pieceToMove == null || steps == -1) return false;
        if(steps >25) {
            return false;
        }
        // backup the piece and current location in for
        mBackupPieceMap.put(pieceToMove,pieceToMove.getCurrentPosition());
        return doMoveInSteps(pieceToMove,steps);
    }
    private boolean doMoveInSteps(Piece piece, int steps) {
        if(piece == null) return false;
        boolean ret = false;
        ret = piece.moveToPosition(steps);
        if(!ret) {
            Log.w(TAG,"Piece.MoveTo position failed steps "+steps);
            return ret;
        }
        Cell cell = mBoard.getCell(piece.getCurrentPosition());
        Log.w(TAG,"next cellNo "+piece.getCurrentPosition());
        switch(cell.getCellType()) {
            case RESTING_CELL:
                RestingCell rCell = (RestingCell)cell;
                if(cell.getCellNo() == piece.getHomeCell()) {
                    rCell.pushToResidents(piece);
                } else {
                    rCell.pushToImmegrints(piece);
                }
                ret = true;
                break;
            case NORMAL_CELL:
                // TODO:check if it is internal cells, if internal , then 2 piece of same type can sit in to same cell
                // TODO: For internal cells its more complicated , handle all scenarios
                // TODO : if external cell , only piece can stay in one cell .
                ret = handlePieceMomentToNormalCell((NormalCell)cell,piece);
                break;
            case DESTINATION_CELL:
               // Log.e(TAG,"Dont need to move from Destination cell !! "+cellNo);
                FinalDestinationCell FdCell = (FinalDestinationCell) cell;
                Integer numOfWinners = FdCell.addToCell(piece);
                if(numOfWinners == Constants.NUM_OF_RESIDENT) {
                    Log.e(TAG,piece.getPieceType()+ " WON the game ");
                }
                ret = true;
                break;
            default:
                ret = false;
               // Log.e(TAG,"Invalid cell !! "+cellNo);

        }

        if(ret) {
            mMoves.remove(steps); // Removed the move from list;
            mTotalSteps-=steps;
            if(mMoves.size()>1) {
                // still moves are yet to be consumed.
            }
            piece.notifyUiPieceUpdate();
        } else {
            // restore the Piece to origional place
        }
     return ret;
    }
    private boolean handlePieceMomentToNormalCell(NormalCell cell, Piece piece) {
        PIECE_MOVE_RET ret = cell.addPieceToNormalCell(piece);
        boolean isPieceMoved = false;
        switch(ret) {
            case PIECE_MOVED_NORMALLY:
                isPieceMoved = true;
                break;
            case PIECE_MOVED_BY_BEATING:
                mMoves.add(EXTRA_MOVE_REQUIRED);
                isPieceMoved = true;
                break;
            case PIECE_MOVED_BY_PAIRING:
                isPieceMoved = true;
                break;
            case PIECE_NOT_MOVED_SIBBLING_PRESENT:
                break;
            case PIECE_NOT_MOVED_UNKNOWN_REASON:
                break;
        }
        return isPieceMoved;
    }

    public ArrayList<Integer> consumeMoves() {
        ArrayList<Integer> moves = mMoves;
        for(Integer steps: moves) {
            //attemptMove();
        }
        // Make the move in Piece and current Cell.
        resetMoves();
        return moves;
    }

    // This API is for highlighting the possiable cells for perticuler pawn.
    public ArrayList<Integer> getPossibleMoves(Piece piece) {
        Integer currentPoistion = piece.getCurrentPosition();
        // rules.getPossibleMoves(pieceInstance.getCurrentPosition);
        return mMoves;
    }

    public boolean win(){
        return false;
    }



}
