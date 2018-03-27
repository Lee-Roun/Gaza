package com.example.jeonwon.gaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by JeonWon on 2018-03-13.
 */

public class FirstFragment extends Fragment{

    private View view;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.myplanlist, container,false);

        button = (Button)view.findViewById(R.id.buttonMakePlan);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MakePlan.class);
                startActivity(intent);
            }
        });


        return view;

    }

}