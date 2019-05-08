package com.amit.nadiger.boardgame.Pagadi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amit.nadiger.boardgame.Pagadi.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.Core.GameRequest;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printBoardGame();
    }

    void printBoardGame() {
        GameRequest req = new GameRequest();
        req.setGamePlayerMode(Constants.GAME_PLAYER_MODE.TWO_PLYER);
        req.setPlayer1Age(37);
        req.setPlayer1Name("Jai Shree Ram");
        req.setPlayer1Color(Constants.PAWNCOLOR.WHITE);
        req.setPlayer1HomeCellNum(2);

        req.setPlayer2Age(35);
        req.setPlayer2Name("Jai Bajrang Bali");
        req.setPlayer2Color(Constants.PAWNCOLOR.RED);
        req.setPlayer2HomeCellNum(22);

        Game game = new Game(req);
        game.DebugPrintGame();
    }
}
