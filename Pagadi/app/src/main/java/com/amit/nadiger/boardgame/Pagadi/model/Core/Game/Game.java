package com.amit.nadiger.boardgame.Pagadi.model.Core.Game;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Board;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Cell.RestingCell;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.PieceFactory;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player.Player;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Player.RobotPlayer;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.EXTRA_MOVE_REQUIRED;


public class Game {
    private static final String TAG = "Game";
    // single player
    private final Constants.GAME_PLAYER_MODE mMode ;
    private final Board mBoard;
    private GameRequest mGameReq ;
    static int gameNum = 0;
    private int mGameId ;
    private static Game mGameInstance = null;

    //Queue for maintaining the order .
    Queue<Player> mPlayingOrderQueue = new LinkedList<>();

    ArrayList<Piece> mPlayer1Piece = new ArrayList<Piece>();
    ArrayList<Piece> mPlayer2Piece = new ArrayList<Piece>();
    ArrayList<Piece> mPlayer3Piece = new ArrayList<Piece>();
    ArrayList<Piece> mPlayer4Piece = new ArrayList<Piece>();

    //MediatorLive data is LiveData subclass which may observe other LiveData objects and react on OnChanged events from them.
    MediatorLiveData<LiveData<Piece>> mPieceMediator = new MediatorLiveData<>();

    Player mPlayer1 = null;
    Player mPlayer2 = null;
    Player mPlayer3 = null;
    Player mPlayer4 = null;



    private  Game(GameRequest req) {
        mBoard = new Board();
        mMode = req.getGamePlayerMode();
        mGameReq = req;
        gameNum++;
        mGameId = gameNum;
        initialize(req);
    }

    public static Game getInstance(GameRequest req) {
        if(req.getGameId() == -1) {
            if(mGameInstance == null) {
                mGameInstance = new Game(req);
            }
        } else {
            // Try to retrive the game from storage i.e shared preferance.
        }
        return mGameInstance;
    }

    public LinkedList<Player> getPlayerList() {
        return (LinkedList)mPlayingOrderQueue;
    }

    synchronized private void updatePlayerOderQueue(Piece piece) {
        if(piece.getOwner() == getCurrentPlayer()) {
            ArrayList<Integer> moves = getCurrentPlayer().getMoves();
            if(moves == null || moves.isEmpty()) {
                Player player = mPlayingOrderQueue.poll();
                if(!player.win()) {
                    mPlayingOrderQueue.add(player);
                }
                // switch player
            } else if(moves.size() == 1) {
                if(moves.get(0) == EXTRA_MOVE_REQUIRED) {
                    // Show pop-up to user for extra chance.
                    // Due to killing of other piece , there will be some extra move.
                }
            } else {
                // still many moves are remaining for this player.
            }
        } else {
            // pop up , invalid piece selected.
        }
    }

    private void addSourceToPieceMediator() {
       // Populate from all 4 Piece lists
        for (LiveData<Piece> piece : mPlayer1Piece) {
            mPieceMediator.addSource(piece, new Observer<Piece>() {
                @Override
                public void onChanged(@Nullable Piece piece) {
                    updatePlayerOderQueue(piece);
                    mPieceMediator.setValue(piece);
                }
            });
        }
        for (LiveData<Piece> piece : mPlayer2Piece) {
            mPieceMediator.addSource(piece, new Observer<Piece>() {
                @Override
                public void onChanged(@Nullable Piece piece) {
                    updatePlayerOderQueue(piece);
                    mPieceMediator.setValue(piece);
                }
            });
        }
        for (LiveData<Piece> piece : mPlayer3Piece) {
            mPieceMediator.addSource(piece, new Observer<Piece>() {
                @Override
                public void onChanged(@Nullable Piece piece) {
                    updatePlayerOderQueue(piece);
                    mPieceMediator.setValue(piece);
                }
            });
        }
        for (LiveData<Piece> piece : mPlayer4Piece) {
            mPieceMediator.addSource(piece, new Observer<Piece>() {
                @Override
                public void onChanged(@Nullable Piece piece) {
                    updatePlayerOderQueue(piece);
                    mPieceMediator.setValue(piece);
                }
            });
        }
    }

    public MediatorLiveData<LiveData<Piece>> getPieceMediator()  { return mPieceMediator;}

    public Board getBoard() { return mBoard;}

    private void initialize(GameRequest req) {
        switch(mMode) {
            case SINGLE_PLYER : // with computer
                CreatePiece(1,req);
                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Piece,mBoard);
                // Create the Robot Player
                CreatePiece(2,req);
                mPlayer2 = new RobotPlayer(req.getPlayer2Name(),
                        req.getPlayer2Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer3HomeCellNum()),
                        mPlayer2Piece,mBoard);
                mPlayingOrderQueue.add(mPlayer1);
                mPlayingOrderQueue.add(mPlayer2);
                break;
            case TWO_PLYER:

                CreatePiece(1,req);
                CreatePiece(2,req);

                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Piece,mBoard);

                mPlayer2 = new Player(req.getPlayer2Name(),
                        req.getPlayer2Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer2HomeCellNum()),
                        mPlayer2Piece,mBoard);
                mPlayingOrderQueue.add(mPlayer1);
                mPlayingOrderQueue.add(mPlayer2);

                // Relate Players with their Pawns
                // Relate the Players for resting cells
                break;
            case THREE_PLYER:
                CreatePiece(1,req);
                CreatePiece(2,req);
                CreatePiece(3,req);
                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Piece,mBoard);

                mPlayer2 = new Player(req.getPlayer2Name(),
                        req.getPlayer2Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer2HomeCellNum()),
                        mPlayer2Piece,mBoard);

                mPlayer3 = new Player(req.getPlayer3Name(),
                        req.getPlayer3Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer3HomeCellNum()),
                        mPlayer3Piece,mBoard);
                mPlayingOrderQueue.add(mPlayer1);
                mPlayingOrderQueue.add(mPlayer2);
                mPlayingOrderQueue.add(mPlayer3);
                break;
            case FOUR_PLYER:
                CreatePiece(1,req);
                CreatePiece(2,req);
                CreatePiece(3,req);
                CreatePiece(3,req);
                mPlayer1 = new Player(req.getPlayer1Name(),
                        req.getPlayer1Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer1HomeCellNum()),
                        mPlayer1Piece,mBoard);

                mPlayer2 = new Player(req.getPlayer2Name(),
                        req.getPlayer2Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer2HomeCellNum()),
                        mPlayer2Piece,mBoard);

                mPlayer3 = new Player(req.getPlayer3Name(),
                        req.getPlayer3Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer3HomeCellNum()),
                        mPlayer3Piece,mBoard);

                mPlayer4 = new Player(req.getPlayer4Name(),
                        req.getPlayer3Age(),
                        (RestingCell)mBoard.getCell(req.getPlayer4HomeCellNum()),
                        mPlayer4Piece,mBoard);
                mPlayingOrderQueue.add(mPlayer1);
                mPlayingOrderQueue.add(mPlayer2);
                mPlayingOrderQueue.add(mPlayer3);
                mPlayingOrderQueue.add(mPlayer4);
                break;
            default:
                break;
        }
        addSourceToPieceMediator();
    }

    public Player getCurrentPlayer() {
        // Current layer is always at the front of the queue.
        return mPlayingOrderQueue.peek();
    }



    private void CreatePiece(int PlayerNum, GameRequest req) {
        int id = 0;
        Piece piece = null;
        switch(PlayerNum) {
            case 1:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    piece = PieceFactory.getPiece(req.getPlayer1Type(),req.getPlayer1HomeCellNum());
                    mPlayer1Piece.add(piece);
                }
                break;
            case 2:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    piece = PieceFactory.getPiece(req.getPlayer2Type(),req.getPlayer2HomeCellNum());
                    mPlayer2Piece.add(piece);
                }
                break;
            case 3:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    piece = PieceFactory.getPiece(req.getPlayer3Type(),req.getPlayer3HomeCellNum());
                    mPlayer3Piece.add(piece);
                }
                break;
            case 4:
                for(int i = 0;i<Constants.NUM_OF_RESIDENT;i++) {
                    piece = PieceFactory.getPiece(req.getPlayer4Type(),req.getPlayer4HomeCellNum());
                    mPlayer4Piece.add(piece);
                }
                break;
            default:
                Log.e(TAG,PlayerNum+" mode is not supported");
                break;
        }
    }



    private void populatePlayersList() {

    }

    public void printPlayers() {
        System.out.println("Player 1 Details");
        if(mPlayer1!= null) {
            System.out.println("Player1 Name" +mPlayer1.getName());
            System.out.println("Player1 Age" +mPlayer1.getAge());
            System.out.println("Player1 HomeCell No" +mPlayer1.getHomeCell().toString());
            for(Piece piece :mPlayer1.getPieces()) {
                piece.debugPrintPawn();
            }
        }

        System.out.println("Player 2 Details");
        if(mMode!= Constants.GAME_PLAYER_MODE.SINGLE_PLYER) {
            System.out.println("Player2 Name" +mPlayer2.getName());
            System.out.println("Player2 Age" +mPlayer2.getAge());
            System.out.println("Player2 HomeCell No" +mPlayer2.getHomeCell().getCellNo());
            for(Piece piece :mPlayer2.getPieces()) {
                piece.debugPrintPawn();
            }
        }

		/*
		System.out.println("Player 3 Details");
		if(mMode!= Constants.GAME_PLAYER_MODE.SINGLE_PLYER ||
				mMode!= Constants.GAME_PLAYER_MODE.TWO_PLYER) {
			System.out.println("Player3 Name" +mPlayer3.getName());
			System.out.println("Player3 Age" +mPlayer3.getAge());
			System.out.println("Player3 HomeCell No" +mPlayer3.getHomeCell().toString());
			for(Piece pawn:mPlayer3.getPieces()) {
				pawn.debugPrintPawn();
			}
		}

		System.out.println("Player 4 Details");
		if(mMode!= Constants.GAME_PLAYER_MODE.SINGLE_PLYER ||
				mMode!= Constants.GAME_PLAYER_MODE.TWO_PLYER ||
				mMode!= Constants.GAME_PLAYER_MODE.THREE_PLYER) {
			System.out.println("Player4 Name" +mPlayer4.getName());
			System.out.println("Player4 Age" +mPlayer4.getAge());
			System.out.println("Player4 HomeCell No" +mPlayer4.getHomeCell().toString());
			for(Piece pawn:mPlayer4.getPieces()) {
				pawn.debugPrintPawn();
			}
		}
	    */
    }



    public void DebugPrintGame() {
        System.out.println("Mode of game ="+this.mMode);
        printPlayers();
        System.out.println("Printing the board");
        mBoard.printCellMap();

    }

    public int saveGame() {
        // save gameId;
        // save different member variables .
        return mGameId;
    }

    public void start() {
        // prepare the order of game

    }

    public void Resume() {
        // Resume the  game from last poition
         // this should spawn the thread and run while loop untill game is completed or paused.
        // From here this game will be active .
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
