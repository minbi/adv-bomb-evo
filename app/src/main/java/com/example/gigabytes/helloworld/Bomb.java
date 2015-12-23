package com.example.gigabytes.helloworld;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Min Bi on 12/22/2015.
 */
public class Bomb extends GameObject {

    public Bomb(float x, float y) {
        super(x, y);
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
