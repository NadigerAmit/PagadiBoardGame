package com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class RestingCell extends Cell{
    private Queue<Piece> mResidentQ = new LinkedList<>();
    private ArrayList<Piece> mImmegrintsList = new ArrayList<Piece>();
    private int mResidentPieceType = -1;
    public RestingCell(int cellNo) {
        super(cellNo,Constants.CELL_TYPE.RESTING_CELL);
        // TODO Auto-generated constructor stub
    }

    public void InitializeWithResidents(ArrayList<Piece> residents) {
        if(residents.size()<= 0) {
            System.out.println("RestingCell: InitializeWithResidents residents.size()<= 0 ");
            return;
        }
        mResidentPieceType = residents.get(0).getPieceType();
        for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
            System.out.println(residents.get(i).getHomeCell()+" Adding Residents "+residents.get(i).getPawnId());
            mResidentQ.add(residents.get(i));
        }
    }

    public Piece getPiece() {
        if(mResidentQ.isEmpty()) return null;
        return mResidentQ.peek();
    }

    public ArrayList<Piece> getResidents() {
        return  new ArrayList(mResidentQ);
    }

    public int getNumOfResidents() {
        return mResidentQ.size();
    }

    public void  pushToResidents(Piece piece) {  // return ERROR code
        if(mResidentQ.size()>Constants.NUM_OF_RESIDENT) return ;
        if(mResidentQ.contains(piece)) return ;
        mResidentQ.add(piece);
    }

    public Piece popFromResidents() {  // return ERROR code
        if(mResidentQ.isEmpty()) return null;
        Piece piece = mResidentQ.poll();
        return piece;
    }

    public void  pushToImmegrints(Piece piece) {  // return ERROR code

        if(mImmegrintsList.size()>Constants.NUM_OF_RESIDENT*3) {
            System.out.println(piece.getPawnId()+ " Error : Reached Max capacity , This impossiable !!!! ");
            return ;
        }
        // should not contain more than (4*3 = 12) immegrants at max.
        if(piece.getPieceType() == mResidentPieceType) {
            System.out.println("Error : "+ piece.getPawnId()+ " <=  is resident , Not immegrant ");
            return ; // Return error
        }
        if(mImmegrintsList.contains(piece)) {
            System.out.println("Error : "+ piece.getPawnId()+ " Already present in the Immegrant Q ");
            return ;
        }
        mImmegrintsList.add(piece);
    }

    public Piece popFromImmegrints(int color) {  // return ERROR code

        if(mImmegrintsList.isEmpty()) return null;
        for(int j = 0;j<mImmegrintsList.size();j++) {
            Piece localPiece = mImmegrintsList.get(j);

            if(localPiece.getPieceType() == color) {
                mImmegrintsList.remove(localPiece);
                return localPiece;
            }
        }
		/*
		 * Iterator i = mImmegrintsList.iterator();
		while (i.hasNext()) {
			Piece pawn = (Piece)i;
			if(pawn.getPieceType() == color) {
				boolean removeStatus = mImmegrintsList.remove(pawn);
				System.out.println("Error : "+pawn.getPawnId()+ "Remove from ImmegrantList status= "+removeStatus);
			}
			return pawn;
		}
		*/
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
            System.out.println("PawnId "+ piece.getPawnId());
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
            System.out.println("PawnId "+ piece.getPawnId());
            System.out.println("Piece Color "+ piece.getPieceType());
        }
    }
}