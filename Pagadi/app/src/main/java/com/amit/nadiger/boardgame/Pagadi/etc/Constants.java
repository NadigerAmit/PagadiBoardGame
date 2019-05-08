package com.amit.nadiger.boardgame.Pagadi.etc;

public final class Constants {
    public static final int ROW                 = 5;
    public static final int COL                 = 5;
    public static final int NUM_OF_RESIDENT     = 4;
    public static final int TOP_RESTING_CELL    = 2;  // Top Katti
    public static final int LEFT_RESTING_CELL   = 10; // Left katti
    public static final int BOTTOM_RESTING_CELL = 22; //Down
    public static final int RIGHT_RESTING_CELL  = 14;

    public enum PAWNCOLOR{
        WHITE,
        BLACK,
        RED,
        GREEN
    };

    public enum CELL_TYPE {
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


    public enum ERROR_CODES {

    }

}
