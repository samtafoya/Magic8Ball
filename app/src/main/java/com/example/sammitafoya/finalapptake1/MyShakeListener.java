package com.example.sammitafoya.finalapptake1;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

public class MyShakeListener implements SensorEventListener {

    public long timeOfLastShake;

    public static final float SHAKE_THRESHOLD_GRAVITY = 22.0f;
    public static final int SHAKE_TIME_LAPSE = 500;

    public OnShakeListener mShakeListener;

    public MyShakeListener(OnShakeListener shakeListener) {
        mShakeListener = shakeListener;
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            double value = Math.pow(gForceX, 2.0) + Math.pow(gForceY, 2.0) + Math.pow(gForceZ, 2.0);
            float gForce = (float) Math.sqrt(value);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                // DON'T ALLOW MULTIPLE SHAKES
                final long now = System.currentTimeMillis();
                if (timeOfLastShake + SHAKE_TIME_LAPSE > now) {
                    return;
                }
                timeOfLastShake = now;

                mShakeListener.onShake();
            }
        }

    }
    @Override
    public void onAccuracyChanged (Sensor sensor,int accuracy){

    }

    public interface OnShakeListener {
        void onShake();
    }
}
