package com.example.dasha.diploma2;

/**
 * Created by dasha on 01.11.2017.
 */

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.TimerTask;


public class GraphUpdater extends TimerTask {
    CubeView cubeView;

    GraphUpdater(CubeView cubeView)
    {
        this.cubeView=cubeView;
    }

    @Override
    public void run() {
        Canvas c = cubeView.getHolder().lockCanvas();
        if (c!=null){
            c.drawColor(Color.WHITE);
            cubeView.drawSea(c);
            cubeView.drawButtonUp(c);
            cubeView.drawButtonDown(c);
            cubeView.drawShip(c);
            cubeView.getHolder().unlockCanvasAndPost(c);
        }
    }
}
