package com.example.gigabytes.helloworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Min Bi on 12/21/2015.
 */
public class GameView extends View implements View.OnTouchListener {
    ArrayList<GameObject> pieces;
    ArrayList<Bomb> bombs;
    ArrayList<Runner> runners;
    ArrayList<Explosion> explosions;
    GameObject hoverPiece;
    GameLoopThread looper;
    boolean first = true;
    int runnerDelay, score;

    Paint mRunnerPaint, mBombPaint, textPaint;


    public GameView(Context context, AttributeSet aset) {
        super(context, aset);
        init();
        looper = new GameLoopThread(this);
        looper.start();
    }

    private void init() {
        this.setOnTouchListener(this);
        hoverPiece = null;
        runnerDelay = 8 * 30;
        score = 0;
        pieces = new ArrayList<>();
        bombs = new ArrayList<>();
        runners = new ArrayList<>();
        explosions = new ArrayList<>();
        mRunnerPaint = new Paint();
        mRunnerPaint.setColor(0x00bfff);
        mRunnerPaint.setStyle(Paint.Style.FILL);
        mBombPaint = new Paint();
        mBombPaint.setColor(0xd1111a);
        mBombPaint.setStyle(Paint.Style.FILL);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(88F);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public void placeBomb(int x, int y) {

    }

    public synchronized void explode(Bomb bomb) {
        if (pieces.contains(bomb)) {
            System.out.println("Found bomb in list");
            pieces.remove(bomb);
            bombs.remove(bomb);
            Explosion explo = new Explosion(bomb._x, bomb._y, this);
            pieces.add(explo);
            explosions.add(explo);
        }
    }

    public synchronized boolean trash(GameObject obj) {
        if (pieces.contains(obj)) {
            if (obj instanceof Bomb) bombs.remove(obj);
            else if (obj instanceof Runner) runners.remove(obj);
            else if (obj instanceof Explosion) explosions.remove(obj);
            return pieces.remove(obj);
        }
        return false;
    }

    public void generateRunner() {
        if (runnerDelay <= 0) {
            runnerDelay = (int) (Math.random() * 5 + 2) * 30;
            int randX = (int) (Math.random() * 1300);
            Runner r = new Runner(randX, 2000, this);
            pieces.add(r);
            runners.add(r);
        } else {
            --runnerDelay;
        }
    }

    public void update() {
        generateRunner();
        for (int i = 0; i < pieces.size(); ++i) {
            pieces.get(i).update();
        }
        intersect();
    }

    private void intersect() {
        for (int i = 0; i < explosions.size(); ++i) {
            Explosion e = explosions.get(i);
            for (int j = 0; j < runners.size(); ++ j) {
                Runner r = runners.get(j);
                if (r.dead) {
                    continue;
                }
                ArrayList<Point> r_pts = new ArrayList<>();
                r_pts.add(new Point(r._x - 50, r._y));
                r_pts.add(new Point(r._x + 50, r._y));
                r_pts.add(new Point(r._x, r._y + 50));
                r_pts.add(new Point(r._x, r._y - 50));
                ArrayList<Point> e_pts = new ArrayList<>();
                e_pts.add(new Point(e._x + 75, e._y - 75));
                e_pts.add(new Point(e._x -75, e._y+75));
                e_pts.add(new Point(e._x - 75, e._y - 75));
                e_pts.add(new Point(e._x + 75, e._y + 75));
                if (e.checkIntersect(r_pts) || r.checkIntersect(e_pts)) {
                    ++score;
                    r.setDead();
                    trash(e);
                    --i;
                    break; // break so we only remove one runner per explosion
                }
            }
        }
    }

    public void redraw() {
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("Score: " + score, 5, 80, textPaint);
        for (GameObject piece : pieces) {
            piece.draw(canvas);
        }

        if (hoverPiece != null) {
            hoverPiece.draw(canvas);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                hoverPiece = null;
                System.out.println(event.getX() + "," + event.getY());
                Bomb piece = new Bomb(event.getX(), event.getY(), this);
                pieces.add(piece);
                bombs.add(piece);
                redraw();
                break;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                hoverPiece = new Bomb(event.getX(), event.getY(), this);
                redraw();
                break;
        }

        return true;
    }
}
