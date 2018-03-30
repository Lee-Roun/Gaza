package com.example.jeonwon.gaza;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                MainFragment MainFragment = new MainFragment();
                return MainFragment;
            case 1:
                SettingFragment SettingFragment = new SettingFragment();
                return SettingFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
