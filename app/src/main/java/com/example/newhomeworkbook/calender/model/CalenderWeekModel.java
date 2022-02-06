package com.example.newhomeworkbook.calender.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

/**
 * CalenderWeekModel räpresentiert eine oder mehrere Kalenderwochen.
 */
public class CalenderWeekModel {
    private static  WeekFields weekFields = WeekFields.of(Locale.getDefault());
    // Or use a specific locale, or configure your own rules
    private static final TemporalField woy = weekFields.weekOfWeekBasedYear();


    private ArrayList<AbstractCalenderWeek> calenderWeeks = new ArrayList<>();

    /**
     * @param year
     * @param weekNumber
     */
    private CalenderWeekModel(int year, int weekNumber) {
        calenderWeeks.add(createCalenderWeek(year, weekNumber));
    }

    /**
     * @param year
     * @param month
     */
    public CalenderWeekModel(int year, Month month) {
        // erster und letzter Tag des Monats
        LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue()).withDayOfMonth(1);
        LocalDate lastDayInMonth = firstDayInMonth.withDayOfMonth(firstDayInMonth.lengthOfMonth());

        // Erste und letzte Wochennummer des Monats
        int firstWeekNr = firstDayInMonth.get(woy);
        int lastWeekNr = lastDayInMonth.get(woy);

        for (int i = firstWeekNr; i <= lastWeekNr; i++) {

            CalenderWeek calenderWeek = createCalenderWeek(year, i);
            calenderWeeks.add(calenderWeek);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // innere Methoden
    ///////////////////////////////////////////////////////////////////////////
    /*
     *
     */
    private CalenderWeek createCalenderWeek(int year, int weekNumber) {
        LocalDate firstDayInWeek = LocalDate.now().withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), 1);

        ArrayList<CalenderDay> weekModel = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate localDate = firstDayInWeek.plusDays(i);
            CalenderDay calenderDay = new CalenderDay(localDate);
            weekModel.add(calenderDay);
        }

        CalenderWeek calenderWeek = new CalenderWeek(year, weekModel, weekNumber);
        return calenderWeek;
    }

    ///////////////////////////////////////////////////////////////////////////
    // INNERCLASS
    ///////////////////////////////////////////////////////////////////////////
    /*
     *
     */
    private class CalenderWeek extends AbstractCalenderWeek {

        private CalenderWeek(int year, ArrayList<CalenderDay> weekmodel, int weekNumber) {
            super(year, weekmodel, weekNumber);
        }

    }

}
