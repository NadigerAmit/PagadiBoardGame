package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.INVALID_PIECE_MOVE;

public class Path4HNo22 extends Path {
    private static final String TAG = "Path4HNo22";
    static private Integer mArray[] = {
            22,
            23,
            24,
            19,
            14,
            9,
            4,
            3,
            2,
            1,
            0,
            5,
            10,
            15,
            20,
            21,
            16,
            11,
            06,
            07,
            8,
            13,
            18,
            17,
            12
    };

    Map<Integer,Integer> mCellPathMap = new HashMap<Integer, Integer>();

    public Path4HNo22() {
        for(int i = 0;i<mArray.length;i++) {
            mCellPathMap.put(mArray[i],i);
        }
    }

    public int getNextPosition(Integer from, Integer steps) {
        if(mCellPathMap == null) {
            Log.e(TAG,"mCellPathMap = null");
        } else {
            Log.e(TAG,"mCellPathMap != null");
        }

        int index = mCellPathMap.get(from);
        if(index+steps>mArray.length) return INVALID_PIECE_MOVE;
        return mArray[index+steps];
    }
}
