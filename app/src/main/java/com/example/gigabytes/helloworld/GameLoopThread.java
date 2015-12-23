package com.example.gigabytes.helloworld;

import android.app.Activity;

/**
 * Created by Min Bi on 12/22/2015.
 */
public class GameLoopThread extends Thread {
    private final static int FPS = 30;
    private final static int FRAME_PERIOD = 1000 / 30;
    private final static int MAX_FRAME_SKIPS = 5;

    private boolean _running;
    private GameView _game;
    private Activity _uiActivity;

    public GameLoopThread(GameView game) {
        _game = game;
        _running = true;
        _uiActivity = (Activity) _game.getContext();
    }

    public void setRunning(boolean running) {
        _running = running;
    }

    @Override
    public void run() {
        while (_running) {
            long startTime = System.currentTimeMillis();
            int framesSkipped = 0;
            _uiActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _game.update();
                    _game.redraw();
                }
            });

            long timeDiff = System.currentTimeMillis() - startTime;
            long sleepTime = FRAME_PERIOD - timeDiff;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (sleepTime > 0 && framesSkipped < MAX_FRAME_SKIPS) {
                _uiActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _game.update();
                    }
                });
                sleepTime += FRAME_PERIOD;
                ++framesSkipped;
            }
        }
    }
}
