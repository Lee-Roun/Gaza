package com.example.jeonwon.gaza;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private Context context;
    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
         context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title) ;
        TextView timeTextView = (TextView) convertView.findViewById(R.id.time) ;
        TextView budgetTextView = (TextView) convertView.findViewById(R.id.budget) ;
        TextView spentMoenyEditView = (EditText) convertView.findViewById(R.id.spentmoney) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(listViewItem.getTitle());
        timeTextView.setText(listViewItem.gettime());
        budgetTextView.setText(listViewItem.getBudget());
        spentMoenyEditView.setText(listViewItem.getSpentMoney());

        titleTextView.setOnClickListener(new detail_form());
        timeTextView.setOnClickListener(new detail_form());
        budgetTextView.setOnClickListener(new detail_form());
        spentMoenyEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        return convertView;
    }
    class detail_form implements View.OnClickListener{
        public void onClick(View v){
            Toast.makeText(context, "dd", Toast.LENGTH_SHORT).show();
        }
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon, String title, String desc, String budget, String spentMoney) {
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.settime(desc);
        item.setBudget(budget);
        item.setSpentMoney(spentMoney);

        listViewItemList.add(item);
    }
}

