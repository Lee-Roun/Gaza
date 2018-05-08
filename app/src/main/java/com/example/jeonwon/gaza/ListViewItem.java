package com.example.jeonwon.gaza;


public class ListViewItem {

    private String titleStr ;
    private String timeStr ;
    private String budgetStr;
    private String spentMoneyStr;
    private String memo;

    public void setTitle(String title) {
        this.titleStr = title ;
    }
    public void settime(String time) { this.timeStr = time ;}
    public void setBudget(String budget) {
        this.budgetStr = budget ;
    }
    public void setSpentMoney(String spentMoney) { this.spentMoneyStr = spentMoney ;}
    public void setMemo(String memo) { this.memo = memo ;}


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
    public String getMemo() {
        return this.memo ;
    }
}


