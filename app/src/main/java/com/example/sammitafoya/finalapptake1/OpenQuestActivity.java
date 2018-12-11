package com.example.sammitafoya.finalapptake1;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.AsyncTask;

public class OpenQuestActivity extends AppCompatActivity {

    public Answers answers;
    public SensorManager sensorManager;
    public Sensor sensorAccelerometer;
    public MyShakeListener mShakeListener;
    private MyAsyncTask myAsyncTask;
    private boolean isRotating;
    private int angle;
    public ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openques_layout);

        // ASSIGN A NEW ANSWER
        answers = new Answers();

        // CREATE AN EMPTY TEXTVIEW
        final TextView ansView = (TextView) findViewById(R.id.ansView);
        ansView.setText("");

        // USE AN ASYNC TASK AND THE ACCELEROMETER SENSOR IN ORDER TO MAKE THE MAGIC 8 BALL IMAGE SPINN
        angle = 0;
        myImage = findViewById(R.id.imageView);
        isRotating = false;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeListener = new MyShakeListener(new MyShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                ansView.setText(answers.getMyAnswer());
                ansView.setTextColor(Color.WHITE);
                if (!isRotating) {
                    isRotating = true;
                    myAsyncTask = new MyAsyncTask();
                    myAsyncTask.execute(angle);
                } else {
                    isRotating = false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(mShakeListener, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(mShakeListener, sensorAccelerometer);
        super.onPause();
    }

    // ONCLICK TO RETURN THE USER TO THE PREVIOUS ACTIVITY
    public void askAnother(View view){
        Intent launchQuestionLayout = new Intent(this, YesNoQuesActivity.class);

        startActivity(launchQuestionLayout);
    }

    // FUNCTION TO ROTATE THE PICTURE
    public void rotateAction(View view){
        if (!isRotating) {
            isRotating = true;
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(angle);
        } else {
            isRotating = false;
        }
    }

    // ASYNC TASK TO ROTATE THE MAGIC 8 BALL IMAGE
    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        protected Integer doInBackground(Integer... values){
            angle = values[0];
            while(isRotating){
                try{
                    Thread.sleep(100);
                    angle = (angle + 10) % 360;
                    publishProgress(angle);
                }
                catch (InterruptedException e){}
            }
            return angle;
        }

        protected void onProgressUpdate (Integer... values){
            myImage.setRotation(values[0]);
        }
    }

}
