package com.example.gigabytes.helloworld;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Min Bi on 12/22/2015.
 */
public class Runner extends GameObject {

    public Runner(float x, float y, GameView game) {
        super(x, y, game);
        init();
    }

    private void init() {
        _brush = new Paint();
        _brush.setColor(0xFF7E5826);
        _brush.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(_x, _y, 50, _brush);
    }
}
