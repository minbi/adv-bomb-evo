package com.example.gigabytes.helloworld;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Min Bi on 12/22/2015.
 */
public class Bomb extends GameObject {
    int countDown;

    public Bomb(float x, float y, GameView game) {
        super(x, y, game);
        init();
    }

    private void init() {
        countDown = 30 * 5;
        _brush = new Paint();
        _brush.setColor(0xFF7E5826);
        _brush.setStyle(Paint.Style.FILL);
        _brush.setTextSize(48F);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(_x, _y, 50, _brush);
        canvas.drawText("" + (countDown / 30), _x + 55, _y, _brush);
    }

    public void update() {
        if (countDown > 0) {
            countDown--;
        } else {
            _game.explode(this);
        }
    }
}
