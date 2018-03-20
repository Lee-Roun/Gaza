package com.example.jeonwon.gaza;

import android.content.Intent;
<<<<<<< HEAD:app/src/main/java/com/example/jeonwon/gaza/ListViewSchedule.java
=======
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
>>>>>>> e44c25e6835dee9ff47cf71cd1b8c965ddbdc0d9:app/src/main/java/com/example/jeonwon/gaza/MainActivity.java
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD:app/src/main/java/com/example/jeonwon/gaza/ListViewSchedule.java
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
=======
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
>>>>>>> e44c25e6835dee9ff47cf71cd1b8c965ddbdc0d9:app/src/main/java/com/example/jeonwon/gaza/MainActivity.java

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
<<<<<<< HEAD:app/src/main/java/com/example/jeonwon/gaza/ListViewSchedule.java

=======
>>>>>>> e44c25e6835dee9ff47cf71cd1b8c965ddbdc0d9:app/src/main/java/com/example/jeonwon/gaza/MainActivity.java

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItem(null, "text", "이", "예산", "");
        adapter.addItem(null, "text1", "정", "예산dsf", "");
        adapter.addItem(null, "text2", "원", "예산sdfsd", "");



    }
}
