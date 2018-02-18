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

    Bitmap bitmapShip, bitmapSea;
    Paint paint = new Paint();
    int width;
    int height;
    int rotate;

    public CubeView(Context context) {
        super(context);

        bitmapShip = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ship);
        bitmapSea = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.sea);

    }

    public void setRotate(double rotate){
        this.rotate += rotate;
    }

    public void drawShip(Canvas c){
        paint.setColor(Color.BLUE);
        width = c.getWidth();
        height = c.getHeight();

        Bitmap bitmapShip_ = Bitmap.createScaledBitmap(bitmapShip, width / 2, height, true);

        c.rotate(rotate, 500 + (width/12), 300+ (height/6));
        c.drawBitmap(bitmapShip_, 350, 320, paint);

    }

    public void drawSea(Canvas c){
        paint.setColor(Color.BLUE);
        width = c.getWidth();
        height = c.getHeight();

        Bitmap bitmapSea_ = Bitmap.createScaledBitmap(bitmapSea, width, height, true);
        c.drawBitmap(bitmapSea_,0,0, paint);
    }


}
