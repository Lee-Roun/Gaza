package com.example.jeonwon.gaza;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JeonWon on 2018-03-13.
 */

public class ThirdFragment extends Fragment {

    private TextView ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       /* View view = inflater.inflate(R.layout.manage, container, false);

        ID = (TextView)view.findViewById(R.id.textViewID);
*/
        return inflater.inflate(R.layout.manage, container, false);
    }
/*
    public void setTextID(String id){
        ID.setText(id);
    }*/
}
