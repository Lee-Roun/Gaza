package com.example.jeonwon.gaza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JeonWon on 3/31/2018.
 */

public class ListViewPlanAdapter extends BaseAdapter{

    private ArrayList<ListViewPlanItem> listPlan = new ArrayList<ListViewPlanItem>();
    private ArrayList<Plan> plans = new ArrayList<Plan>();

    @Override
    public int getCount() {
        return plans.size();
    }

    @Override
    public Object getItem(int i) {
        return plans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_plan, viewGroup, false);
        }

        TextView textView = (TextView)view.findViewById(R.id.textViewPlan);

        //ListViewPlanItem listViewPlanItem = listPlan.get(i);
        Plan plan = plans.get(i);

        textView.setText(plan.getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, (pos + 1) + "번째 리스트가 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void addPlanItem(String planName){
        //ListViewPlanItem item = new ListViewPlanItem();
        Plan item = new Plan();

        item.setTitle(planName);

        plans.add(item);
    }
}
