package com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.Pawn;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class RestingCell extends Cell{
    private Queue<Pawn> mResidentQ ;
    private ArrayList<Pawn> mImmegrintsList;
    private Constants.PAWNCOLOR mRestingCellColor = null;
    public RestingCell(int cellNo) {
        super(cellNo,Constants.CELL_TYPE.RESTING_CELL);
        // TODO Auto-generated constructor stub
    }

    public void InitializeWithResidents(Pawn residents[]) {
        mResidentQ = new LinkedList<>();
        if(residents.length<= 0) {
            return;
        }
        mRestingCellColor = residents[0].getColor();
        for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
            mResidentQ.add(residents[i]);
        }
        mImmegrintsList = new  ArrayList<Pawn>();
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
}