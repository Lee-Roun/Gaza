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
    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FirstFragment FirstFragment = new FirstFragment();
                return FirstFragment;
            case 1:
                ThirdFragment ThirdFragment = new ThirdFragment();

                //SecondFragment SecondFragment = new SecondFragment();
                return ThirdFragment;
            /*
                case 2:
                ThirdFragment ThirdFragment = new ThirdFragment();
                return ThirdFragment;
                */
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
