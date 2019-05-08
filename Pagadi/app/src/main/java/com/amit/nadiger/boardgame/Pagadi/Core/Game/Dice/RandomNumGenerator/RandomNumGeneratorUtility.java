package com.amit.nadiger.boardgame.Pagadi.Core.Game.Dice.RandomNumGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumGeneratorUtility {
    static public int getRandomElement(int Array[]) {
        List<Integer> mList = new ArrayList<>();
        for(int i = 0;i<Array.length;i++) {
            mList.add(i);
        }
        Random rand = new Random();
        return mList.get(rand.nextInt(mList.size()));
    }
}
