package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell;

import java.util.ArrayList;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.Pawn;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class FinalDestinationCell extends Cell {

    private ArrayList<Pawn> mRIPWinners;
    public FinalDestinationCell(int cellNo) {
        super(cellNo, Constants.CELL_TYPE.DESTINATION_CELL);
        // TODO Auto-generated constructor stub
    }
}
