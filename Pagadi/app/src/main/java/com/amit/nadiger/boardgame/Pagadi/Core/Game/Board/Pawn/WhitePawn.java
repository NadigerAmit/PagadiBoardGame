package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class WhitePawn extends Pawn {
    static private int id = 0;
    public WhitePawn(int position) {

        super(position, Constants.PAWNCOLOR.WHITE, id++);
        if(id >=Constants.NUM_OF_RESIDENT)
            System.out.println("Error id ="+id);
        // TODO Auto-generated constructor stub
    }

    // Return the total num of Pawns of this color
    public static int getInstanceCount() {return id;}

    @Override
    public Constants.PAWNCOLOR getColor() {
        // TODO Auto-generated method stub
        return Constants.PAWNCOLOR.WHITE;
    }
}
