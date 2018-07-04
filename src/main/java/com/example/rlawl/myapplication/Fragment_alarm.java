package com.example.rlawl.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_alarm extends Fragment {


    public Fragment_alarm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //fragment_alarm에서 setButton가져오기, view는 onCreateView에서 사용해야 버튼 객체 생성 가능해서 사용한 것
        View view=inflater.inflate(R.layout.fragment_fragment_alarm,container,false);
        Button setbtn=(Button) view.findViewById(R.id.setButton);

        //버튼 setOnClickListener 만들기, 버튼 눌렀을 경우
        setbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(),NotifyDemoActivity.class);
                //Intent는 다른 activity와 연결시켜주는것, this 사용불가, 현재 Context를 받아오는 getActivity
                startActivity(intent1); //띄우기
            }
        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fragment_alarm, container, false);
        return view;
    }

}