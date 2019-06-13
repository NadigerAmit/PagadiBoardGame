package com.amit.nadiger.boardgame.Pagadi.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amit.nadiger.boardgame.Pagadi.R;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DummyBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DummyBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DummyBoardFragment extends Fragment {
    private static final String TAG = "DummyBoardFragment";
    private Context mContext;

    private RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    //private GridView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;


    public static DummyBoardFragment newInstance() {

        Bundle args = new Bundle();

        DummyBoardFragment fragment = new DummyBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dummy_fragment_board,container,false);
        // Inflate the layout for this fragment
        mContext = view.getContext();

        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(mContext,5);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);


      //  mAdapter = new CellAdapter(mContext);
        Game game = Game.getInstance(getGameRequest());
     //   ((CellAdapter) mAdapter).mapCells(game.getBoard().getCellList());
        mRecyclerView.setAdapter(mAdapter);

        Log.e(TAG,"onCreateView() called");
        return view;


    }

    public GameRequest getGameRequest() {
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
}
