package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

import java.util.ArrayList;

public class PawnFactory {

    static public Pawn getPawn(Constants.PAWNCOLOR pawnColor ,int restingCellNo) {
        if(validateRestingCellNo(restingCellNo) == false) {
            System.out.println("Resting cell is not valid or number of residents alrready"
                    + " reached max of "+Constants.NUM_OF_RESIDENT);
            return null;
        }
        switch(pawnColor) {
            case WHITE:
                if(WhitePawn.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new WhitePawn(restingCellNo);  // hardCoaded to Top up seat
            case BLACK:
                if(BlackPawn.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new BlackPawn(restingCellNo);
            case RED:
                if(RedPawn.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new RedPawn(restingCellNo);
            case GREEN:
                if(GreenPawn.getInstanceCount()>=
                        Constants.NUM_OF_RESIDENT) {
                    return null;
                }
                return new GreenPawn(restingCellNo);
            default:
                return null;
        }
    }

    static private boolean validateRestingCellNo(int restingCellNo) {
        Board mboard = Board.getInstance();
        Cell cell = mboard.getCell(restingCellNo);
        if(cell.getCellType() != Constants.CELL_TYPE.RESTING_CELL) {
            return false; // the the cell is other than resting cell , return false;
        }
        ArrayList<Pawn> residentPawn = cell.getResidents();
        if(residentPawn == null) return true; // if no pawns are allocated for the 1st time , return true
        if(residentPawn.size()< Constants.NUM_OF_RESIDENT) return true;
        // if residents are less than max num, return true
        return false;
    }
}
