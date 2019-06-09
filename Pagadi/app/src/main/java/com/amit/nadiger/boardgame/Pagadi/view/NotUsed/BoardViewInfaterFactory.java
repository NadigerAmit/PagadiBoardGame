package com.amit.nadiger.boardgame.Pagadi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;

public class BoardViewInfaterFactory implements LayoutInflater.Factory2 {
    private static final String TAG = "BoardViewInfaterFactory";
    private Game mCustomVal;

    public void setCustomValue(Game game) {
        mCustomVal = game;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.e(TAG, "BoardViewInfaterFactory onCreateView() called for custome inf- ");
        if (name.equals("com.amit.nadiger.boardgame.Pagadi")) {
            Log.e(TAG, "Cratoing custom view");
            return new UiBoard(context, attrs, mCustomVal);
        }
        return null;
    }

  //  @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.e(TAG, "BoardViewInfaterFactory onCreateView() called for custome inf Factory2");
        if (name.equals("com.amit.nadiger.boardgame.Pagadi")) {
            Log.e(TAG, "Cratoing custom view");
            return new UiBoard(context, attrs, mCustomVal);
        } else {
            Log.e(TAG, "BoardViewInfaterFactory onCreateView() called in else part Factory2");
            return new UiBoard(context, attrs, mCustomVal);
        }

    }
}

