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

    /**
     *
     * @return Kalenderereignisse vom Typ CalendarEvent
     */
    public ArrayList<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }

    /**
     * Gibt das aktuelle Datum als {@link LocalDate} Objekt zurück
     * @return aktuelles Datum
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "CalenderDayModel{" +
                "localDate=" + localDate +
                '}';
    }

    /**
     * Gibt den Status zurück, ob der Tag noch im Aktuellen Monat ist, oder vorher oder nachher
     * @return
     */
    public DayInMonthStatus getDayInMonthStatus() {
        return dayInMonthStatus;
    }

    /**
     * Methode zum Setzen des Status, ob der Tag noch im Aktuellen Monat ist, oder vorher oder nachher
     * @param dayInMonthStatus
     */
    public void setDayInMonthStatus(DayInMonthStatus dayInMonthStatus) {
        this.dayInMonthStatus = dayInMonthStatus;
    }

    /**
     * Gibt den Tag als {@link Integer} zurück.
     * 1..31
     * @return
     */
    public int getDay() {
       return localDate.getDayOfMonth();
    }
}
