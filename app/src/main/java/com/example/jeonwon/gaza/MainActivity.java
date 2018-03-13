package com.example.jeonwon.gaza;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItem(null, "text", "이", "예산", "쓴돈sdfsdf");
        adapter.addItem(null, "text1", "정", "예산dsf", "쓴돈");
        adapter.addItem(null, "text2", "원", "예산sdfsd", "쓴돈");

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "dd", Toast.LENGTH_SHORT).show();
                /*
                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
*/
                // TODO : use item data.
            }
        });

    }
}
