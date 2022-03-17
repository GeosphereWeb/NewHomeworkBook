package de.geosphere.newhomeworkbook.calendar.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model für ein Kalendertag
 */
public class CalendarDay extends Day implements DayInMonthStatus, CalendarEvent {

    private ArrayList calendarEvents;
    private MonthStatus monthStatus;

    public CalendarDay(LocalDate localDate) {
        super(localDate);
        calendarEvents = new ArrayList<CalendarEvent>();

    }

    /**
     * Zeigt an, ob Kalenderereignisse vorliegen
     *
     * @return
     */
    public boolean hasCalendarEvents() {
        return calendarEvents.isEmpty();
    }

    /**
     * @return Kalenderereignisse vom Typ CalendarEvent
     */
    public ArrayList<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }

    /**
     * Methode zum Setzen des Status, ob der Tag noch im Aktuellen Monat ist, oder vorher oder nachher
     *
     * @param monthStatus
     */
    public void setDayInMonthStatus(MonthStatus monthStatus) {
        this.monthStatus = monthStatus;
    }

    @Override
    public MonthStatus getDayInMonthStatus() {
        return monthStatus;
    }
}
