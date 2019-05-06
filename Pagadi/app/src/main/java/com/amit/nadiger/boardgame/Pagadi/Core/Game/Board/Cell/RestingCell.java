package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.Pawn;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class RestingCell extends Cell{
    private Queue<Pawn> mResidentQ = new LinkedList<>();
    private ArrayList<Pawn> mImmegrintsList = new ArrayList<Pawn>();
    private Constants.PAWNCOLOR mRestingCellColor = null;
    public RestingCell(int cellNo) {
        super(cellNo,Constants.CELL_TYPE.RESTING_CELL);
        // TODO Auto-generated constructor stub
    }

    public void InitializeWithResidents(ArrayList<Pawn> residents) {
        if(residents.size()<= 0) {
            System.out.println("RestingCell: InitializeWithResidents residents.size()<= 0 ");
            return;
        }
        mRestingCellColor = residents.get(0).getColor();
        for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
            System.out.println(residents.get(i).getHomeCell()+" Adding Residents "+residents.get(i).getPawnId());
            mResidentQ.add(residents.get(i));
        }
    }

    public int getNumOfResidents() {
        return mResidentQ.size();
    }

    public void  pushToResidents(Pawn pawn) {  // return ERROR code
        if(mResidentQ.size()>Constants.NUM_OF_RESIDENT) return ;
        if(mResidentQ.contains(pawn)) return ;
        mResidentQ.add(pawn);
    }

    public Pawn popFromResidents() {  // return ERROR code
        if(mResidentQ.isEmpty()) return null;
        Pawn pawn = mResidentQ.poll();
        return pawn;
    }

    public void  pushToImmegrints(Pawn pawn) {  // return ERROR code

        if(mImmegrintsList.size()>Constants.NUM_OF_RESIDENT*3) {
            System.out.println(pawn.getPawnId()+ " Error : Reached Max capacity , This impossiable !!!! ");
            return ;
        }
        // should not contain more than (4*3 = 12) immegrants at max.
        if(pawn.getColor() == mRestingCellColor) {
            System.out.println("Error : "+pawn.getPawnId()+ " <=  is resident , Not immegrant ");
            return ; // Return error
        }
        if(mImmegrintsList.contains(pawn)) {
            System.out.println("Error : "+pawn.getPawnId()+ " Already present in the Immegrant Q ");
            return ;
        }
        mImmegrintsList.add(pawn);
    }

    public Pawn popFromImmegrints(Constants.PAWNCOLOR color) {  // return ERROR code

        if(mImmegrintsList.isEmpty()) return null;
        for(int j = 0;j<mImmegrintsList.size();j++) {
            Pawn localPawn = mImmegrintsList.get(j);

            if(localPawn.getColor() == color) {
                mImmegrintsList.remove(localPawn);
                return localPawn;
            }
        }
		/*
		 * Iterator i = mImmegrintsList.iterator();
		while (i.hasNext()) {
			Pawn pawn = (Pawn)i;
			if(pawn.getColor() == color) {
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

        for(Pawn pawn:mResidentQ) {
            System.out.println("Current Position "+pawn.getCurrentPosition());
            System.out.println("Home Cell no "+pawn.getHomeCell());
            System.out.println("PawnId "+pawn.getPawnId());
            System.out.println("Pawn Color "+pawn.getColor());
        }
    }

    public void debugImmegrants() {
        System.out.println("Immegrants Info ");
        if(mImmegrintsList.size()<=0) {
            System.out.println("No Immegrants! ");
            return ;
        }
        for(Pawn pawn:mImmegrintsList) {
            System.out.println("Current Position "+pawn.getCurrentPosition());
            System.out.println("Home Cell no "+pawn.getHomeCell());
            System.out.println("PawnId "+pawn.getPawnId());
            System.out.println("Pawn Color "+pawn.getColor());
        }
    }
}