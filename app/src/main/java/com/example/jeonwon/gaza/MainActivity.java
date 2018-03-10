package com.example.jeonwon.gaza;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText ID, PW;
    String sID, sPW;

    Button btnLogin, btnJoin, btnFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = (EditText) findViewById(R.id.editTextLgnId);
        PW = (EditText) findViewById(R.id.editTextLgnPw);

        btnLogin = (Button) findViewById(R.id.buttonLgn);
        btnJoin = (Button) findViewById(R.id.buttonJoin);
        btnFind = (Button) findViewById(R.id.buttonFind);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.buttonLgn:
                        Login(view);

                        //Toast.makeText(MainActivity.this, "로그인 버튼", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.buttonFind:

                        break;
                    case R.id.buttonJoin:
                        Intent intent = new Intent(MainActivity.this, SubActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        };

        btnJoin.setOnClickListener(listener);
        btnFind.setOnClickListener(listener);
        btnLogin.setOnClickListener(listener);


    }

    public void Login(View v) {

        try {
            sID = ID.getText().toString();
            sPW = PW.getText().toString();
        } catch (NullPointerException e) {
            Log.d("Error", e.getMessage());
        }

        LoginDB loinDB = new LoginDB();
        loinDB.execute();
    }

    public class LoginDB extends AsyncTask<Void, Integer, Void> {

        String data = "";


        @Override
        protected Void doInBackground(Void... voids) {

            String connURL = "http://192.168.0.128/loginGaza.v1.1.php";
            String param = "id=" + sID + "&pw=" + sPW + "";

            Log.e("POST", param);

            try {
                //서버 연결
                //서버 연결
                URL url = new URL(connURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                //OutputStream은 안드로이드->서버
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(param.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                InputStream is = null;
                BufferedReader in = null;

                is = httpURLConnection.getInputStream();
                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    buff.append(line + "\n");
                }
                data = buff.toString().trim();

                /* 서버에서 응답 */
                Log.e("RECV DATA", data);

                if (data.equals("0")) {
                    Log.e("RESULT", "성공적으로 처리되었습니다!");
                } else {
                    Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
            @Override
            protected void onPostExecute (Void avoid){
                super.onPostExecute(avoid);

                if(data.equals("0"))
                {
                    Log.e("RESULT","성공적으로 처리되었습니다!");
                    Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    startActivity(intent);
                }
                else if(data.equals("1"))
                {
                    Log.e("RESULT","비밀번호가 일치하지 않습니다.");
                    Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Log.e("RESULT","에러 발생! ERRCODE = " + data);
                    Toast.makeText(MainActivity.this, "등록중 에러", Toast.LENGTH_LONG).show();

                }



            }

    }

}
