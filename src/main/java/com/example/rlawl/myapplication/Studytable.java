package com.example.rlawl.myapplication;

import java.util.ArrayList;

public class Studytable extends ArrayList<Integer> {
    private int _num;
    private String _name;
    private int _time;
    private int _study;
    private int _rest;
    private int _str;
    private int _sleep;

    public Studytable(){}
    public Studytable(String name,int time,  int study, int rest, int str, int sleep){
        this._name = name;
        this._time = time;
        this._study = study;
        this._rest = rest;
        this._str = str;
        this._sleep = sleep;
    }

    public void set_time(int _time){this._time = _time;}

    public int get_time(){return _time;}

    public int get_num() {
        return _num;
    }

    public void set_num(int _num) {
        this._num = _num;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_study() {
        return _study;
    }

    public void set_study(int _study) {
        this._study = _study;
    }

    public int get_rest() {
        return _rest;
    }

    public void set_rest(int _rest) {
        this._rest = _rest;
    }

    public int get_str() {
        return _str;
    }

    public void set_str(int _str) {
        this._str = _str;
    }

    public int get_sleep() {
        return _sleep;
    }

    public void set_sleep(int _sleep) {
        this._sleep = _sleep;
    }
}
