package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;

import android.util.Log;
import android.widget.Toast;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

public class NormalCell extends Cell {
        private static final String TAG = "NormalCell";
    HashSet<Integer> mExternalNormalCellsSet = new HashSet();
    private static Integer mExternalNormalCells[] ={
            0,
            1,
            3,
            4,
            5,
            9,
            15,
            19,
            20,
            21,
            23,
            24,
            6,7,8,13,18,17,16,11  // This line should be removed for level 2

    };
    public static enum PIECE_MOVE_RET {
        PIECE_MOVED_NORMALLY,
        PIECE_MOVED_BY_BEATING,
        PIECE_NOT_MOVED_SIBBLING_PRESENT,
        PIECE_MOVED_IN_EXISTING_PAIR,
        PIECE_MOVED_BY_PAIRING,
        PIECE_NOT_MOVED_UNKNOWN_REASON,

    };
    private ArrayList<Piece> mPiceList = new ArrayList<>();

    public NormalCell(int cellNo) {
        super(cellNo, Constants.CELL_TYPE.NORMAL_CELL);
        mExternalNormalCellsSet.addAll(Arrays.asList(mExternalNormalCells));
    }

    public ArrayList<Piece> getAllPieceOfCell() {
        return mPiceList;
    }

    public PIECE_MOVE_RET addPieceToNormalCell(Piece piece) {
        PIECE_MOVE_RET ret = PIECE_MOVE_RET.PIECE_MOVED_NORMALLY;
        if(mPiceList.isEmpty()) {
            mPiceList.add(piece);
            ret =  PIECE_MOVE_RET.PIECE_MOVED_NORMALLY;
        } else if(mPiceList.size()>1) {
            if(mExternalNormalCellsSet.contains(this.getCellNo())) {
                // This case should never happen as in external normal cells more than 2 piece cant sit.
                Log.e(TAG,"Piece not moved as illegal mob of piece is sitting in CellNo "+this.getCellNo());
                ret = PIECE_MOVE_RET.PIECE_NOT_MOVED_UNKNOWN_REASON;
            } else {  // This is internal cell
                mPiceList.add(piece); // Handle the case of jugga here
                ret = PIECE_MOVE_RET.PIECE_MOVED_IN_EXISTING_PAIR;
            }
        } else if(mPiceList.size() == 1) {
            Piece currentPiece = mPiceList.get(0);
            if(currentPiece.getPieceType() == piece.getPieceType()) {
                if(mExternalNormalCellsSet.contains(this.getCellNo())){
                    ret = PIECE_MOVE_RET.PIECE_NOT_MOVED_SIBBLING_PRESENT;
                } else {
                    mPiceList.add(piece);
                    // Make pair , Jugga
                    ret = PIECE_MOVE_RET.PIECE_MOVED_BY_PAIRING;
                }
            } else {
                // Beat the other piece type and put your piece
                //  currentPiece.gobackToHome();
                mPiceList.remove(currentPiece);
                currentPiece.getOwner().doMove(currentPiece,currentPiece.getSteps(currentPiece.getHomeCellNo())); // resetting back to home.
                mPiceList.add(piece);
                ret = PIECE_MOVE_RET.PIECE_MOVED_BY_BEATING;
            }
        }
        if(ret == PIECE_MOVE_RET.PIECE_MOVED_BY_BEATING ||
            ret == PIECE_MOVE_RET.PIECE_MOVED_NORMALLY ||
            ret == PIECE_MOVE_RET.PIECE_MOVED_IN_EXISTING_PAIR ||
            ret == PIECE_MOVE_RET.PIECE_MOVED_BY_PAIRING) {
            // do something .
        }
        // TODO : should return true if there is killing of other type of piece , else false.

        return ret;
    }


    public void retrivePieceFromCell(Piece piece) {
        mPiceList.remove(piece);
        setValue(this);
    }

}
