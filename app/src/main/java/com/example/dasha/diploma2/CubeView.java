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
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;


public class CubeView extends SurfaceView{

    Bitmap bitmapShip, bitmapSea, bitmapButtonUp, bitmapButtonDown;
    Paint paint = new Paint();
    int rotate;
    int widthShip, heightShip, widthSea, heightSea, widthButtonUp, heightButtonUp, drawLeftButtonUp,
            drawTopButtonUp, widthButtonDown, heightButtonDown, drawLeftButtonDawn,
            drawTopButtonDown;

    public CubeView(Context context) {
        super(context);

        bitmapShip = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ship);
        bitmapSea = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.sea);
        bitmapButtonUp = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.buttonup);
        bitmapButtonDown = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.buttondown);

    }

    public void setRotate(double rotate){
        this.rotate += rotate;
    }

    public void drawShip(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthShip = canvas.getWidth()/2;
        heightShip = canvas.getHeight();

        Bitmap bitmapShip_ = Bitmap.createScaledBitmap(bitmapShip, widthShip, heightShip, true);
        // ограничение добавить на поворот
        canvas.rotate(rotate, 500 + (widthShip/6), 300+ (heightShip/6));
        canvas.drawBitmap(bitmapShip_, 350, 320, paint);

    }

    public void drawSea(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthSea = canvas.getWidth();
        heightSea = canvas.getHeight();

        Bitmap bitmapSea_ = Bitmap.createScaledBitmap(bitmapSea, widthSea, heightSea, true);
        canvas.drawBitmap(bitmapSea_,0,0, paint);
    }

    public void drawButtonUp(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthButtonUp = canvas.getWidth()/7;
        heightButtonUp = canvas.getHeight()/5;
        drawLeftButtonUp = canvas.getWidth() - 200;
        drawTopButtonUp = 50;

        Bitmap bitmapButtonUp_ = Bitmap.createScaledBitmap(bitmapButtonUp, widthButtonUp, heightButtonUp, true);
        canvas.drawBitmap(bitmapButtonUp_, drawLeftButtonUp, drawTopButtonUp, paint);
    }

    public void drawButtonDown(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthButtonDown = canvas.getWidth()/7;
        heightButtonDown = canvas.getHeight()/5;
        drawLeftButtonDawn = canvas.getWidth() - 200;
        drawTopButtonDown = 200;

        Bitmap bitmapButtonDown_ = Bitmap.createScaledBitmap(bitmapButtonDown, widthButtonDown,
                heightButtonDown, true);
        canvas.drawBitmap(bitmapButtonDown_, drawLeftButtonDawn, drawTopButtonDown, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float currentX = event.getX();
        float currentY = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_UP:
                if( currentX > drawLeftButtonUp && currentX < drawLeftButtonUp + widthButtonUp &&
                        currentY > drawTopButtonUp && currentY < drawTopButtonUp + heightButtonUp )
                {
                    Log.e("tag", "onTouchEvent: drawable touched_1 ");
                }
                return true;

            case MotionEvent.ACTION_DOWN:
                if( currentX > drawLeftButtonDawn && currentX < drawLeftButtonDawn + widthButtonDown &&
                        currentY > drawTopButtonDown && currentY < drawTopButtonDown + heightButtonDown )
                {
                    Log.e("tag", "onTouchEvent: drawable touched_2 ");
                }
                return true;


        }
        return false;
    }

}
