package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;

public class PathFactory {
    private static final String TAG = "PieceFactory";
    static public Path getPath(int restingCellNo) {
        Path path = null;
        switch(restingCellNo) {
            case 02:
                path = new Path4HNo02();
                break;
            case 10:
                path = new Path4HNo10();
                break;
            case 22:
                path = new Path4HNo22();
                break;
            case 14:
                path = new Path4HNo14();
                break;
            default:
                break;
        }
        return path;
    }
}
