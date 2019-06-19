package com.amit.nadiger.boardgame.Pagadi.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;
import com.amit.nadiger.boardgame.Pagadi.viewmodel.CellViewModel;
import com.amit.nadiger.boardgame.Pagadi.viewmodel.PieceViewModel;

import androidx.annotation.Nullable;

public class BoardFragment extends Fragment {
    private static final String TAG = "BoardFragment";
    private Context mContext;
    // class member variable to save the X,Y coordinates
    private float[] mLastTouchDownXY = new float[2];
    UiBoard mBoard;
    private LinearLayout mLinearLayout;
    private View mView;
    static private Game mGame = null;
    private LinearLayout homelayout;
    private CellViewModel mCellViewModel = null;
    private PieceViewModel mPieceViewModel = null;

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
        //mCellViewModel.
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
        mCellViewModel = ViewModelProviders.of(this).get(CellViewModel.class);
        mPieceViewModel = ViewModelProviders.of(this).get(PieceViewModel.class);
        // we should not create the CellViewModel instance with new , as it will create new view model each time activity/Fragment is re-created.
        // instead we should get it from android system i.e viewModel providers , so that viewModel knows to which activity or view it is attached to
        // when activity or fragment it is attached to is destroyed , viewModel can remove itself to avoid memory leaks.
        mBoard = new UiBoard(container.getContext(),attrs);
        mCellViewModel.init(getGameRequest());
        mBoard.setViewModel(mCellViewModel);

        mCellViewModel.getCellMediator().observe(this, new Observer<LiveData<Cell>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable LiveData<Cell> cellLiveData) {
                Log.e(TAG,"cell No= "+ cellLiveData.getValue().getCellNo());
                Log.e(TAG,"cellType  = "+ cellLiveData.getValue().getCellType());

            }
        });

        mPieceViewModel.init(getGameRequest());
        mPieceViewModel.getMediator().observe(this, new Observer<LiveData<Piece>>() {
                    @Override
                    public void onChanged(@android.support.annotation.Nullable LiveData<Piece> pieceLiveData) {
                        Log.e(TAG,"Pice changed Home cell = "+ pieceLiveData.getValue().getHomeCell());
                        Log.e(TAG,"Pice changed Type  = "+ pieceLiveData.getValue().getPieceType());
                        Log.e(TAG,"Pice changed Type  = "+ pieceLiveData.getValue().getPieceId());
                        reDrawBoard();
                    }
                });

        mBoard.setClickable(true);
        // the purpose of the touch listener is just to store the touch X,Y coordinates
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // save the X,Y coordinates
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mLastTouchDownXY[0] = event.getX();
                    mLastTouchDownXY[1] = event.getY();
                }
                // let the touch event pass on to whoever needs it
                return false;
            }
        };

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // retrieve the stored coordinates
                float x = mLastTouchDownXY[0];
                float y = mLastTouchDownXY[1];
                int cellNo = 1;

                // use the coordinates for whatever
                Log.e("TAG", "onLongClick: x = " + x + ", y = " + y);
                cellNo  = mBoard.coOrdinateToSquare((int) x,(int) y);
                mCellViewModel.clicked(cellNo);
                Log.e(TAG,"Cell no = "+cellNo);

            }
        };

     //   board.setGame(getgameInstance());
        mBoard.setOnTouchListener(touchListener);
        mBoard.setOnClickListener(clickListener);
        return mBoard;
    }

    private void reDrawBoard() {
        Log.e(TAG,"reDrawBoard is called ");
        mCellViewModel.DebugPrintGame();
        mBoard.invalidate();
    }

    private GameRequest getGameRequest() {
        GameRequest req = new GameRequest();
        req.setGameId(-1);
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
