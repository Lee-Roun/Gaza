package com.example.jeonwon.gaza;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by JeonWon on 4/1/2018.
 */

public class LoadingActivity extends AppCompatActivity {

    private TextView textView;
    private Animation anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.textViewLogo);

        initView();

        //startLoading();


    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void initView() {
        anim = AnimationUtils.loadAnimation(this, R.anim.loading);
        textView.setAnimation(anim);
    }

    public class loading extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }


    }


}
