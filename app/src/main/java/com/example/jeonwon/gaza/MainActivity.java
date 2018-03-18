package com.example.jeonwon.gaza;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};
    private Button add;
    public TextView  spentMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button)findViewById(R.id.add);
        spentMoney = (TextView)findViewById(R.id.todaymoney);

        ListView listview;
        ListViewAdapter adapter;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "추가 창 넘어가기", Toast.LENGTH_SHORT).show();
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

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
   /*    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                /get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "dd", Toast.LENGTH_SHORT).show();

                String titleStr = item.getTitle() ;
                String descStr = item.gettime() ;

                 TODO : use item data.
            }
        });*/

    }
}
