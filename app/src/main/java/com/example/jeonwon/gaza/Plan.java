package com.example.jeonwon.gaza;


import java.sql.Date;

/**
 * Created by JeonWon on 4/1/2018.
 */

public class Plan {

    private String title;
    private String startDate, endDate;
    private String people, budget;

    public Plan(){

    }
    public Plan(String title, String budget, String people){
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

    public void setPeople(String people) {
        this.people = people;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    //GET
    public String getTitle(){
        return this.title;
    }

    public String getBudget(){
        return this.budget;
    }

    public String getPeople() {
        return this.people;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

}
