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
       // return y * 5 + x;
        return transformCoOrdinateSquareToCells(y * 5 + x);
    }

    // This is function is to convert to below table
    /*****************
     0 -20
     1 -21
     2 -22
     3 -23
     4 -24
     5 -16
     6- 17
     7 -18
     8 -19
     9 -20
     10-10
     11-11
     12-12
     13-13
     14-14
     15-05
     16-06
     17-07
     18-08
     19-9
     20-0
     21-1
     22-2
     23-3
     24-4
     **************/
    public static int transformCoOrdinateSquareToCells(int sq) {
        int factor = 0;
        if(sq >= 0 && sq <= 4) {
            factor = 20;
        } else if(sq>=5 && sq <=9) {
            factor = 10;
        } else if(sq>=15 && sq <=19) {
            factor = -10;
        } else if(sq >= 20 && sq <= 24) {
            factor = -20;
        }
        sq +=factor;
        return sq;
    }

}
