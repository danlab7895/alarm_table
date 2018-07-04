package com.example.rlawl.myapplication;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_time extends Fragment {

    private Chronometer mChronometer;


    public Fragment_time() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_time,container,false);

        mChronometer=(Chronometer) view.findViewById(R.id.chronometer_view);
        Button startbtn=(Button) view.findViewById(R.id.start_button);
        Button stopbtn=(Button) view.findViewById(R.id.stop_button);
        Button resetbtn=(Button) view.findViewById(R.id.reset_button);

        startbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();
                }
        });
        stopbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                mChronometer.stop();
                }
        });
        resetbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());

            }
        });

        // Inflate the layout for this fragment
        return view;
        //inflater.inflate(R.layout.fragment_fragment_time, container, false);
    }

}