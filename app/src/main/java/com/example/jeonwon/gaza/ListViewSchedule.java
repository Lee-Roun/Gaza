package com.example.jeonwon.gaza;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ListViewSchedule extends AppCompatActivity {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};
    private Button add;
    public TextView spentMoney;
    public Intent intent;
    public Intent intentPop;
    public ListViewAdapter adapter;
    TextView d;
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_schedule);

        add = (Button) findViewById(R.id.add);
        spentMoney = (TextView) findViewById(R.id.todaymoney);
        d = (TextView) findViewById(R.id.spentmoney);


        intent = new Intent(getApplicationContext(), ScheduleDetail.class);
        intentPop = new Intent(getApplicationContext(), Spentmoney_popup.class);
        ListView listview;
        adapter = new ListViewAdapter(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(intent);
                adapter.addItem(null, "text", "이", "예산", "");
                adapter.notifyDataSetChanged();
//                Toast.makeText(ListViewSchedule.this, "추가 창 넘어가기", Toast.LENGTH_SHORT).show();
            }
        });

        // Adapter 생성


        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        // adapter.addItem(null, "text1", "정", "예산dsf", "");
        // adapter.addItem(null, "text2", "원", "예산sdfsd", "");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            //데이터 받기
             result = data.getStringExtra("result");
            Log.i("d", result);
        }
    }

     public String a(){
        Log.i("d","실행");
        String result = intentPop.getStringExtra("spentMoney");
        startActivityForResult(intentPop, 1);


        return result;
    }


}
