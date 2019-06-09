package com.amit.nadiger.boardgame.Pagadi.etc;

public final class Utility {
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

    /** Return index in squares[] vector corresponding to (x,y). */
    public final static int getSquare(int x, int y) {
        //return y * 8 + x;
        return y * 5 + x;
    }

}
