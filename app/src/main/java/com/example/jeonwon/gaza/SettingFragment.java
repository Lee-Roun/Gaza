package com.example.jeonwon.gaza;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JeonWon on 3/31/2018.
 */

public class SettingFragment extends Fragment {
    private TextView ID;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.manage, container, false);

        return view;
    }


}
