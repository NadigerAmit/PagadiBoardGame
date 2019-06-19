package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece;

import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path.Path;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path.PathFactory;

import java.util.ArrayList;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.*;

public class PieceFactory {
    private static final String TAG = "PieceFactory";

    static public Piece getPiece(int pieceType, int restingCellNo) {
        if(validateRestingCellNo(restingCellNo) == false) {
            Log.e(TAG,"Resting cell is not valid or number of residents already"
                    + " reached max of "+Constants.NUM_OF_RESIDENT);
            return null;
        }
        // Creating the Path for each pawn .
        Path path = PathFactory.getPath(restingCellNo);
        if(path == null) {
            Log.e(TAG,"Path is null for CellNo = "+restingCellNo);
            return null;
        }
        switch(pieceType) {
            case WKING:
                if(WhitePiece.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new WhitePiece(restingCellNo,path);  // hardCoaded to Top up seat
            case BKING:
                if(BlackPiece.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new BlackPiece(restingCellNo,path);
            case WQUEEN:
                if(RedPiece.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new RedPiece(restingCellNo,path);
            case BQUEEN:
                if(GreenPiece.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new GreenPiece(restingCellNo,path);
            default:
                return null;
        }
    }

    static private boolean validateRestingCellNo(int restingCellNo) {
        Board mboard = Board.getInstance();
        if(mboard.isRestingCell(restingCellNo) == false) return false;
        RestingCell cell = (RestingCell) mboard.getCell(restingCellNo);
        if(cell.getCellType() != Constants.CELL_TYPE.RESTING_CELL) {
            return false; // the the cell is other than resting cell , return false;
        }
        ArrayList<Piece> residentPiece = cell.getResidents();
        if(residentPiece == null) return true; // if no pawns are allocated for the 1st time , return true
        if(residentPiece.size()< Constants.NUM_OF_RESIDENT) return true;
        // if residents are less than max num, return true
        return false;
    }
}
