package com.example.newhomeworkbook.calendar.model;

import java.util.ArrayList;

public abstract class Weekmodel {
    private final int weeknumber;
    private final int year;


    protected Weekmodel(int year, int weeknumber) {
        this.year = year;
        this.weeknumber = weeknumber;
    }

    public abstract ArrayList<CalendarDay> getCalendarDays();

    public int getWeeknumber() {
        return weeknumber;
    }

    public int getYear() {
        return year;
    }
}
