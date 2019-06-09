package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player;

import android.util.Log;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private static final String TAG = "Player";
    final private String mName;
    final private int mAge ;
    final private RestingCell mHome;
    private Map<Integer, Piece> mPawns = null; // Map of cell no and Actual cell it self
    private ArrayList<Integer> mMoves = new ArrayList<Integer>();
    public Player(String name,int age,
                  RestingCell cell ,ArrayList<Piece> pieces) {
        mName = name;
        mAge = age;
        mHome = cell;
        mPawns = new HashMap<Integer, Piece>();
        mHome.InitializeWithResidents(pieces);
    }
    public String getName() { return mName;}
    public int getAge() { return mAge;}
    public RestingCell getHomeCell() { return mHome;}

    public ArrayList<Piece> getPawns() {
        return new ArrayList<Piece>(mPawns.values());
    }

    public void addMoves(int num) {
        if(num == 5 || num == 6 || num == 7 || num>8) {
            Log.e(TAG,"Invalid moves");
        }
        mMoves.add(num);
    }

    public ArrayList<Integer> getMoves() {
        return mMoves;
    }

    private void resetMoves() {
        mMoves.clear(); // Clear all the moves once given to
    }

    public ArrayList<Integer> consumeMoves() {
        ArrayList<Integer> moves = mMoves;
        resetMoves();
        return moves;
    }

    // This API is for highlighting the possiable cells for perticuler pawn.
    public ArrayList<Integer> getPossiableMoves(int pawnId) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        Piece pieceInstance = mPawns.get(Integer.valueOf(pawnId));
        // rules.getPossiableMoves(pieceInstance.getCurrentPosition);
        return mMoves;
    }



}
