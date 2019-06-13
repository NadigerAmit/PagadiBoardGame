package com.amit.nadiger.boardgame.Pagadi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.view.UiBoard;
import com.amit.nadiger.boardgame.Pagadi.view.GameActivity;

public class MainActivity extends AppCompatActivity {
    private UiBoard myCustomView = null;

    Button startNewGameButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startNewGameButton = findViewById(R.id.button);
       // myCustomView = findViewById(R.id.view);
        startNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartGameActivity();
            }
        });
      // printBoardGame();
    }

    private void StartGameActivity() {
        Intent startGameIntent = new Intent(this, GameActivity.class);
        startActivity(startGameIntent);
    }

    void printBoardGame() {
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

        Game game = Game.getInstance(req);
        game.DebugPrintGame();
    }
}
