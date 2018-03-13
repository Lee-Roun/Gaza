package com.example.jeonwon.gaza;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private String titleStr ;
    private String descStr ;
    private String budget;
    private String spentMoney;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setBudget(String desc) {
        descStr = budget ;
    }
    public void setSpentMoney(String desc) {
        descStr = spentMoney ;
    }


    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getBudget() {
        return this.budget ;
    }
    public String getSpentMoney() {
        return this.spentMoney ;
    }
}


