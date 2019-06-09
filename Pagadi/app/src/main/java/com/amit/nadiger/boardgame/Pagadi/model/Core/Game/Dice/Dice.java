package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Dice;

import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Dice.RandomNumGenerator.RandomNumGeneratorUtility;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class Dice {
    private static final String TAG = "Dice";
    private Constants.DICE_TYPE mType ;
    private int mArray[] ;
    public Dice(Constants.DICE_TYPE type) {
        mType = type;
        switch(mType) {
            case FOUR_PIECE:
                mArray = new int[5];
                mArray[0] = 1;
                mArray[1] = 2;
                mArray[2] = 3;
                mArray[3] = 4;
                mArray[4] = 8;
                break;
            case SIX_PIECE:
                mArray = new int[7];
                mArray[0] = 1;
                mArray[1] = 2;
                mArray[2] = 3;
                mArray[3] = 4;
                mArray[4] = 5;
                mArray[5] = 6;
                mArray[6] = 12;
                break;
            default:
                Log.e(TAG,"Not supported "+mType);
                break;
        }

    }
    public int getChance() {
        return RandomNumGeneratorUtility.getRandomElement(mArray);
    }
}
