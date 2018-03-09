package com.example.macbookair.stepcounter;

/**
 * Created by macbookair on 2018-03-03.
 */

public class StepCounter {

    private String step;
    private long id;
    private String date;
    private String name;
    private int stepgoal;

    @Override
    public String toString() {
        return "Date : " + date + " " + "Steps : " + step;
    }


    public String getSteps() {
        return step;
    }

    public String setSteps(String steps) {
        this.step = steps;
        return steps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String setDate(String date) {
        this.date = date;
        return date;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public int getStepgoal() {
        return stepgoal;
    }

    public int setStepgoal(int stepgoal) {
        this.stepgoal = stepgoal;
        return stepgoal;
    }
}
