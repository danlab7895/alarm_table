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

import javax.xml.transform.Result;

public class NotifyDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_demo);
    }


    public void createNotification(View view) {
        show();
    }

    private void show() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("알림 제목");
        builder.setContentText("알림 세부 텍스트");

        Intent intent = new Intent(this, ResultActivity.class);
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
        NotificationManagerCompat.from(this).cancel(1);
        // = NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 같은 코드
    }
}
