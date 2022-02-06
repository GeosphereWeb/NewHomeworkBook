package com.example.newhomeworkbook.calender.model;

import java.util.ArrayList;

/**
 * Abstrakte Klasse einer Kalenderwoche.
 * Die Kalenderwoche binhaltet die Kalenderwochennummer als Integer und ein {@link ArrayList} von {@link CalenderDay}.
 *
 */
public abstract class AbstractCalenderWeek {
    private int calenderweek = 0;
    private int year;
    private ArrayList<CalenderDay> weekmodel;

    public AbstractCalenderWeek(int year, ArrayList<CalenderDay> weekmodel, int calenderweek) {
        this.calenderweek = calenderweek;
        this.weekmodel = weekmodel;
        this.year = year;
    }

    public int getCalenderweek() {
        return calenderweek;
    }

    public ArrayList<CalenderDay> getCalenderDays() {
        return weekmodel;
    }

    public void getCalenderDayAt(int i) {
        weekmodel.get(i);
    }
}
