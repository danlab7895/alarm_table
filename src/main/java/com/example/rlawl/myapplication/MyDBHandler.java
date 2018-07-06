package com.example.rlawl.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int database_version = 1;
    private static final String database_name = "AlTable.db";
    private static final String table_altable = "altable";

    public static final String column_num = "_num";
    public static final String column_name = "_name";
    public static final String column_time = "_time";
    public static final String column_study = "_study";
    public static final String column_rest = "_rest";
    public static final String column_sleep = "_sleep";
    public static final String column_str = "_str";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, database_name, factory, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_altable_table = "create table "+
                table_altable+"("
                +column_num + " integer primary key,"
                +column_name+" text,"
                +column_time+" integer,"
                +column_study+" integer,"
                +column_rest+" integer,"
                +column_str + " integer,"
                +column_sleep + " integer"+")";
        db.execSQL(create_altable_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table_altable);
        onCreate(db);
    }

    //값 추가
    public void addAltable(Studytable studytable){
        ContentValues values = new ContentValues();
        values.put(column_name, studytable.get_name());
        values.put(column_time, studytable.get_time());
        values.put(column_study, studytable.get_study());
        values.put(column_rest, studytable.get_rest());
        values.put(column_str, studytable.get_str());
        values.put(column_sleep,studytable.get_sleep());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(table_altable,null,values);
        db.close();
    }

    //값 삭제
    public boolean deleteAltable(String name){
        boolean result = false;

        String query = "select * from "+table_altable+" where "+
                column_name + " = \""+name+"\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        Studytable studytable = new Studytable();

        if(cursor.moveToFirst()){
            studytable.set_num(Integer.parseInt(cursor.getString(0)));
            System.out.println(studytable.get_num());
            db.delete(table_altable,column_num+" = ?", new String[]{String.valueOf(studytable.get_num())});
            cursor.close();
            return true;
        }
        db.close();
        return result;
    }

    public Studytable findAltable(String name){
        String query ="select * from "+table_altable+" where "+
                column_name + " = \"" + name+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(query);

        Cursor cursor = db.rawQuery(query,null);
        Studytable studytable = new Studytable();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            studytable.set_num(Integer.parseInt(cursor.getString(0)));
            studytable.set_name(cursor.getString(1));
            studytable.set_time(Integer.parseInt(cursor.getString(2)));
            studytable.set_study(Integer.parseInt(cursor.getString(3)));
            studytable.set_rest(Integer.parseInt(cursor.getString(4)));
            studytable.set_str(Integer.parseInt(cursor.getString(5)));
            studytable.set_sleep(Integer.parseInt(cursor.getString(6)));
            cursor.close();
        }else{
            studytable = null;
        }
        db.close();
        return studytable;
    }

    public Studytable findAltable(int num){
        String query ="select * from "+table_altable+" where "+
                column_num + " = " + num+"";
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(query);

        Cursor cursor = db.rawQuery(query,null);
        Studytable studytable = new Studytable();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            studytable.set_num(Integer.parseInt(cursor.getString(0)));
            studytable.set_name(cursor.getString(1));
            studytable.set_time(Integer.parseInt(cursor.getString(2)));
            studytable.set_study(Integer.parseInt(cursor.getString(3)));
            studytable.set_rest(Integer.parseInt(cursor.getString(4)));
            studytable.set_str(Integer.parseInt(cursor.getString(5)));
            studytable.set_sleep(Integer.parseInt(cursor.getString(6)));
            cursor.close();
        }else{
            studytable = null;
        }
        db.close();
        return studytable;
    }
}
