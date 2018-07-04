package com.example.rlawl.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int number0fTabs){
        super(fm);
        this.tabCount=number0fTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                Fragment_alarm tab1=new Fragment_alarm();
                return tab1;
            case 1:
                Fragment_time tab2=new Fragment_time();
                return tab2;
            default:
                return null;
        }
    }
    @Override
    public int getCount(){
        return tabCount;
    }

}
