package com.example.jeonwon.gaza;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JeonWon on 2018-03-16.
 */

public class ConnectSERVER {

    private String sID, sPW, data, connURL;


    protected void setUserInfo(String ID, String PW){
        sID = ID;
        sPW = PW;
    }

    protected void setServer(){
        connURL = "http://192.168.0.128/loginGaza.v2.0.php";
    }

    protected String ConnectSERVER() {
        setServer();
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

            return data;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return data;
        }
    }

}
