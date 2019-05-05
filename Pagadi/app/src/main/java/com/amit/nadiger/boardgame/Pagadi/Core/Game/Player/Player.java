package com.amit.nadiger.boardgame.Pagadi.Core.Game.Player;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.Pawn;

import java.util.ArrayList;

public class Player {
    final private String mName;
    final private int mAge ;
    final private RestingCell mHome;
    private  ArrayList<Pawn> mPawns ;
    public Player(String name,int age,
                  RestingCell cell ,ArrayList<Pawn> pawns) {
        mName = name;
        mAge = age;
        mHome = cell;
        mPawns = pawns;
    }
}
