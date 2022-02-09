package com.example.newhomeworkbook.calendar.model;

import java.time.LocalDate;

public class Day {
    protected LocalDate localDate;
    protected boolean today;

    public Day(LocalDate localDate) {
        this.localDate = localDate;
        today = isLocalDateActualDate(LocalDate.now());
    }

    /**
     * Vergleicht das zu  überprüfenden Datum auf das des Objektes
     *
     * @param dateToCompare
     * @return
     */
    public boolean isLocalDateActualDate(LocalDate dateToCompare) {
        boolean equal = this.localDate.isEqual(dateToCompare);
        return equal;
    }

    public boolean isToday() {
        return today;
    }

    /**
     * Gibt das aktuelle Datum als {@link LocalDate} Objekt zurück
     *
     * @return aktuelles Datum
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Gibt den Tag als {@link Integer} zurück.
     * 1..31
     *
     * @return
     */
    public int getDay() {
        return localDate.getDayOfMonth();
    }

    @Override
    public String toString() {
        return "Day{" +
                "localDate=" + localDate +
                '}';
    }


}
