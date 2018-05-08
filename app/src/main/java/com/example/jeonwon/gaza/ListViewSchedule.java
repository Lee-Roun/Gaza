package com.example.jeonwon.gaza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewSchedule extends Activity {
    private Button add;
    public TextView spentMoney;

    public Intent intent;
    public Intent intentPop;

    public ListViewAdapter adapter;
    private ListView listView;

    private String memo;

    int pos;//아이템 위치
    ListViewItem listItem;//아이템 한개
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_schedule);

        add = (Button) findViewById(R.id.add);
        spentMoney = (TextView) findViewById(R.id.todaymoney);

        intent = new Intent(getApplicationContext(), ScheduleDetail.class);
        intentPop = new Intent(getApplicationContext(), Spentmoney_popup.class);

        // Adapter 생성
        //adapter = new ListViewAdapter(this);
        adapter = new ListViewAdapter();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(intent);
                adapter.addItem(null, "", "", "예산", "22");
                adapter.notifyDataSetChanged();
            }
        });


        // 리스트뷰 참조 및 Adapter달기
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//롱 클릭
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                startActivityForResult(intentPop, 1);

                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//세부창 보기
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListView temp = (ListView) parent;
                ListViewAdapter Atemp = (ListViewAdapter) temp.getAdapter();
                listItem = (ListViewItem) Atemp.getItem(position);

                intent.putExtra("place", listItem.getTitle().toString());
                intent.putExtra("time", listItem.gettime().toString());
                intent.putExtra("budget", listItem.getBudget().toString());
                intent.putExtra("spentmoney", listItem.getSpentMoney().toString());
                intent.putExtra("memo", listItem.getMemo());
                startActivityForResult(intent, 2);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("spentMoney");

                adapter.setSpnetMoney(pos, result);

            }
        }
        else if(requestCode == 2){
            if(resultCode == RESULT_OK){

                listItem.setTitle(data.getStringExtra("place"));
                listItem.settime(data.getStringExtra("time"));
                listItem.setBudget(data.getStringExtra("budget"));
                listItem.setSpentMoney(data.getStringExtra("spentmoney"));
                listItem.setMemo(data.getStringExtra("memo"));
                adapter.notifyDataSetChanged();


            }
        }
    }
}
