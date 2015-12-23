package com.example.gigabytes.helloworld;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Min Bi on 12/21/2015.
 */
public class GameObject {
    float _x, _y;
    int _frameIndex;
    Paint _brush;

    public GameObject() {
        init();
    }

    public GameObject(float x, float y) {
        init();
        _x = x;
        _y = y;
    }

    private void init() {
        _frameIndex = 0;
        _brush = new Paint();
        _brush.setColor(0xFFFF0000);
        _brush.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(_x - 75, _y - 75, _x + 75, _y + 75, _brush);
    }

    public void update() {
        _x -= 25;
    }
}
