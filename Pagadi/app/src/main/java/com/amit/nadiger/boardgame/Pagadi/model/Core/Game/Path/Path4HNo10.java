package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Path;

import java.util.HashMap;
import java.util.Map;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.INVALID_PIECE_MOVE;

public class Path4HNo10 extends Path {
    static private int mArray[] = {
            10,
            15,
            20,
            21,
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
            6,
            7,
            8,
            13,
            18,
            17,
            16,
            11,
            12
    };

    Map<Integer,Integer> mCellPathMap = new HashMap<Integer, Integer>();

    public Path4HNo10() {
        for(int i = 0;i<mArray.length;i++) {
            mCellPathMap.put(mArray[i],i);
        }

    }

    public int getNextPosition(Integer from, Integer steps) {
        int index = mCellPathMap.get(from);
        if(index+steps>mArray.length) return INVALID_PIECE_MOVE;
        return mArray[index+steps];
    }
}
