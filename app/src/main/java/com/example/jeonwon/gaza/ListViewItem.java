package com.example.jeonwon.gaza;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private String titleStr ;
    private String timeStr ;
    private String budgetStr;
    private String spentMoneyStr;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void settime(String time) {timeStr = time ;}
    public void setBudget(String budget) {
        budgetStr = budget ;
    }
    public void setSpentMoney(String spentMoney) {
        spentMoneyStr = spentMoney ;
    }


    public String getTitle() {
        return this.titleStr ;
    }
    public String gettime() {return this.timeStr ;}
    public String getBudget() {
        return this.budgetStr ;
    }
    public String getSpentMoney() {
        return this.spentMoneyStr ;
    }
}


