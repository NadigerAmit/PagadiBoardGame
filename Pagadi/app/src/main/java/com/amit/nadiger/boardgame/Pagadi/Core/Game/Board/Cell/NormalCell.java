package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.Pawn;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class NormalCell extends Cell {
    private Pawn mRenter;

    public NormalCell(int cellNo) {
        super(cellNo, Constants.CELL_TYPE.NORMAL_CELL);
        mRenter = null;
    }
    public Pawn getPawn() {return mRenter;}
}
