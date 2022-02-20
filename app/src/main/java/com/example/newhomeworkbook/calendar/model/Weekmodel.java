package com.example.newhomeworkbook.calendar.model;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;

public abstract class Weekmodel {
    private final int year;
    private final int kw;

    protected Weekmodel(int year, LocalDate dayInWeek) {
        this.year = year;
        this.kw = dayInWeek.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public abstract ArrayList<CalendarDay> getCalendarDays();

    public int getYear() {
        return year;
    }

    public int getKw() {
        return kw;
    }
}
