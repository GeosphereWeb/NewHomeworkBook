package com.example.newhomeworkbook.calender.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class CalenderDayModelManager {
    private static WeekFields weekFields = WeekFields.of(Locale.getDefault());

    private CalenderDayModelManager() {
        LocalDate localWeek = LocalDate.now().with(weekFields.weekOfYear(), 1).with(weekFields.dayOfWeek(), 1);
    }

    /**
     * Liefert eine Woche als Array von CalenderDayModel zurück
     * @param year
     * @param weekNumber
     * @return
     */
    public static ArrayList<CalenderDayModel> createCalenderWeekModel(int year, int weekNumber) {
        ArrayList<CalenderDayModel> calenderWeek = new ArrayList<>();
        LocalDate firstDayInWeek = LocalDate.now()
                .withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), 1);

        for (int i = 0; i < 7; i++) {
            calenderWeek.add(new CalenderDayModel(firstDayInWeek.plusDays(i)));
        }
        calenderWeek.trimToSize();

        return calenderWeek;
    }
}
