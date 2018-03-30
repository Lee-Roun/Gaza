package com.example.jeonwon.gaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by JeonWon on 2018-03-13.
 */

public class MainFragment extends Fragment {

    private View view;
    private Button button, buttonMap;
    private String planName[] = {"China"};
    private ListView listView;
    private ListViewPlanAdapter listViewPlanAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.myplanlist, container,false);

        button = (Button)view.findViewById(R.id.buttonMakePlan);
        buttonMap =(Button)view.findViewById(R.id.buttonMap);

        listViewPlanAdapter = new ListViewPlanAdapter();
        listView = (ListView)view.findViewById(R.id.listViewPlan);

        listView.setAdapter(listViewPlanAdapter);

        for(int i=0; i<planName.length; i++){
            listViewPlanAdapter.addPlanItem(planName[i]);
        }


        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

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