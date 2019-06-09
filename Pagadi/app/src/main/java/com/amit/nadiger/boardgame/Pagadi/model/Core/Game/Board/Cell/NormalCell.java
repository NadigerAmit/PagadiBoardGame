package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

import java.util.ArrayList;

public class NormalCell extends Cell {
    private Piece mRenter;

    public NormalCell(int cellNo) {
        super(cellNo, Constants.CELL_TYPE.NORMAL_CELL);
        mRenter = null;
    }
    public Piece getPiece() {return mRenter;}

    public ArrayList<Piece> getResidents() {
        return null;
        //return  new ArrayList(mResidentQ);
    }
}
