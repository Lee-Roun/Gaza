package com.example.jeonwon.gaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewSchedule extends AppCompatActivity {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};
    private Button add;
    public TextView  spentMoney;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_schedule);

        add = (Button)findViewById(R.id.add);
        spentMoney = (TextView)findViewById(R.id.todaymoney);

        intent = new Intent(getApplicationContext(), ScheduleDetail.class);

        ListView listview;
        ListViewAdapter adapter;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                Toast.makeText(ListViewSchedule.this, "추가 창 넘어가기", Toast.LENGTH_SHORT).show();
            }
        });

        // Adapter 생성
        adapter = new ListViewAdapter(this);


        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItem(null, "text", "이", "예산", "");
        adapter.addItem(null, "text1", "정", "예산dsf", "");
        adapter.addItem(null, "text2", "원", "예산sdfsd", "");



    }
}
