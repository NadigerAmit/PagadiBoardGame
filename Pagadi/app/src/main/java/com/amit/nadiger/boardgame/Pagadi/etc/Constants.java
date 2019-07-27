package com.amit.nadiger.boardgame.Pagadi.etc;

public final class Constants {
    public static final int ROW                 = 5;
    public static final int COL                 = 5;
    public static final int NUM_OF_RESIDENT     = 4;
    public static final int TOP_RESTING_CELL    = 2;  // Top Katti
    public static final int LEFT_RESTING_CELL   = 10; // Left katti
    public static final int BOTTOM_RESTING_CELL = 22; //Down
    public static final int RIGHT_RESTING_CELL  = 14;
    public static final int FINAL_DESTIONATION  = 12;

    public static final int EMPTY = 0;
    public static final int WKING  = 1;  // WhitePiece
    public static final int WQUEEN = 2;  // RedPiece
    public static final int BKING  = 3;  // BlackPiece
    public static final int BQUEEN = 4;  // GreenPiece
    public static final int INVALID_PIECE_MOVE = -1;
    public static final int EXTRA_MOVE_REQUIRED = -101;


    public enum CELL_TYPE {
        INVALID,
        RESTING_CELL,
        DESTINATION_CELL,
        NORMAL_CELL
    };

    public enum GAME_PLAYER_MODE {
        SINGLE_PLYER, // with computer
        TWO_PLYER,    // Currently on this is supported .
        THREE_PLYER,
        FOUR_PLYER
    };

    public enum DICE_TYPE {
        FOUR_PIECE,
        SIX_PIECE
    };

    // Error Codes
    public static final int  INVALID_GAMEID = 9999999;

}
