package com.example.dasha.diploma2;

/**
 * Created by dasha on 01.11.2017.
 */

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.TimerTask;


public class GraphUpdater extends TimerTask {
    private CubeView cubeView;
    static String color;
    static int degrees, px, py;

    GraphUpdater(CubeView cubeView)
    {
        this.cubeView=cubeView;
    }

    public static void updateColor(String color){
        CubeView.setColor(color);
    }

    public static void updateDegrees(int degrees){
        GraphUpdater.degrees = degrees;
    }

    public static void updatePx(int px){
        GraphUpdater.px=px;
    }

    public static void updatePy(int py){
        GraphUpdater.py=py;
    }

    @Override
    public void run() {
        Canvas canvas = cubeView.getHolder().lockCanvas();
        if (canvas!=null){
            canvas.drawColor(Color.WHITE);
            cubeView.drawSea(canvas);
            cubeView.drawButtonUp(canvas);
            cubeView.drawButtonDown(canvas);
            cubeView.drawTextSpeedView(canvas);
            cubeView.drawCompass(canvas);
            cubeView.drawArrow(canvas);
            cubeView.drawShip(canvas);
            cubeView.getHolder().unlockCanvasAndPost(canvas);
        }
    }
}
