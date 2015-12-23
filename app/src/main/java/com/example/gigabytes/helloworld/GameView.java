package com.example.gigabytes.helloworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Min Bi on 12/21/2015.
 */
public class GameView extends View implements View.OnTouchListener {
    ArrayList<GameObject> pieces;
    GameObject hoverPiece;
    GameLoopThread looper;
    boolean first = true;

    Paint mRunnerPaint, mBombPaint;

    public GameView(Context context, AttributeSet aset) {
        super(context, aset);
        init();
        looper = new GameLoopThread(this);
        looper.start();
    }

    private void init() {
        this.setOnTouchListener(this);
        hoverPiece = null;
        pieces = new ArrayList<>();
        mRunnerPaint = new Paint();
        mRunnerPaint.setColor(0x00bfff);
        mRunnerPaint.setStyle(Paint.Style.FILL);
        mBombPaint = new Paint();
        mBombPaint.setColor(0xd1111a);
        mBombPaint.setStyle(Paint.Style.FILL);
    }

    public void placeBomb(int x, int y) {

    }

    public void generateRunner() {

    }

    public void update() {
        for (GameObject piece : pieces) {
            piece.update();
        }
    }

    public void redraw() {
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (GameObject piece : pieces) {
            piece.draw(canvas);
        }

        if (hoverPiece != null) {
            hoverPiece.draw(canvas);
        }

        System.out.println("onDraw() called.");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                hoverPiece = null;
                System.out.println(event.getX() + "," + event.getY());
                GameObject piece = new Bomb(event.getX(), event.getY());
                pieces.add(piece);
                redraw();
                break;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                hoverPiece = new Bomb(event.getX(), event.getY());
                redraw();
                break;
        }

        return true;
    }
}
