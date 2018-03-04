package com.example.macbookair.stepcounter;

/**
 * Created by macbookair on 2018-03-03.
 */

public class Steg {

    private String step;
    private long id;
    private String date;

    @Override
    public String toString() {
        return step;
    }


    public String getSteps() {
        return step;
    }

    public void setSteps(String steps) {
        this.step = steps;
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

    public void setDate(String date) {
        this.date = date;
    }
}
