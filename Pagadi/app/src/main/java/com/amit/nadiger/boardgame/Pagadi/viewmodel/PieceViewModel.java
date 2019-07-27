package com.amit.nadiger.boardgame.Pagadi.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;

import androidx.annotation.NonNull;

public class PieceViewModel extends AndroidViewModel {

    MediatorLiveData<LiveData<Piece>> mediator  = new MediatorLiveData<>();

    Game mGame;

    PieceViewModel(@NonNull Application AppContext) {
        super(AppContext);
    }

    public void init(GameRequest req) {
        mGame =  Game.getInstance(req);
        mediator = mGame.getPieceMediator();
    }

    public MediatorLiveData<LiveData<Piece>> getMediator() {
        return mediator;
    }



}
