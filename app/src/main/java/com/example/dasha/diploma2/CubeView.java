package com.example.dasha.diploma2;

/**
 * Created by dasha on 01.11.2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;


public class CubeView extends SurfaceView {

    Bitmap bitmapCube;
    Paint paint = new Paint();
    int width;
    int height;
    int rotate;

    public CubeView(Context context) {
        super(context);

        bitmapCube = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.cube);

    }

    public void setRotate(double rotate){
        this.rotate += rotate;
    }

    public void drawCube(Canvas c){
        paint.setColor(Color.BLUE);
        width = c.getWidth();
        height = c.getHeight();

        Bitmap bitmapCube_ = Bitmap.createScaledBitmap(bitmapCube, width / 6, height / 3, true);

        c.rotate(rotate, 500 + (width/6)/2, 300+ (height/3)/2);
        c.drawBitmap(bitmapCube_, 500, 300, paint);

    }


}
