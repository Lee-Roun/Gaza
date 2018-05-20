package com.example.jeonwon.gaza;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by sec on 2018-03-17.
 */

public class ScheduleList extends AppCompatActivity {

    public static boolean isSet=false;

    //며칠짜리 여행인지 선택하면 생기는 클래스
    int pos;
    int usedmoney;
    long tripPeriod;
    String result;
    String budget;
    String strPlace,strBudget,strTime,strSpentMoney;
   // private int a;
    Intent intentPop,intentMake, intentDetail;
    TextView textViewBudget, textViewUsedMoney, textViewResultMoney;
    LinearLayout layout;
    ListView temp; //임시 어댑터
    ListViewItem listItem,tempItem; // 세부창 넘어갈때 아이템 하나 저장 변수

    ListView tempListView;
    ListViewAdapter tempAdapter;





    private ArrayList<ListViewAdapter> adapterArrayList = new ArrayList<ListViewAdapter>();

    //어뎁터 컬랙션

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulelist);

        intentDetail = new Intent(getApplicationContext(), ScheduleDetail.class);
        intentPop=new Intent(getApplicationContext(),Spentmoney_popup.class);

        textViewBudget = (TextView)findViewById(R.id.textBudget);
        textViewUsedMoney = (TextView)findViewById(R.id.textUsedMoney);
        textViewResultMoney = (TextView)findViewById(R.id.textRestMoney);

        layout=(LinearLayout)findViewById(R.id.layout);
//        textViewBudget.setText(intentDetail.getExtras().getString("Budget"));


        intentMake=getIntent();
        tripPeriod=intentMake.getLongExtra("TripPeriod",0);
        budget=intentMake.getStringExtra("Budget");
        textViewBudget.setText("총 예산\n"+budget);

        Log.i("gaza","ok");


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


            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//롱클릭
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    pos=position;
                    startActivityForResult(intentPop, 1);
                    temp= (ListView) parent;

                    return true;
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//세부창 보기
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    temp = (ListView) parent;
                    ListViewAdapter Atemp = (ListViewAdapter) parent.getAdapter(); //어댑터 접근
                    temp = (ListView) parent;
                    //ListViewAdapter Atemp = (ListViewAdapter) temp.getAdapter();
//                    ListViewAdapter Atemp = (ListViewAdapter) temp.getAdapter();
                    listItem = (ListViewItem) Atemp.getItem(position); //postioion 위치의 아이템 가져옴

                    intentDetail.putExtra("place", listItem.getTitle().toString());
                    intentDetail.putExtra("time", listItem.gettime().toString());
                    intentDetail.putExtra("budget", listItem.getBudget().toString());
                    intentDetail.putExtra("spentmoney", listItem.getSpentMoney().toString());
                    intentDetail.putExtra("memo", listItem.getMemo());
                    startActivityForResult(intentDetail, 2);

                }
            });

            layout.addView(listView);
            adapterArrayList.add(adapter);

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
                    intentDetail.putExtra("place", "");
                    intentDetail.putExtra("time", "");
                    intentDetail.putExtra("budget", "");
                    intentDetail.putExtra("spentmoney", "");
                    intentDetail.putExtra("memo", "");
                    startActivityForResult(intentDetail,3);
                    tempAdapter=adapter;
                    tempListView=listView;
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


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ListViewAdapter adapter= (ListViewAdapter) temp.getAdapter();

                //데이터 받기
                result = data.getStringExtra("spentMoney");
             //   textViewUsedMoney.setText(result);
                adapter.setSpnetMoney(pos, result);
            }
        }else if(requestCode == 2){


            if(resultCode == RESULT_OK){
                ListViewAdapter adapter= (ListViewAdapter) temp.getAdapter();

                listItem.setTitle(data.getStringExtra("place"));
                listItem.settime(data.getStringExtra("time"));
                listItem.setBudget(data.getStringExtra("budget"));
                listItem.setSpentMoney(data.getStringExtra("spentmoney"));
                listItem.setMemo(data.getStringExtra("memo"));

                adapter.notifyDataSetChanged();

            }
        }else if(requestCode==3){
            if(resultCode==RESULT_OK){
                strPlace=data.getStringExtra("place");
                strTime=data.getStringExtra("time");
                strBudget=data.getStringExtra("budget");
                strSpentMoney=data.getStringExtra("spentmoney");
                tempAdapter.addItem(null, strPlace, strTime, strBudget, strSpentMoney);
                tempAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(tempListView);
            }
        }

        usedmoney=0;
        for (ListViewAdapter adapter:adapterArrayList) {
            ArrayList<ListViewItem> temp =adapter.getArrayList();
            for (ListViewItem tempItem :temp) {
                try{
                    usedmoney+=Integer.parseInt(tempItem.getSpentMoney());
                }
                catch(Exception e){}
            }
        }
        textViewUsedMoney.setText("사용한 돈\n"+usedmoney);
        int rest=Integer.parseInt(budget)-usedmoney;
        if(rest<0){
            textViewResultMoney.setTextColor(Color.RED);
        }
        else{
            textViewResultMoney.setTextColor(Color.BLACK);
        }
        textViewResultMoney.setText("남은 돈\n"+rest);
    }
}
