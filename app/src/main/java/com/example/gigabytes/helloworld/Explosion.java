package com.example.gigabytes.helloworld;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Min Bi on 2/2/2016.
 */
public class Explosion extends GameObject {

    public Explosion() {
        init();
    }

    public Explosion(float x, float y, GameView game) {
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

    public boolean checkIntersect(ArrayList<Point> pts) {
        for (Point p : pts) {
            if (p.x >= _x - 75 && p.x <= _x + 75 && p.y >= _y - 75 && p.y <= _y + 75) {
                return true;
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(_x - 75, _y - 75, _x + 75, _y + 75, _brush);
    }

    public void update() {
        _x -= 10;
        if (_x < 0 - 75) {
            _game.trash(this);
        }
    }
}
