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

    private Bitmap bitmapShip, bitmapSea, bitmapButtonUp, bitmapButtonDown,
            bitmapButtonUpBlack, bitmapButtonDownBlack, bitmapFieldForSpeed;
    Bitmap bitmapShip_, bitmapSea_, bitmapButtonUp_, bitmapButtonDown_,
            bitmapButtonUpBlack_, bitmapButtonDownBlack_, bitmapFieldForSpeed_;
    Bitmap bitmapCompass, bitmapArrow;
    Bitmap bitmapCompass_, bitmapArrow_;
    Paint paint = new Paint();
    int rotate;
    int rotateOfArrow = 0;
    static double speedView = 0.1;
    static String color = "white";
    int widthShip, heightShip, widthSea, heightSea;
    int widthButtonUp, heightButtonUp, drawLeftButtonUp,
            drawTopButtonUp, widthButtonDown, heightButtonDown, drawLeftButtonDawn,
            drawTopButtonDown;
    int widthTextSpeed, widthFieldForSpeed, heightFieldForSpeed,
            drawLeftTextSpeed, drawTopTextSpeed, drawLeftFieldForSpeed, drawTopFieldForSpeed;
    int widthCompass, heightCompass, drawLeftCompass, drawTopCompass, drawTopArrow, drawLeftArrow,
            widthArrow, heightArrow;
    MainActivity mainActivity = new MainActivity();

    public CubeView(Context context) {
        super(context);

        bitmapShip = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ship);
        bitmapSea = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.sea);
        bitmapButtonUp = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.buttonup);
        bitmapButtonUpBlack = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.buttonup2);
        bitmapButtonDown = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.buttondown);
        bitmapButtonDownBlack = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.buttondown2);
        bitmapFieldForSpeed = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.fieldforspeed);
        bitmapCompass = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.compass2);
        bitmapArrow = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.arrow);

    }

    public void setRotateOfShip(double rotate){
        // Log.v("tag", "rotate" + this.rotate);
        if(this.rotate <= 30 && this.rotate >=-30){
            this.rotate += rotate;
        }
        if (this.rotate > 30){
            this.rotate--;
        }
        if(this.rotate < -30){
            this.rotate++;
        }
    }

    public int getRotateOfShip(){
        return rotate;
    }

    public static void setSpeedView(double speedView){
        CubeView.speedView += speedView;
    }

    public static void setColor(String color){
        CubeView.color = color;
    }

    public void drawShip(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthShip = canvas.getWidth()/2;
        heightShip = canvas.getHeight();

        bitmapShip_ = Bitmap.createScaledBitmap(bitmapShip, widthShip, heightShip, true);
        canvas.rotate(rotate, 500 + (widthShip/6), 300+ (heightShip/6));
        canvas.drawBitmap(bitmapShip_, 350, 320, paint);

    }

    public void drawSea(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthSea = canvas.getWidth();
        heightSea = canvas.getHeight();

        bitmapSea_ = Bitmap.createScaledBitmap(bitmapSea, widthSea, heightSea, true);
        canvas.drawBitmap(bitmapSea_,0,0, paint);
    }

    public void drawButtonUp(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthButtonUp = canvas.getWidth()/7;
        heightButtonUp = canvas.getHeight()/5;
        drawLeftButtonUp = canvas.getWidth() - 200;
        drawTopButtonUp = 30;

        if(color.equals("white") || color.equals("black2")){
            bitmapButtonUp_ = Bitmap.createScaledBitmap(bitmapButtonUp, widthButtonUp, heightButtonUp, true);
            canvas.drawBitmap(bitmapButtonUp_, drawLeftButtonUp, drawTopButtonUp, paint);
        }

        else if(color.equals("black1")){
            bitmapButtonUpBlack_ = Bitmap.createScaledBitmap(bitmapButtonUpBlack, widthButtonUp, heightButtonUp, true);
            canvas.drawBitmap(bitmapButtonUpBlack_, drawLeftButtonUp, drawTopButtonUp, paint);
        }

    }

    public void drawButtonDown(Canvas canvas){
        paint.setColor(Color.BLUE);
        widthButtonDown = canvas.getWidth()/7;
        heightButtonDown = canvas.getHeight()/5;
        drawLeftButtonDawn = canvas.getWidth() - 200;
        drawTopButtonDown = 150 + canvas.getHeight()/7;

        if (color.equals("white") || color.equals("black1")){
            bitmapButtonDown_ = Bitmap.createScaledBitmap(bitmapButtonDown, widthButtonDown,
                    heightButtonDown, true);
            canvas.drawBitmap(bitmapButtonDown_, drawLeftButtonDawn, drawTopButtonDown, paint);
        }

        else if (color.equals("black2")){
            bitmapButtonDownBlack_ = Bitmap.createScaledBitmap(bitmapButtonDownBlack, widthButtonDown,
                    heightButtonDown, true);
            canvas.drawBitmap(bitmapButtonDownBlack_, drawLeftButtonDawn, drawTopButtonDown, paint);
        }

    }

    public void drawTextSpeedView(Canvas canvas){
        paint.setColor(Color.GREEN);
        // прорисовка фона
        widthFieldForSpeed = canvas.getWidth()/7;
        heightFieldForSpeed = canvas.getHeight()/7;
        drawLeftFieldForSpeed = canvas.getWidth() - 200;
        drawTopFieldForSpeed = 150;
        bitmapFieldForSpeed_ = Bitmap.createScaledBitmap(bitmapFieldForSpeed, widthFieldForSpeed,
                heightFieldForSpeed, true);
        canvas.drawBitmap(bitmapFieldForSpeed_, drawLeftFieldForSpeed, drawTopFieldForSpeed, paint);

        //прорисовка текста
        widthTextSpeed = canvas.getWidth()/18;
        drawLeftTextSpeed = canvas.getWidth() - 180;
        drawTopTextSpeed = 130 + heightFieldForSpeed;
        paint.setTextSize(widthTextSpeed);
        // String currentSpeed = String.format("%.11f", mainActivity.getUpdatedSpeedView());
        String currentSpeed = mainActivity.getUpdatedSpeedModel();
        canvas.drawText(currentSpeed, drawLeftTextSpeed, drawTopTextSpeed, paint);

    }

    public void drawCompass(Canvas canvas){
        paint.setColor(Color.BLUE);
        // прорисовка компаса
        widthCompass = canvas.getWidth()/4;
        heightCompass = canvas.getHeight()/3;
        drawLeftCompass = 45;
        drawTopCompass = 40;

        bitmapCompass_ = Bitmap.createScaledBitmap(bitmapCompass, widthCompass,
                heightCompass, true);
        canvas.drawBitmap(bitmapCompass_, drawLeftCompass, drawTopCompass, paint);

        // прорисовка стрелы
        widthArrow = canvas.getWidth()/48;
        heightArrow = canvas.getHeight()/4;
        drawLeftArrow = 35 + widthCompass/2;
        drawTopArrow = 0;

        bitmapArrow_ = Bitmap.createScaledBitmap(bitmapArrow, widthArrow,
                heightArrow, true);
        canvas.rotate(rotateOfArrow, 500 + (widthArrow/6), 300+ (heightArrow/6));
        canvas.drawBitmap(bitmapArrow_, drawLeftArrow, drawTopArrow, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float currentX = event.getX();
        float currentY = event.getY();
        switch(event.getAction())
        {
            // действие при нажатии(black picture)
            case MotionEvent.ACTION_DOWN:
                if( currentX > drawLeftButtonUp && currentX < drawLeftButtonUp + widthButtonUp &&
                        currentY > drawTopButtonUp && currentY < drawTopButtonUp + heightButtonUp )
                {
                    mainActivity.updateSpeedView(0.1);
                    GraphUpdater.updateColor("black1");
                    Log.v("tag", "onTouchEvent: drawable touched_1 " + mainActivity.getUpdatedSpeedView());
                }

                else if( currentX > drawLeftButtonDawn && currentX < drawLeftButtonDawn + widthButtonDown &&
                        currentY > drawTopButtonDown && currentY < drawTopButtonDown + heightButtonDown )
                {
                    mainActivity.updateSpeedView(-0.1);
                    GraphUpdater.updateColor("black2");
                    Log.v("tag", "onTouchEvent: drawable touched_2 " + mainActivity.getUpdatedSpeedView());
                }
                return true;

            // действие сразу после нажатия
            case MotionEvent.ACTION_UP:
                if( currentX > drawLeftButtonUp && currentX < drawLeftButtonUp + widthButtonUp &&
                        currentY > drawTopButtonUp && currentY < drawTopButtonUp + heightButtonUp )
                {
                    GraphUpdater.updateColor("white");
                    Log.v("tag", "onTouchEvent: drawable touched_10 ");
                }

                else if( currentX > drawLeftButtonDawn && currentX < drawLeftButtonDawn + widthButtonDown &&
                        currentY > drawTopButtonDown && currentY < drawTopButtonDown + heightButtonDown )
                {
                    GraphUpdater.updateColor("white");
                    Log.v("tag", "onTouchEvent: drawable touched_20 ");
                }
                return true;

        }
        return false;
    }

}
