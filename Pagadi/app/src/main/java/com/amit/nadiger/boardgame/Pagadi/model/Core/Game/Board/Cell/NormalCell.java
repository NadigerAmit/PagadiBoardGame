package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

import java.util.ArrayList;

public class NormalCell extends Cell {
    private ArrayList<Piece> mPiceList = new ArrayList<>();

    public NormalCell(int cellNo) {
        super(cellNo, Constants.CELL_TYPE.NORMAL_CELL);
    }

    public ArrayList<Piece> getAllPieceOfCell() {
        return mPiceList;
    }

    public void addPieceToCell(Piece piece) {
        mPiceList.add(piece);
        setValue(this);
    }

    public void retrivePieceFromCell(Piece piece) {
        mPiceList.remove(piece);
        setValue(this);
    }

}
