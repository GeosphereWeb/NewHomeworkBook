package com.example.newhomeworkbook.calender.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model für ein Kalendertag
 */
public class CalenderDayModel {
    private LocalDate localDate;
    ArrayList calenderEvents;

    public CalenderDayModel(LocalDate localDate){
        this.localDate = localDate;
        calenderEvents = new ArrayList<Kalenerereignisse>();
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
    public boolean hasCalenderevents(){
        return calenderEvents.isEmpty();
    }

    public ArrayList<Kalenerereignisse> getCalenderEvents() {
        return calenderEvents;
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
}
