package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;

import java.util.ArrayList;


public class RobotPlayer extends Player {
    public RobotPlayer(String name, int age,
                       RestingCell cell , ArrayList<Piece> pieces) {
        super(name,age,cell, pieces);
    }
}
