package com.example.jeonwon.gaza;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

/**
 * Created by JeonWon on 2018-03-13.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private String id;
    public TabPagerAdapter(FragmentManager fm, int tabCount, String ID) {
        super(fm);
        this.tabCount = tabCount;
        this.id = ID;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                FirstFragment FirstFragment = new FirstFragment();
                return FirstFragment;
            case 1:
                SecondFragment SecondFragment = new SecondFragment();
                return SecondFragment;
            case 2:
                ThirdFragment ThirdFragment = new ThirdFragment();
                //ThirdFragment.setTextID(this.id);
                return ThirdFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
