package com.example.jeonwon.gaza;


import java.sql.Date;

/**
 * Created by JeonWon on 4/1/2018.
 */

public class Plan {

    private String title, budget;
    private Date startDate, endDate;
    private int people;

    public Plan(){

    }
    public Plan(String title, String budget, int people){
        this.title = title;
        this.budget = budget;
        this.people = people;
    }

    //SET
    public void setTitle(String title){
        this.title = title;
    }

    public void setBudget(String budget){
        this.budget = budget;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    //GET
    public String getTitle(){
        return this.title;
    }

    public String getBudget(){
        return this.budget;
    }

    public int getPeople() {
        return this.people;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

}
