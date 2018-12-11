package com.example.sammitafoya.finalapptake1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void askQuestion(View view) {
        // LAUNCH ACTIVITY FOR USER TO ENTER A QUESTION
        Intent launchQuestionLayout = new Intent(this, YesNoQuesActivity.class);

        startActivity(launchQuestionLayout);
    }

}
