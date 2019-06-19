package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class RestingCell extends Cell{
    private static final String TAG = "RestingCell";
    private Queue<Piece> mResidentQ = new LinkedList<>();
    private ArrayList<Piece> mImmegrintsList = new ArrayList<Piece>();
    private int mResidentPieceType = -1;
    public RestingCell(int cellNo) {
        super(cellNo,Constants.CELL_TYPE.RESTING_CELL);
        // TODO Auto-generated constructor stub
    }

    public void InitializeWithResidents(ArrayList<Piece> residents) {
        if(residents.size()<= 0) {
            Log.w(TAG,"RestingCell: InitializeWithResidents residents.size()<= 0 ");
            return;
        }
        mResidentPieceType = residents.get(0).getPieceType();
        for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
            Log.i(TAG,residents.get(i).getHomeCell()+" Adding Residents "+residents.get(i).getPieceId());
            mResidentQ.add(residents.get(i)); // Adding in Residents Q
        }
    }

    public ArrayList<Piece> getAllPieceOfCell() {
        // Combine both Immegrints and residents and send a list.
        ArrayList<Piece> overAllList = new ArrayList<>(mImmegrintsList);
        overAllList.addAll(mResidentQ);
        return overAllList;
    }


    public ArrayList<Piece> getResidents() {
        return  new ArrayList(mResidentQ);
    }

    public int getNumOfResidents() {
        return mResidentQ.size();
    }

    public void  pushToResidents(Piece piece) {  // return ERROR code
        if(mResidentQ.size()>Constants.NUM_OF_RESIDENT) {
            Log.e(TAG,"mResidentQ.size()>Constants.NUM_OF_RESIDENT");
            return ;
        }
        if(mResidentQ.contains(piece)) {
            Log.e(TAG,"Pice present in ResQ  "+piece.getPieceId());
            return ;
        }
        mResidentQ.add(piece);
        setValue(this);
    }

    public Piece popFromResidents() {  // return ERROR code
        if(mResidentQ.isEmpty()) return null;
        Piece piece = mResidentQ.poll(); // pops and removes from the list
        setValue(this); // TODO: this is required ?
        return piece;
    }

    public void  pushToImmegrints(Piece piece) {  // return ERROR code
        if(mImmegrintsList.size()>Constants.NUM_OF_RESIDENT*3) {
            Log.w(TAG,piece.getPieceId()+ " Error : Reached Max capacity , This impossiable !!!! ");
            return ;
        }
        // should not contain more than (4*3 = 12) immegrants at max.
        if(piece.getPieceType() == mResidentPieceType) {
            Log.w(TAG,"Error : "+ piece.getPieceId()+ " <=  is resident , Not immegrant ");
            return ; // Return error
        }
        if(mImmegrintsList.contains(piece)) {
            Log.w(TAG,"Error : "+ piece.getPieceId()+ " Already present in the Immegrant Q ");
            return ;
        }
        mImmegrintsList.add(piece);
        setValue(this);
    }

    public Piece popFromImmegrints(int type) {  // return ERROR code

        if(mImmegrintsList.isEmpty()) return null;
        for(int j = 0;j<mImmegrintsList.size();j++) {
            Piece localPiece = mImmegrintsList.get(j);

            if(localPiece.getPieceType() == type) {
                mImmegrintsList.remove(localPiece);
                setValue(this); // TODO: is required
                return localPiece;
            }
        }
        return null;
    }
    public void debugPrintResidents() {
        System.out.println("Resident Info ");
        if(mResidentQ.size()<=0) {
            System.out.println("No Residents! ");
        }

        for(Piece piece :mResidentQ) {
            System.out.println("Current Position "+ piece.getCurrentPosition());
            System.out.println("Home Cell no "+ piece.getHomeCell());
            System.out.println("PawnId "+ piece.getPieceId());
            System.out.println("Piece Color "+ piece.getPieceType());
        }
    }

    public void debugImmegrants() {
        System.out.println("Immegrants Info ");
        if(mImmegrintsList.size()<=0) {
            System.out.println("No Immegrants! ");
            return ;
        }
        for(Piece piece :mImmegrintsList) {
            System.out.println("Current Position "+ piece.getCurrentPosition());
            System.out.println("Home Cell no "+ piece.getHomeCell());
            System.out.println("PawnId "+ piece.getPieceId());
            System.out.println("Piece Color "+ piece.getPieceType());
        }
    }
}