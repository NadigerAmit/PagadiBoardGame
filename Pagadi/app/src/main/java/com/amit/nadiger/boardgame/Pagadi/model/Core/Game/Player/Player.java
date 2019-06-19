package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player;

import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.NormalCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    //Properties .
    private static final String TAG = "Player";
    final private String mName;
    final private int mAge ;
    final private Board mBoard;
    final private RestingCell mHome;
    private ArrayList<Piece> mPiece = new ArrayList<Piece>(); // Map of cell no and Piece // This should be multi map .
    private ArrayList<Integer> mMoves = new ArrayList<Integer>();
    private int mTotalSteps = 0;

    // Methods
    public Player(String name,int age,
                  RestingCell cell ,ArrayList<Piece> pieces,Board board) {
        mName = name;
        mAge = age;
        mHome = cell;
        mPiece = pieces;
        mHome.InitializeWithResidents(pieces);
        mBoard = board;
        mTotalSteps = 0;
    }
    public String getName() { return mName;}
    public int getAge() { return mAge;}
    public RestingCell getHomeCell() { return mHome;}

    public ArrayList<Piece> getPieces() { return mPiece; }

    public void addMoves(int num) {
        if(num == 5 || num == 6 || num == 7 || num>8) {
            Log.e(TAG,"Invalid moves");
        }
        mMoves.add(num);
        mTotalSteps+=num;
    }

    public ArrayList<Integer> getMoves() {
        return mMoves;
    }

    private void resetMoves() {
        mMoves.clear(); // Clear all the moves once given to
        mTotalSteps = 0;
    }

    private Piece selectPieceToMove(int cellNo) {
        for(Piece piece :mPiece) {
            if(piece.getCurrentPosition() == cellNo) {
                return piece;
            }
        }
        return null;
    }

    public boolean doMove(int cellNo, int steps) {
        Piece piece = selectPieceToMove(cellNo);
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
                NormalCell nCell = (NormalCell)cell;
                nCell.addPieceToCell(piece);
                ret = true;
                break;
            case DESTINATION_CELL:
                Log.e(TAG,"Dont need to move from Destination cell !! "+cellNo);
                ret = false;
                break;
            default:
                ret = false;
                Log.e(TAG,"Invalid cell !! "+cellNo);

        }
//        mMoves.remove(steps); // Removed the move from list;
        if(ret) {
            piece.notifyUiPieceUpdate();
        }
     return ret;
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
    public ArrayList<Integer> getPossiableMoves(int pieced) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        Piece pieceInstance = mPiece.get(pieced);
        // rules.getPossiableMoves(pieceInstance.getCurrentPosition);
        return mMoves;
    }



}
