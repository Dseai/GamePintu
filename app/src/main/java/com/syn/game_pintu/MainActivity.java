package com.syn.game_pintu;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;

import android.widget.TextView;

import com.syn.game_pintu.view.GamePintuLayout;

public class MainActivity extends Activity
{

    private GamePintuLayout mGamePintuLayout;
    private TextView mLevel ;
    private TextView mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTime = (TextView) findViewById(R.id.id_time);
        mLevel = (TextView) findViewById(R.id.id_level);

        mGamePintuLayout = (GamePintuLayout) findViewById(R.id.id_gamepintu);
        mGamePintuLayout.setTimeEnabled(true);

        mGamePintuLayout.setOnGamePintuListener(new GamePintuLayout.GamePintuListener()
        {
            @Override
            public void timechanged(int currentTime)
            {
                mTime.setText(""+currentTime);
            }

            @Override
            public void nextLevel(final int nextLevel)
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Game Info").setMessage("LEVEL UP !!!")
                        .setPositiveButton("NEXT LEVEL", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                mGamePintuLayout.nextLevel();
                                mLevel.setText(""+nextLevel);
                            }
                        }).show();
            }

            @Override
            public void gameover()
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Game Info").setMessage("Game over !!!")
                        .setPositiveButton("RESTART", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                mGamePintuLayout.restart();
                            }
                        }).setNegativeButton("QUIT",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                }).show();
            }
        });

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        mGamePintuLayout.pause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mGamePintuLayout.resume();
    }

}
