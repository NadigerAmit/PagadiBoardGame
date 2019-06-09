package com.amit.nadiger.boardgame.Pagadi.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amit.nadiger.boardgame.Pagadi.R;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.etc.Utility;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;
import com.amit.nadiger.boardgame.Pagadi.viewmodel.BoardViewModel;

import java.util.ArrayList;

import androidx.annotation.Nullable;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class BoardFragment extends Fragment {
    private static final String TAG = "BoardFragment";
    private Context mContext;
    UiBoard mBoard;
    private LinearLayout mLinearLayout;
    private View mView;
    static private Game mGame = null;
    private LinearLayout homelayout;
    private BoardViewModel mBoardViewModel = null;

    public static BoardFragment newInstance() {
        Log.e(TAG, "BoardFragment newInstance ");
        Bundle args = new Bundle();
        BoardFragment fragment = new BoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "BoardFragment onCreate() ");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "BoardFragment onActivityCreated() ");
        //mBoardViewModel.
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.e(TAG, "BoardFragment onCreateView() ");
        LayoutInflater layoutInflater = inflater.cloneInContext(inflater.getContext());
        BoardViewInfaterFactory factory = new BoardViewInfaterFactory();
        factory.setCustomValue(getgameInstance());
        layoutInflater.setFactory2(factory);
        Log.e(TAG, "BoardFragment onCreateView() setFactory done! ");
        View view = layoutInflater.inflate(R.layout.fragment_board,container,false);

        Log.e(TAG, "BoardFragment onCreateView() after Inflation  ");
        mContext = view.getContext();
   //     UiBoard board = (UiBoard) view;
        mLinearLayout = (LinearLayout) view.findViewById(R.id.homeLayout);
      //  mView = (View) view.findViewById(R.id.view);


     //   board.setGame(game);
     //   mLinearLayout.addView(new UiBoard(getActivity(),null,game));
        Log.e(TAG,"onCreateView is called ");
        view.setBackgroundColor(Color.GRAY);

        return view;
    }
*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AttributeSet attrs = null;
        mBoardViewModel = ViewModelProviders.of(this).get(BoardViewModel.class);
        // we should not create the BoardViewModel instance with new , as it will create new view model each time activity/Fragment is re-created.
        // instead we should get it from android system i.e viewModel providers , so that viewModel knows to which activity or view it is attached to
        // when activity or fragment it is attached to is destroyed , viewModel can remove itself to avoid memory leaks.
        mBoard = new UiBoard(container.getContext(),attrs);
        mBoardViewModel.init(getGameRequest());
        mBoard.setViewModel(mBoardViewModel);
        mBoardViewModel.getAllCells().observe(this, new Observer<ArrayList<Cell>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Cell> cells) {
                // update the Board and redraw the board
                //Toast.makeText(MainActivity.this,"OmShreeGaneshayaNamah Onchanged",Toast.LENGTH_SHORT).show();

                reDrawBoard();

            }
        });
        mBoard.setClickable(true);
        mBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                Log.e(TAG,"View is clicked x = "+location[0] + " y "+location[1]);
               // board.getSquare((int)v.getX(),(int)v.getY());
               // int cellNo = mBoardViewModel.get(location[0],location[1]).getCellNo();
                int cellNo = 1;
                mBoardViewModel.setOccupied(cellNo,true);
                cellNo  = Utility.getSquare(location[0],location[1]);
                Log.e(TAG,"Cell no = "+cellNo);
            }
        });
     //   board.setGame(getgameInstance());
        return mBoard;
    }

    private void reDrawBoard() {
        Log.e(TAG,"reDrawBoard is called ");
        mBoardViewModel.DebugPrintGame();
        mBoard.invalidate();
    }

    private GameRequest getGameRequest() {
        GameRequest req = new GameRequest();
        req.setGamePlayerMode(Constants.GAME_PLAYER_MODE.TWO_PLYER);
        req.setPlayer1Age(37);
        req.setPlayer1Name("Jai Shree Ram");
        req.setPlayer1Type(Constants.WKING);
        req.setPlayer1HomeCellNum(2);

        req.setPlayer2Age(35);
        req.setPlayer2Name("Jai Bajrang Bali");
        req.setPlayer2Type(Constants.BKING);
        req.setPlayer2HomeCellNum(22);
        return req;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e(TAG,"onViewCreated is called ");
    }

}
