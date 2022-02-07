package com.example.newhomeworkbook.calendar.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

/**
 * CalenderWeekModel repräsentiert eine oder mehrere Kalenderwochen.
 */
public class CalendarWeekModel {
    private static WeekFields weekFields = WeekFields.of(Locale.getDefault());
    // Or use a specific locale, or configure your own rules
    private static final TemporalField woy = weekFields.weekOfWeekBasedYear();

    private final ArrayList<CalendarWeek> calendarWeeks = new ArrayList<>();

    /**
     * @param year Das Jahr als Integer. Z.B. '2022'
     * @param weekNumber Wochennummer als Integer. '1' entspricht die erste Woche im Jahr
     */
    public CalendarWeekModel(int year, int weekNumber) {
        calendarWeeks.add(createCalendarWeek(year, weekNumber));
    }

    /**
     * @param year Das Jahr als Integer. Z.B. '2022'
     * @param month Monat{@link Month} als Monat
     */
    public CalendarWeekModel(int year, Month month) {
        // erster und letzter Tag des Monats
        LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue()).withDayOfMonth(1);
        LocalDate lastDayInMonth = firstDayInMonth.withDayOfMonth(firstDayInMonth.lengthOfMonth());

        // Erste und letzte Wochennummer des Monats
        int firstWeekNrInMonth = firstDayInMonth.get(woy);
        int lastWeekNrInMonth = lastDayInMonth.get(woy);

        for (int i = firstWeekNrInMonth; i <= lastWeekNrInMonth; i++) {

            CalendarWeek calendarWeek = createCalendarWeek(year, i);
            calendarWeeks.add(calendarWeek);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getter und Setter
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Die Methode getModel liefert das ArrayList Objekt, die mit {@link CalendarWeek} befüllt sind aus.
     * @return ArrayList mit {@link CalendarWeek}-Items
     */
    public ArrayList<CalendarWeek> getCalenderWeeks() {
        return calendarWeeks;
    }

    public CalendarWeek get(int position) {
        return calendarWeeks.get(position);
    }

    public int size(){
        return calendarWeeks.size();
    }

    ///////////////////////////////////////////////////////////////////////////
    // innere Methoden
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    private CalendarWeek createCalendarWeek(int year, int weekNumber) {
        LocalDate firstDayInWeek = LocalDate.now().withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), 1);

        ArrayList<CalendarDay> weekModel = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate localDate = firstDayInWeek.plusDays(i);
            CalendarDay calendarDay = new CalendarDay(localDate);
            weekModel.add(calendarDay);
        }

        return new CalendarWeek(year, weekModel, weekNumber);
    }



    ///////////////////////////////////////////////////////////////////////////
    // INNERCLASS
    ///////////////////////////////////////////////////////////////////////////



}
