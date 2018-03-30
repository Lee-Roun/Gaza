package com.example.jeonwon.gaza;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 조영태 on 2018-03-28.
 */

public class Spentmoney_popup extends AppCompatActivity {
    Button button;
    EditText editText;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.spentmoney_popup);

        button = findViewById(R.id.btnpOk);
        editText = findViewById(R.id.txtText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("spentMoney", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }



}
