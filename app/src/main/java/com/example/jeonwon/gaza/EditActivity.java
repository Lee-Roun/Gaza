package com.example.jeonwon.gaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;


/**
 * Created by JeonWon on 2018-03-10.
 */

//구글맵 API 키
//AIzaSyC-PiJhaeOQcu288CgZD8Dt-xo1idhWViQ
public class EditActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DBHelper dbHelper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tapmain);

        //DB
        dbHelper = new DBHelper(EditActivity.this, "GazaDB", null, 1);
        dbHelper.testDB();
        //DB

        //탭페이지
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //뷰페이저 달기
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //탭 넘어가는 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
