package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;

import java.util.ArrayList;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class FinalDestinationCell extends Cell {

    private ArrayList<Piece> mRIPWinners;
    public FinalDestinationCell(int cellNo) {
        super(cellNo, Constants.CELL_TYPE.DESTINATION_CELL);
        // TODO Auto-generated constructor stub
    }

    public ArrayList<Piece> getResidents() {
        return null;
        //return  new ArrayList(mResidentQ);
    }
}
