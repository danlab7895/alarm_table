package com.example.rlawl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DataBaseActivity extends AppCompatActivity {

    TextView idView;
    EditText nameBox;
    EditText timeBox;
    EditText studyBox;
    EditText restBox;
    EditText strBox;
    EditText sleepBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        idView = (TextView)findViewById(R.id.alarmID);
        nameBox = findViewById(R.id.setName);
        timeBox = findViewById(R.id.setTime);
        studyBox = findViewById(R.id.setStudy);
        restBox = findViewById(R.id.setRest);
        strBox = findViewById(R.id.setStr);
        sleepBox = findViewById(R.id.setSleep);
    }

    public void newAlarm(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        int time = Integer.parseInt(timeBox.getText().toString());
        int study = Integer.parseInt(studyBox.getText().toString());
        int rest = Integer.parseInt(restBox.getText().toString());
        int str = Integer.parseInt(strBox.getText().toString());
        int sleep = Integer.parseInt(sleepBox.getText().toString());

        Studytable studytable = new Studytable(nameBox.getText().toString(),time, study,rest,str,sleep);
        dbHandler.addAltable(studytable);
        idView.setText("Add Alarm!");
        Clear();
    }

    public void findAlarm(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        Studytable studytable = dbHandler.findAltable(nameBox.getText().toString());

        if(studytable != null){
            idView.setText(String.valueOf(studytable.get_num()));
            nameBox.setText(String.valueOf(studytable.get_name()));
            timeBox.setText(String.valueOf(studytable.get_time()));
            studyBox.setText(String.valueOf(studytable.get_study()));
            restBox.setText(String.valueOf(studytable.get_rest()));
            strBox.setText(String.valueOf(studytable.get_str()));
            sleepBox.setText(String.valueOf(studytable.get_sleep()));
        }
        else{
            idView.setText("No Match Found");
        }
    }

    public void deleteAlarm(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,null,1);
        boolean result = dbHandler.deleteAltable(nameBox.getText().toString());
        if(result){
            idView.setText("Record Deleted!");
            Clear();
        }
        else
            idView.setText("No Match Found");
    }

    public void Clear(){
        nameBox.setText("");
        timeBox.setText("");
        studyBox.setText("");
        restBox.setText("");
        strBox.setText("");
        sleepBox.setText("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this,SavedAlarm.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
