package com.example.jeonwon.gaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 조영태 on 2018-03-20.
 */

public class ScheduleDetail extends AppCompatActivity {

    Intent intent;

    Button button;

    EditText editLocation;
    EditText editTime;
    EditText editBudget;
    EditText editSpentMoney;
    EditText editMemo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_detail);
        intent = getIntent();//new Intent(getApplicationContext(), ListViewSchedule.class);


        editLocation = findViewById(R.id.editLocation);
        editTime = findViewById(R.id.editTime);
        editBudget = findViewById(R.id.editBudget);
        editSpentMoney = findViewById(R.id.editSpentmoney);
        editMemo = findViewById(R.id.editMemo);
        button = findViewById(R.id.btnOkdetail);

        editLocation.setText(intent.getStringExtra("place"));
        editTime.setText(intent.getStringExtra("time"));
        editBudget.setText(intent.getStringExtra("budget"));
        editSpentMoney.setText(intent.getStringExtra("spentmoney"));
        editMemo.setText(intent.getStringExtra("memo"));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    intent.putExtra("place", editLocation.getText().toString());
                }catch (NumberFormatException e){
                    intent.putExtra("place", "");
                }

              //  try {
                Log.i("d",editTime.getText().toString());
                    intent.putExtra("time", editTime.getText().toString());
           //     }catch (NumberFormatException e){
            //        Log.i("에러ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ","ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
            //        intent.putExtra("time", "");
            //    }

                try {
                    intent.putExtra("budget", editBudget.getText().toString());
                }catch (NumberFormatException e){
                    intent.putExtra("budget", "");
                }

                try {
                    intent.putExtra("spentmoney", editSpentMoney.getText().toString());
                }catch (NumberFormatException e){
                    intent.putExtra("spentmoney", "");
                }

                try {
                    intent.putExtra("memo", editMemo.getText().toString());
                }catch (NumberFormatException e){
                    intent.putExtra("memo", "");
                }

                setResult(RESULT_OK, intent);
                finish();


            }
        });
    }


}
