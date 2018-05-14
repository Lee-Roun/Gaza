package com.example.jeonwon.gaza;


import java.sql.Date;

/**
 * Created by JeonWon on 4/1/2018.
 */

public class Plan {

    private String title;
    private String startDate, endDate;
    private int people, budget, PID;

    public Plan(){

    }
    public Plan(String title, int budget, int people){
        this.title = title;
        this.budget = budget;
        this.people = people;
    }

    //SET
    public void setTitle(String title){
        this.title = title;
    }

    public void setBudget(int budget){
        this.budget = budget;
    }

    public void setPeople(int people) {
        this.people = people;
    }
    public void setPid(int PID) {
        this.PID = PID;
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

    public int getBudget(){
        return this.budget;
    }

    public int getPeople() {
        return this.people;
    }
    public int getPid() {
        return this.PID;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

}
