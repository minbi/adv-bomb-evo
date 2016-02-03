package com.example.gigabytes.helloworld;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Min Bi on 12/21/2015.
 */
public class GameObject {
    float _x, _y;
    int _frameIndex;
    Paint _brush;
    GameView _game;

    public GameObject() {
        init();
    }

    public GameObject(float x, float y, GameView game) {
        init();
        _x = x;
        _y = y;
        _game = game;
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
        if (_x < 0 - 75) {
            _game.trash(this);
        }
    }
}
