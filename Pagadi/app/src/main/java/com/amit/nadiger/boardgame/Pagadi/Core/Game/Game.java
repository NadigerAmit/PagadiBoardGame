package com.amit.nadiger.boardgame.Pagadi.Core.Game;

import java.util.ArrayList;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.Pawn;
import com.amit.nadiger.boardgame.Pagadi.Core.Game.Board.Pawn.PawnFactory;
import com.amit.nadiger.boardgame.Pagadi.Core.Game.Player.Player;
import com.amit.nadiger.boardgame.Pagadi.Core.GameRequest;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;



public class Game {
    // single player
    private final Constants.GAME_PLAYER_MODE mMode ;
    private final Board mBoard;
    private GameRequest mGameReq ;

    ArrayList<Pawn> mPlayer1Pawn = null;
    ArrayList<Pawn> mPlayer2Pawn = null;
    ArrayList<Pawn> mPlayer3Pawn = null;
    ArrayList<Pawn> mPlayer4Pawn = null;
    Player mPlayer1 = null;
    Player mPlayer2 = null;
    Player mPlayer3 = null;
    Player mPlayer4 = null;

    Game(GameRequest req) {
        mBoard = new Board();
        mMode = req.getGamePlayerMode();
        mGameReq = req;
        initialize(req);
    }

    private void initialize(GameRequest req) {
        switch(mMode) {
            case SINGLE_PLYER : // with computer
                CreatePawn(1,req);

                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Pawn);
                // Create the computer player automatically .
                System.out.println("SINGLE_PLYER mode is not supported");
                break;
            case TWO_PLYER:

                CreatePawn(1,req);
                CreatePawn(2,req);
                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Pawn);

                mPlayer2 = new Player(req.getPlayer2Name(),
                        req.getPlayer2Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer2HomeCellNum()),
                        mPlayer2Pawn);

                // Relate Players with their Pawns
                // Relate the Players for resting cells
                break;
            case THREE_PLYER:
                CreatePawn(1,req);
                CreatePawn(2,req);
                CreatePawn(3,req);
                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Pawn);

                mPlayer2 = new Player(req.getPlayer2Name(),
                        req.getPlayer2Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer2HomeCellNum()),
                        mPlayer2Pawn);

                mPlayer3 = new Player(req.getPlayer3Name(),
                        req.getPlayer3Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer3HomeCellNum()),
                        mPlayer3Pawn);
                break;
            case FOUR_PLYER:
                CreatePawn(1,req);
                CreatePawn(2,req);
                CreatePawn(3,req);
                CreatePawn(3,req);
                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Pawn);

                mPlayer2 = new Player(req.getPlayer2Name(),
                        req.getPlayer2Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer2HomeCellNum()),
                        mPlayer2Pawn);

                mPlayer3 = new Player(req.getPlayer3Name(),
                        req.getPlayer3Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer3HomeCellNum()),
                        mPlayer3Pawn);

                mPlayer4 = new Player(req.getPlayer4Name(),
                        req.getPlayer3Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer4HomeCellNum()),
                        mPlayer4Pawn);
                break;
            default:
                break;
        }
    }

    private void CreatePawn(int PlayerNum, GameRequest req) {
        int id = 0;
        Pawn pawn = null;
        switch(PlayerNum) {
            case 1:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    pawn = PawnFactory.getPawn(req.getPlayer1Color(),req.getPlayer1HomeCellNum());
                    mPlayer1Pawn.add(pawn);
                }
                break;
            case 2:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    pawn = PawnFactory.getPawn(req.getPlayer2Color(),req.getPlayer2HomeCellNum());
                    mPlayer2Pawn.add(pawn);
                }
                break;
            case 3:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    pawn = PawnFactory.getPawn(req.getPlayer3Color(),req.getPlayer3HomeCellNum());
                    mPlayer3Pawn.add(pawn);
                }
                break;
            case 4:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    pawn = PawnFactory.getPawn(req.getPlayer4Color(),req.getPlayer4HomeCellNum());
                    mPlayer4Pawn.add(pawn);
                }
                break;
            default:
                System.out.println(PlayerNum+" mode is not supported");
                break;
        }
    }

    private void populatePlayersList() {

    }

    /*
     * *Write initialize function (It should do following )
     * 1. Initialize the Board()
     * 2. Initialize the mode (single player , 2 player , 3 player ,4 player )
     * 3. Based on the mode of play , initialize he Pawns (4 Pawns ) with restingSeat etc .
     * 4. Associate the Pawns for each player
     * 5. Associate the Players for each RestingCell in Board.
     *
     */
    /*
     * * Initialize the Dice()
     * 1. Initialize the dice with Max output Ex : 8
     * 2. Dice should output only values (1,2,3,4,8) ,Randomly .
     *
     */

    /*
     * * StartGame()
     * 1. Should run in thread (infinite while loop )
     * 2. Should do round robin scheduling for each player
     * 3. Maintain a circular Queue for each players
     * 4. Take the front and play its turn  , once finished push_back them in the queue
     * 5. If Anyaone wins (All 4 pawns reach final destination ), then dont push the palyer back on the Queue.
     * 6. Keep notifying the moves to upper layer i.e UI layer.
     *
     */

}
