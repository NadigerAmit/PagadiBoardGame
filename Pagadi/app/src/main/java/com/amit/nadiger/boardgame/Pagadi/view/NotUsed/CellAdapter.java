package com.amit.nadiger.boardgame.Pagadi.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;


import java.util.List;
import java.util.ArrayList;


import com.amit.nadiger.boardgame.Pagadi.R;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.Cell;

public class CellAdapter extends  RecyclerView.Adapter<CellAdapter.CellHolder> {
    private static final String TAG = "CellAdapter";
    private Context mContext;
    private List<Cell> mCells = new ArrayList<Cell>();
    public CellAdapter(Context context) {
        Log.e(TAG,"CellAdapter con() ");
        mContext = context;
    }

    public  void mapCells(List<Cell> cells) {
        Log.e(TAG,"mapCells called() no cells = "+ cells.size());
        this.mCells = cells;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCells.size();
    }

    @NonNull
    @Override
    public CellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_item,parent,false);
        return new CellHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CellHolder holder, int position) {
        Cell currentCell = mCells.get(position);
        Log.e(TAG,"onBindViewHolder currentCellNo = "+currentCell.getCellNo());
        String chr = Character.toString((char)(currentCell.getCellNo()+65));
        chr+=chr+chr+chr;
        System.out.println(String.valueOf(0x265A));
       // Log.e(TAG,"onBindViewHolder currentCellNo = "+chr);
        holder.ButtonViewCellNo.setText(String.valueOf(chr));
    }

    class CellHolder extends RecyclerView.ViewHolder {
        private Button ButtonViewCellNo;
        public CellHolder(View itemView) {
            super(itemView);
            Log.e(TAG,"CellHolder called");
            ButtonViewCellNo = itemView.findViewById(R.id.cellNo);
        }

    }

}

