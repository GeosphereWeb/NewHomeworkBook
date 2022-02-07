package com.example.newhomeworkbook.calendar.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Klasse ist Deprecated. Bitte neue Klasse {@link CalendarWeekModel} benutzen
 */
@Deprecated
public class CalenderDayModelManager {
    private static final WeekFields weekFields = WeekFields.of(Locale.getDefault());

    private CalenderDayModelManager() {
        LocalDate localWeek = LocalDate.now().with(weekFields.weekOfYear(), 1).with(weekFields.dayOfWeek(), 1);
    }

    /**
     * Liefert eine Woche als Array von CalenderDayModel zurück
     *
     * @param year
     * @param month
     * @param weekNumber
     * @return
     */
    public static ArrayList<CalendarDay> createCalenderWeekModel(int year, Month month, int weekNumber) {
        ArrayList<CalendarDay> weekModel = new ArrayList<>();

        LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue())
                .withDayOfMonth(1);
        LocalDate lastDayInMonth = firstDayInMonth
                .withDayOfMonth(firstDayInMonth.lengthOfMonth());
        LocalDate firstDayInWeek = LocalDate.now().withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), 1);

        for (int i = 0; i < 7; i++) {
            LocalDate localDate = firstDayInWeek.plusDays(i);
            CalendarDay calendarDay = new CalendarDay(localDate);

            if (localDate.isBefore(firstDayInMonth)) {
                calendarDay.setDayInMonthStatus(DayInMonthStatus.BEFORE_ACTUAL_MONTH);
            } else if (localDate.isAfter(lastDayInMonth)) {
                calendarDay.setDayInMonthStatus(DayInMonthStatus.AFTER_ACTUAL_MONTH);
            } else {
                calendarDay.setDayInMonthStatus(DayInMonthStatus.IN_ACTUAL_MONTH);
            }

            weekModel.add(calendarDay);
        }

        weekModel.trimToSize();

        return weekModel;
    }

    public static ArrayList<CalendarDay> createCalenderMonthModel(int year, Month month) {
        ArrayList<CalendarDay> monthModel = new ArrayList<>();

        // Or use a specific locale, or configure your own rules
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

        LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue())
                .withDayOfMonth(1);
        LocalDate lastDayInMonth = LocalDate.now().withYear(year)
                .withMonth(month.getValue())
                .withDayOfMonth(LocalDate.now().withYear(year).withMonth(month.getValue()).lengthOfMonth());

//        int firstWeekNr = firstDayInMonth.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
//        int lastWeekNr = lastDayInMonth.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int firstWeekNr = firstDayInMonth.get(woy);
        int lastWeekNr = lastDayInMonth.get(woy);

        for (int i = firstWeekNr; i <= lastWeekNr; i++) {
            ArrayList<CalendarDay> calenderWeekModel = createCalenderWeekModel(year, month, i);
            monthModel.addAll(calenderWeekModel);
        }

        return monthModel;
    }
}
