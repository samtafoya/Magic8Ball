package com.example.sammitafoya.finalapptake1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

//YesNo means a question about an action, open means there is a question about the future
public class YesNoQuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yesno_layout);
    }

    public void getAnswer(View view) {
        // LAUNCH ACTIVITY FOR USER TO GET THEIR ANSWER
        Intent launchQuestionLayout = new Intent(this, OpenQuestActivity.class);

        startActivity(launchQuestionLayout);
    }

}
