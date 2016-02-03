package com.example.gigabytes.helloworld;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Min Bi on 12/22/2015.
 */
public class Runner extends GameObject {
    boolean dead;
    int radius;

    public Runner(float x, float y, GameView game) {
        super(x, y, game);
        init();
    }

    private void init() {
        radius = 50;
        dead = false;
        _brush = new Paint();
        _brush.setColor(0xFF7E5826);
        _brush.setStyle(Paint.Style.FILL);
    }

    public float x() {
        return _x;
    }

    public float y() {
        return _y;
    }

    public void setDead() {
        _brush.setColor(Color.GREEN);
    }

    public boolean checkIntersect(ArrayList<Point> pts) {
        for (Point p : pts) {
            if (Math.pow(p.x - _x, 2) + Math.pow(p.y - _y,2) < Math.pow(radius, 2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(_x, _y, radius, _brush);
    }

    @Override
    public void update() {
        _y -= 2;
        if (_y < 0 - 25) {
            _game.trash(this);
        }
    }


}
