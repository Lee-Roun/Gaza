package com.example.jeonwon.gaza;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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
    int people;
    long tripPeriod;
    String budget,planName;
    public Intent adapterIntent;
    private int a;
    LinearLayout layout;
    int pos;
    Intent intentPop;
    String result;
    ListView temp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulelist);

        adapterIntent = new Intent(getApplicationContext(), ScheduleDetail.class);

        layout=(LinearLayout)findViewById(R.id.layout);
        Intent intent=getIntent();
        tripPeriod=intent.getLongExtra("TripPeriod",0);
        people=intent.getIntExtra("People",0);

        intentPop=new Intent(getApplicationContext(),Spentmoney_popup.class);



        //N일차 만큼 리스트 만드는 루프
        for(int i=1;i<=tripPeriod;i++){

            TextView textView=new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            textView.setText(i+"일차");
            textView.setTextSize(20);
            textView.setBackgroundColor(Color.GRAY);
            layout.addView(textView);

            final ListView listView= new ListView(this);
            final ListViewAdapter adapter;
            adapter=new ListViewAdapter(this);


            listView.setAdapter(adapter);
            listView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT,WRAP_CONTENT));

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    pos=position;
                    startActivityForResult(intentPop, 1);
                    temp= (ListView) parent;

                    return false;
                }
            });
            layout.addView(listView);

            LinearLayout btnLayout=new LinearLayout(this);
            btnLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            btnLayout.setGravity(Gravity.RIGHT);
            layout.addView(btnLayout);

            //각 일차별 상세 일정 추가 버튼
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);

            Button list=new Button(this);
            list.setText("리스트");
            list.setTextSize(20);
            list.setLayoutParams(params );
            btnLayout.addView(list);

            Button map=new Button(this);
            map.setText("지도");
            map.setTextSize(20);
            map.setLayoutParams(params );
            btnLayout.addView(map);

            //버튼 리스너
            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.addItem(null,Integer.toString(a++),"d","예싼","");
                    setListViewHeightBasedOnChildren(listView);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ListViewAdapter adapter= (ListViewAdapter) temp.getAdapter();
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                result = data.getStringExtra("spentMoney");

                adapter.setSpnetMoney(pos, result);
            }
        }
    }
}
