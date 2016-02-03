package com.example.gigabytes.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Min Bi on 12/20/2015.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Play! button */
    public void playStart(View view) {
//        Button button = (Button) findViewById(R.id.playButton);
//        button.setText("Done." + System.currentTimeMillis());
        final GameView gameView = new GameView(this, null);
        setContentView(gameView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GameLoopThread looper = new GameLoopThread(gameView);
                looper.start();
            }
        });

    }

    protected void initGame() {

    }

}
