package com.example.newhomeworkbook.calendar.model;

import java.util.ArrayList;

/**
 * Repräsentiert ein Wochentags-Modell. Die Einzelnen Tage stehen in einem Array mit {@link CalendarDay} zur Verfügung.
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

    /**
     * Lifert die Wochennummer zurück. 1..52
     * @return
     */
    public int getWeekNumber() {
        return weekNumber;
    }

    public ArrayList<CalendarDay> getCalendarDaysInWeek() {
        return calendarDays;
    }

    public CalendarDay getCalendarDayAt(int i) {
        return calendarDays.get(i);
    }

    public int getYear() {
        return year;
    }
}