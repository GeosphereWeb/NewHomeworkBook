package com.example.newhomeworkbook.calendar.model;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
@Deprecated
public  class CalenderDaysModel {
    private ArrayList<CalendarDay> calenderModel = new ArrayList<>();

    private static final WeekFields weekFields = WeekFields.of(Locale.getDefault());
    // Or use a specific locale, or configure your own rules
    private static final TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

    private CalenderDaysModel() {
    }

    /**
     *
     * @param year
     * @param weekNumber
     * @return
     */
    @Deprecated
    public Calenderweek getInstance(int year, int weekNumber){
        ArrayList<CalendarDay> calenderWeekModel = createCalenderWeekModel(year, null, weekNumber);
        Calenderweek calenderweek = new Calenderweek(year, calenderWeekModel);
        return calenderweek;
    }

    /**
     *
     * @param year
     * @param month
     * @return
     */
    @Deprecated
    public static CalenderDaysModel getInstance(int year, Month month){
        CalenderDaysModel calenderDaysModel = new CalenderDaysModel();
        calenderDaysModel.calenderModel = createCalenderMonthModel(year, month);
        return calenderDaysModel;
    }



    /**
     * Liefert eine Woche als Array von CalenderDayModel zurück
     *
     * @param year
     * @param month
     * @param weekNumber
     * @return
     */

    @Deprecated
    private static ArrayList<CalendarDay> createCalenderWeekModel(int year, @Nullable Month month, int weekNumber) {
        ArrayList<CalendarDay> weekModel = new ArrayList<>();

        LocalDate firstDayInWeek = LocalDate.now().withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), 1);

        for (int i = 0; i < 7; i++) {
            LocalDate localDate = firstDayInWeek.plusDays(i);
            CalendarDay calendarDay = new CalendarDay(localDate);

            if (month != null) {
                LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue())
                        .withDayOfMonth(1);
                LocalDate lastDayInMonth = firstDayInMonth
                        .withDayOfMonth(firstDayInMonth.lengthOfMonth());

                if (localDate.isBefore(firstDayInMonth)) {
                    calendarDay.setDayInMonthStatus(DayInMonthStatus.BEFORE_ACTUAL_MONTH);

                } else if (localDate.isAfter(lastDayInMonth)) {
                    calendarDay.setDayInMonthStatus(DayInMonthStatus.AFTER_ACTUAL_MONTH);

                } else {
                    calendarDay.setDayInMonthStatus(DayInMonthStatus.IN_ACTUAL_MONTH);
                }
            }
            
            weekModel.add(calendarDay);
        }

        weekModel.trimToSize();

        return weekModel;
    }

    /**
     *
     * @param year
     * @param month
     * @return
     */
    @Deprecated
    private static ArrayList<CalendarDay> createCalenderMonthModel(int year, Month month) {
        ArrayList<CalendarDay> monthModel = new ArrayList<>();

        LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue())
                .withDayOfMonth(1);
        LocalDate lastDayInMonth = LocalDate.now().withYear(year)
                .withMonth(month.getValue())
                .withDayOfMonth(LocalDate.now().withYear(year).withMonth(month.getValue()).lengthOfMonth());

        int firstWeekNr = firstDayInMonth.get(woy);
        int lastWeekNr = lastDayInMonth.get(woy);

        for (int i = firstWeekNr; i <= lastWeekNr; i++) {
            ArrayList<CalendarDay> calenderWeekModel = createCalenderWeekModel(year, month, i);
            monthModel.addAll(calenderWeekModel);
        }

        return monthModel;
    }

    ///////////////////////////////////////////////////////////////////////////
    // InnerClass SECTION
    ///////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    @Deprecated
    private class Calenderweek {
        private int calenderweek = 0;

        private ArrayList<CalendarDay> weekmodel;

        public Calenderweek(int year, ArrayList<CalendarDay> weekmodel) {
            this.calenderweek = calenderweek;
            this.weekmodel = weekmodel;
        }

        public int getCalenderweek() {
            return calenderweek;
        }

        public ArrayList<CalendarDay> getCalenderDays() {
            return weekmodel;
        }

        public void getCalenderDayAt(int i){
            weekmodel.get(i);
        }


    }

}
