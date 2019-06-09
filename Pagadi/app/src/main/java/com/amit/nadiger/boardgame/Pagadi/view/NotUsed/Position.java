package com.amit.nadiger.boardgame.Pagadi.view;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.view.NotUsed.Piece;

import java.util.ArrayList;

public class Position {
    private int[] squares;
    private ArrayList<Cell> mCells;

    public boolean whiteMove;


    /** Initialize board to empty position. */
    private Position() {
        squares = new int[25];
        for (int i = 0; i < 25; i++) {
            if (i == 2) squares[i] = Piece.WKING;
            else if (i == 22) squares[i] = Piece.BQUEEN;
        }
    }

    public Position(ArrayList<Cell> cells) {
        mCells = new ArrayList<Cell>(cells);
        squares = new int[cells.size()];

        for (int i = 0; i < 25; i++) {
            if (mCells.get(i).getCellType() == Constants.CELL_TYPE.NORMAL_CELL) {
                //squares[i] = mCells.get(i).getPiece().getPieceType();
            }
            else if (i == 22) squares[i] = Piece.BQUEEN;
        }

    }

    public Position(Position other) {
        squares = new int[25];
        System.arraycopy(other.squares, 0, squares, 0, 25);
    }





    /** Return index in squares[] vector corresponding to (x,y). */
    public final static int getSquare(int x, int y) {
        //return y * 8 + x;
        return y * 5 + x;
    }

    /** Return index in squares[] vector corresponding to (x,y). */
    public final Cell getCell(int x, int y) {
        int square =  y * 8 + x;
        return mCells.get(square);
         //y * 5 + x;
    }

    /** Return x position (file) corresponding to a square. */
    public final static int getX(int square) {
        return square & 7;
    }
    /** Return y position (rank) corresponding to a square. */
    public final static int getY(int square) {
        return square >> 3;
    }
    /** Return true if (x,y) is a dark square. */
    public final static boolean darkSquare(int x, int y) {
        return (x & 1) == (y & 1);
    }
    public final int getPiece(int square) {
        return squares[square];
    }



    /**
     * Count number of pieces of a certain type.
     */
    public final int nPieces(int pType) {
        int ret = 0;
        for (int sq = 0; sq < 25; sq++) {
            if (squares[sq] == pType)
                ret++;
        }
        return ret;
    }

    /** Apply a move to the current position. */
    public final void makeMove(Move move) {
        // Rules
    }


    public final void unMakeMove(Move move) {
        // Rules
    }

    /** Useful for debugging. */
    public final String toString() {
        return "Dummy Output";
    }
}
