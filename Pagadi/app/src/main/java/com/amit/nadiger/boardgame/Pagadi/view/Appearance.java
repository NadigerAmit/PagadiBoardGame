package com.amit.nadiger.boardgame.Pagadi.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

public class Appearance {
    final static int DARK_SQUARE = 1;
    final static int BRIGHT_SQUARE = 0;
    final static int SELECTED_SQUARE = 2;
    final static int CURSOR_SQUARE = 3;
    final static int ARROW_0 = 4;
    static Context context;

    public static String colorTable[];
    final static int getColor(int colorType) {

        context=GameActivity.getContextOfApplication();
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//        int choice = sharedPreferences.getInt("Choice", 4);
        int choice = 4;
        switch(choice)
        {

            case 0:  colorTable = new String[]{ "#EF5350", "#D32F2F",
                    "#B71C1C", "#FF00FF00", "#A01F1FFF", "#A0FF1F1F", "#501F1FFF",
                    "#50FF1F1F", "#1E1F1FFF", "#28FF1F1F" }; break;
            case 1:  colorTable = new String[]{ "#42A5F5", "#1976D2",
                    "#0D47A1", "#FF00FF00", "#A01F1FFF", "#A0FF1F1F", "#501F1FFF",
                    "#50FF1F1F", "#1E1F1FFF", "#28FF1F1F" }; break;
            case 2:  colorTable = new String[]{ "#AB47BC", "#7B1FA2",
                    "#4A148C", "#FF00FF00", "#A01F1FFF", "#A0FF1F1F", "#501F1FFF",
                    "#50FF1F1F", "#1E1F1FFF", "#28FF1F1F" }; break;
            case 3:  colorTable = new String[]{ "#66BB6A", "#388E3C",
                    "#1B5E20", "#FF00FF00", "#A01F1FFF", "#A0FF1F1F", "#501F1FFF",
                    "#50FF1F1F", "#1E1F1FFF", "#28FF1F1F" }; break;
            case 4:  colorTable = new String[]{ "#FFA726", "#F57C00",
                    "#E65100", "#FF00FF00", "#A01F1FFF", "#A0FF1F1F", "#501F1FFF",
                    "#50FF1F1F", "#1E1F1FFF", "#28FF1F1F" }; break;
            case 5:  colorTable = new String[]{ "#BDBDBD", "#616161",
                    "#212121", "#FF00FF00", "#A01F1FFF", "#A0FF1F1F", "#501F1FFF",
                    "#50FF1F1F", "#1E1F1FFF", "#28FF1F1F" }; break;

        }
        return Color.parseColor(colorTable[colorType]);
    }
}
