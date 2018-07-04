package com.example.rlawl.myapplication;

import android.app.NotificationChannel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.transform.Result;

public class NotifyDemoActivity extends AppCompatActivity {

    //CheckBox의 값을 저장 할 변수 list선언
    ArrayList<String> selection = new ArrayList<String>();

    TextView final_text;
    TextView final_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_demo);
        final_text = (TextView) findViewById(R.id.final_result);
        final_text.setEnabled(false);
        final_name =(TextView) findViewById(R.id.result_name);
        final_name.setEnabled(false);
        Button transButton = (Button)findViewById(R.id.transButton);
        transButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        ResultActivity.class)

                        //다른 Activity를 열었을 때 값을 전달하는 부분
                        .putExtra("value","선택된 인원이 없습니다.");

                startActivity(intent);
            }
        });
    }

    public void createNotification(View view) {
        // CheckBox에서 체크 된 메시지 호출
        String final_msg_selection = "";
        //문자열 순서로 정렬
        Collections.sort(selection);
        for(String Selections : selection){
            final_msg_selection = final_msg_selection + Selections + " ";
        }
        final_text.setText(final_msg_selection);
        final_text.setEnabled(true);

        //RadioButton에서 선택한 이름 호출
        String name = (String)final_name.getText();

        //알림 생성 메소드 호출
        show(name,final_msg_selection);
    }

    private void show(String name, String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        if(name!=null){
            builder.setContentTitle(name);
        } else if (name =="") {
            Toast.makeText(getApplication(),"Select Your Message Name", Toast.LENGTH_SHORT).show();
        } else {
            builder.setContentTitle("알림 생성");
        }
        if(msg != null)
        {builder.setContentText(msg);}
        else
        {builder.setContentText("알림 없음");}

        // 알림에서 선택한 RadioButton의 텍스트를 알림 페이지에서 출력
        Intent intent = new Intent(this, ResultActivity.class).putExtra("value",final_name.getText());
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        builder.setLargeIcon(largeIcon);
        builder.setColor(Color.RED);
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this,
                RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        long[] vibrate = {0,100,200,300};
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);
        //noti를 누르면 noti가 날아간다.

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Oreo버전 부터는 아래 코드가 필요함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default",
                    "기본 채널",NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1,builder.build());
    }

    public void removeNotification(View view) {
        hide();
    }

    private void hide() {
        selection.clear();
        CheckBox one = findViewById(R.id.oneMsg);
        CheckBox two = findViewById(R.id.twoMsg);
        CheckBox three = findViewById(R.id.threeMsg);
        one.setChecked(false);
        two.setChecked(false);
        three.setChecked(false);
        RadioButton kjh = (RadioButton)findViewById(R.id.kjh);
        RadioButton khw = (RadioButton)findViewById(R.id.khw);
        RadioButton kyh = (RadioButton)findViewById(R.id.kyh);
        kjh.setChecked(false);
        khw.setChecked(false);
        kyh.setChecked(false);

        NotificationManagerCompat.from(this).cancel(1);
        // = NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 같은 코드

    }

    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()){
            case R.id.oneMsg:
                if(checked)
                    {selection.add("1번 Msg");}
                else
                    {selection.remove("1번 Msg");}
                break;
            case R.id.twoMsg:
                if(checked)
                    {selection.add("2번 Msg");}
                else
                    {selection.remove("2번 Msg");}
                break;
            case R.id.threeMsg:
                if(checked)
                    {selection.add("3번 Msg");}
                else
                    {selection.remove("3번 Msg");}
                break;
        }
    }

    public void selectName(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.kjh:
                if(checked){
                    final_name.setText("김지하님");
                    final_name.setEnabled(true);
                }
                else{
                    final_name.setEnabled(false);
                }
                break;
            case R.id.khw:
                if(checked){
                    final_name.setText("김혜원님");
                    final_name.setEnabled(true);
                }
                else{
                    final_name.setEnabled(false);
                }
                break;
            case R.id.kyh:
                if(checked){
                    final_name.setText("김용호님");
                    final_name.setEnabled(true);
                }
                else{
                    final_name.setEnabled(false);
                }
                break;
         }
    }
}