package com.example.newhomeworkbook.calendar.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model für ein Kalendertag
 */
public class CalendarDay {
    private LocalDate localDate;
    private ArrayList calendarEvents;
    private DayInMonthStatus dayInMonthStatus;

    public CalendarDay(LocalDate localDate){
        this.localDate = localDate;
        calendarEvents = new ArrayList<CalendarEvent>();
    }

    /**
     * Vergleicht das zu  überprüfenden Datum auf das des Objektes
     * @param dateToCompare
     * @return
     */
    public boolean isLocalDateActualDate(LocalDate dateToCompare){
        boolean equal = this.localDate.isEqual(dateToCompare);
        return equal;
    }

    /**
     * Zeigt an, ob Kalenderereignisse vorliegen
     * @return
     */
    public boolean hasCalendarEvents(){
        return calendarEvents.isEmpty();
    }

    public ArrayList<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "CalenderDayModel{" +
                "localDate=" + localDate +
                '}';
    }

    public DayInMonthStatus getDayInMonthStatus() {
        return dayInMonthStatus;
    }

    public void setDayInMonthStatus(DayInMonthStatus dayInMonthStatus) {
        this.dayInMonthStatus = dayInMonthStatus;
    }
}
