package com.amit.nadiger.boardgame.Pagadi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;

import androidx.annotation.NonNull;

public class PieceViewModel extends AndroidViewModel {

    MediatorLiveData<LiveData<Piece>> mediator  = new MediatorLiveData<>();

    /*
    MutableLiveData<ArrayList<Piece>> mPiece1 = new MediatorLiveData<>();
    MutableLiveData<ArrayList<Piece>> mPiece2 = new MediatorLiveData<>();
    MutableLiveData<ArrayList<Piece>> mPiece3 = new MediatorLiveData<>();
    MutableLiveData<ArrayList<Piece>> mPiece4 = new MediatorLiveData<>();
    */
    Game mGame;

    PieceViewModel(@NonNull Application AppContext) {
        super(AppContext);
    }

    public void init(GameRequest req) {
        mGame =  Game.getInstance(req);
        mediator = mGame.getMediator();
    }

    /*
    public MutableLiveData<ArrayList<Piece>> getAllPieceListOne() {
        return mPiece1;
    }

    public MutableLiveData<ArrayList<Piece>> getAllPieceListTwo() {
        return mPiece2;
    }

    public MutableLiveData<ArrayList<Piece>> getAllPieceListThree() {
        return mPiece3;
    }

    public MutableLiveData<ArrayList<Piece>> getAllPieceListFour() {
        return mPiece4;
    }
*/
    public MediatorLiveData<LiveData<Piece>> getMediator() {
        return mediator;
    }



}
