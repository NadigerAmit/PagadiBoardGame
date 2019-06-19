package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;

import android.util.Log;

import java.util.ArrayList;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class FinalDestinationCell extends Cell {
    private static final String TAG = "FinalDestinationCell";
    private ArrayList<Piece> mRIPWinners;
    public FinalDestinationCell(int cellNo) {
        super(cellNo, Constants.CELL_TYPE.DESTINATION_CELL);
        // TODO Auto-generated constructor stub
    }

    public void addToCell(Piece piece) {
        mRIPWinners.add(piece);
        setValue(this);
    }

    public void retrivePieceFromCell(Piece piece) {
        Log.w(TAG,"Can't be retrieved from final destination ");
    }

}
