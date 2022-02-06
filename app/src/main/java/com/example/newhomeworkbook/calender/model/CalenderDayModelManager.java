package com.example.newhomeworkbook.calender.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

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
    public static ArrayList<CalenderDay> createCalenderWeekModel(int year, Month month, int weekNumber) {
        ArrayList<CalenderDay> weekModel = new ArrayList<>();

        LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue())
                .withDayOfMonth(1);
        LocalDate lastDayInMonth = firstDayInMonth
                .withDayOfMonth(firstDayInMonth.lengthOfMonth());
        LocalDate firstDayInWeek = LocalDate.now().withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), 1);

        for (int i = 0; i < 7; i++) {
            LocalDate localDate = firstDayInWeek.plusDays(i);
            CalenderDay calenderDay = new CalenderDay(localDate);

            if (localDate.isBefore(firstDayInMonth)) {
                calenderDay.setDayInMonthStatus(DayInMonthStatus.PRE);
            } else if (localDate.isAfter(lastDayInMonth)) {
                calenderDay.setDayInMonthStatus(DayInMonthStatus.POST);
            } else {
                calenderDay.setDayInMonthStatus(DayInMonthStatus.IN);
            }

            weekModel.add(calenderDay);
        }

        weekModel.trimToSize();

        return weekModel;
    }

    public static ArrayList<CalenderDay> createCalenderMonthModel(int year, Month month) {
        ArrayList<CalenderDay> monthModel = new ArrayList<>();

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
            ArrayList<CalenderDay> calenderWeekModel = createCalenderWeekModel(year, month, i);
            monthModel.addAll(calenderWeekModel);
        }

        return monthModel;
    }
}
