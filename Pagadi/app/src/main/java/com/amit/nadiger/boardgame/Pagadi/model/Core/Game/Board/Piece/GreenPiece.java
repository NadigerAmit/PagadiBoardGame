package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class GreenPiece extends Piece {
    static private int id = 0;
    public GreenPiece(int position) {

        super(position, Constants.BQUEEN, id++);
        if(id >Constants.NUM_OF_RESIDENT)
            System.out.println("Error id ="+id);
        // TODO Auto-generated constructor stub
    }

    // Return the total num of Pawns of this color
    public static int getInstanceCount() {return id;}

    @Override
    public int getPieceType() {
        // TODO Auto-generated method stub
        return Constants.BQUEEN;
    }

}
