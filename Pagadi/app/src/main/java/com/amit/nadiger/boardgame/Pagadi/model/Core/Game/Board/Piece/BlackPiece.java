package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path.Path;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player.Player;

public class BlackPiece extends Piece {
    static private int id = 0;
    public BlackPiece(int position , Path path) {

        super(position, Constants.BKING, id++,path);
        if(id >Constants.NUM_OF_RESIDENT)
            System.out.println("Error id ="+id);
        // TODO Auto-generated constructor stub
    }

    // Return the total num of Pawns of this color
    public static int getInstanceCount() {return id;}

    @Override
    public int getPieceType() {
        // TODO Auto-generated method stub
        return Constants.BKING;
    }
}
