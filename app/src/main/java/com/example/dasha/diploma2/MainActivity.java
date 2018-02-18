package com.example.dasha.diploma2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.Timer;



public class MainActivity extends AppCompatActivity implements SensorEventListener {

    CubeView cubeView;
    private Sensor mOrientation;
    private SensorManager mSensorManager;
    private Timer timer;
    private TextView tv_XZ;
    private float xz_angle = 0.0f;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cubeView = new CubeView(this);
        setContentView(this.cubeView);

        this.timer = new Timer();
        this.mSensorManager = (SensorManager) getSystemService("sensor");
        this.mOrientation = this.mSensorManager.getDefaultSensor(1);
        this.mSensorManager.registerListener(this, this.mOrientation, 3);
    }

    public void onSensorChanged(SensorEvent event) {
        this.xz_angle = event.values[1];
        this.cubeView.setRotate((double) this.xz_angle);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onStart() {
        super.onStart();
        this.timer.scheduleAtFixedRate(new GraphUpdater(this.cubeView), 0, 100);
        this.mSensorManager.registerListener(this, this.mOrientation, 1);
        getWindow().setFlags(128, 128);
    }

    public void onStop() {
        super.onStop();
        finish();
        this.timer.cancel();
        this.timer.purge();
        this.mSensorManager.unregisterListener(this);
    }
}