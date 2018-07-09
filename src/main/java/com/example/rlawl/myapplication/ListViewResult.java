package com.example.rlawl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ListViewResult extends AppCompatActivity {

    TextView number;
    Intent intent = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_result);
        number = (TextView)findViewById(R.id.listResult);
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        int num = Integer.parseInt(getIntent().getStringExtra("study"));
        Studytable studytable = dbHandler.findAltable(num);
        number.setText(studytable.get_name());
    }
}
