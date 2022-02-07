package com.example.newhomeworkbook.calendar.model;

import java.util.ArrayList;

/**
 * Repräsentiert ein WochentagsModell.
 */
public class CalendarWeek {
    private final int weekNumber;
    private final ArrayList<CalendarDay> calendarDays;
    private final int year;

    public CalendarWeek(int year, ArrayList<CalendarDay> calendarDays, int weekNumber) {
        this.year = year;
        this.weekNumber = weekNumber;
        this.calendarDays = calendarDays;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public ArrayList<CalendarDay> getCalendarDays() {
        return calendarDays;
    }

    public CalendarDay getCalendarDayAt(int i) {
        return calendarDays.get(i);
    }

    public int getYear() {
        return year;
    }
}