package com.example.rlawl.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SavedAlarm extends AppCompatActivity {
    String dbname = "AlTable.db";
    String tablename = "altable";
    String sql;
    SQLiteDatabase db;   // db를 다루기 위한 SQLiteDatabase 객체 생성
    Cursor resultset;   // select 문 출력위해 사용하는 Cursor 형태 객체 생성
    ListView listView;   // ListView 객체 생성
    String[] result;   // ArrayAdapter에 넣을 배열 생성
    List<Studytable> list;
    MyDBHandler myDBHandler = new MyDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_alarm);
        db = openOrCreateDatabase(dbname, MODE_PRIVATE, null);   // 있으면 열고 없으면 DB를 생성
        listView = (ListView)findViewById(R.id.listView);

        try {
            sql = "select * from "+ tablename;
            resultset = db.rawQuery(sql, null);   // select 사용시 사용(sql문, where조건 줬을 때 넣는 값)
            int count = resultset.getCount();   // db에 저장된 행 개수를 읽어온다
            result = new String[count];   // 저장된 행 개수만큼의 배열을 생성

            for (int i = 0; i < count; i++) {
                resultset.moveToNext();   // 첫번째에서 다음 레코드가 없을때까지 읽음
                int _num = resultset.getInt(0);   // 첫번째 속성
                //String str_phone = resultset.getString(1);   // 두번째 속성
                result[i] = _num + "";   // 각각의 속성값들을 해당 배열의 i번째에 저장
            }
            System.out.println("select ok");
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, result);   // ArrayAdapter(this, 출력모양, 배열)
            for(String results : result){
                System.out.println(results);
            }
            listView.setAdapter(adapter);   // listView 객체에 Adapter를 붙인다

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), ListViewResult.class).putExtra("study",result[position]);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            System.out.println("select Error :  " + e);
        }
    }

    public void newAlarm(View view) {
        Intent intent = new Intent(getApplicationContext(),DataBaseActivity.class);
        startActivity(intent);
    }
}
