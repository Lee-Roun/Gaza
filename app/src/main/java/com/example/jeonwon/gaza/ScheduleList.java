package com.example.jeonwon.gaza;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by sec on 2018-03-17.
 */

public class ScheduleList extends AppCompatActivity {

    //며칠짜리 여행인지 선택하면 생기는 클래스
    LinearLayout layout;
    int people;
    long tripPeriod;
    String budget,planName;
    public Intent adapterIntent;
    private int a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulelist);


        layout=(LinearLayout)findViewById(R.id.layout);
        adapterIntent = new Intent(getApplicationContext(), ScheduleDetail.class);

        Intent intent=getIntent();
        tripPeriod=intent.getLongExtra("TripPeriod",0);
        people=intent.getIntExtra("People",0);




        //N일차 만큼 리스트 만드는 루프
        for(int i=1;i<=tripPeriod;i++){
            TextView textView=new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            textView.setText(i+"일차");
            textView.setTextSize(20);
            textView.setBackgroundColor(Color.GRAY);
            layout.addView(textView);

            final ArrayList list=new ArrayList();

            final ListView listView= new ListView(this);
            final ListViewAdapter adapter;
            adapter=new ListViewAdapter(this);
            listView.setAdapter(adapter);
            listView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            layout.addView(listView);

            //각 일차별 상세 일정 추가 버튼
            Button btn=new Button(this);
            btn.setText("추가");
            btn.setTextSize(20);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
            btn.setLayoutParams(params );
            layout.addView(btn);

            //버튼 리스너
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ScheduleList.this, "d", Toast.LENGTH_SHORT).show();
                    adapter.addItem(null,Integer.toString(a++),"d","예싼","");
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
