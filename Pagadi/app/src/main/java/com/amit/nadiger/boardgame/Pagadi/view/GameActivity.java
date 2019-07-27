package com.amit.nadiger.boardgame.Pagadi.view;

import android.content.Context;

import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.amit.nadiger.boardgame.Pagadi.R;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.model.GameRequest;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    Resources.Theme mThemeId ;

    static private Context mContextOfApplication = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){e.printStackTrace();}
        setContentView(R.layout.activity_game);

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

      //  Game game = new Game(req);

        if(savedInstanceState == null) {

            int displaymode = getResources().getConfiguration().orientation;
            if (displaymode == 1) { // it portrait mode
                Log.e(TAG, "Fragment is not yet created created ");
                getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, BoardFragment.newInstance())
                        .commitNow();
                Log.e(TAG, "Fragment is created ");
            } else {// its landscape
                // change the fragment from boardFragment to some other.
                getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, BoardFragment.newInstance())
                        .commitNow();
            }
            mContextOfApplication = this.getApplicationContext();
        }
    }
    public static Context getContextOfApplication(){
        return mContextOfApplication;
    }
}
