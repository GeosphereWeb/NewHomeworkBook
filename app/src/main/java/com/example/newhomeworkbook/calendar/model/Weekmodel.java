package com.example.newhomeworkbook.calendar.model;

import android.content.Context;

import com.example.newhomeworkbook.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class Weekmodel {
    private final int year;
    private final int kw;
    private static ArrayList<DayOfWeek> weekFields;
    private static DayOfWeek firstDayOfWeek = WeekFields.ISO.getFirstDayOfWeek();


    protected Weekmodel(int year, LocalDate dayInWeek) {
        this.year = year;
        this.kw = dayInWeek.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public abstract ArrayList<CalendarDay> getCalendarDays();

    public int getYear() {
        return year;
    }

    public int getKw() {
        return kw;
    }

    /**
     * @return Liste mit Wochentagen {@link DayOfWeek}-Array
     */
    public static ArrayList<DayOfWeek> getDayOfWeekAsSortedList() {
        ArrayList<DayOfWeek> dayOfWeekArrayList = new ArrayList<>(7);

        // Erzeuge die Reihenfolge der Wochentage
        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = firstDayOfWeek.plus(i);
            dayOfWeekArrayList.add(i, dayOfWeek);
        }
        dayOfWeekArrayList.trimToSize();

        weekFields = dayOfWeekArrayList;

        return weekFields;
    }

    /**
     *
     * @param context
     * @return
     */
    public static ArrayList<String> getDayOfWeekAsStrings(Context context) {
        ArrayList<String> dayOfWeekArrayList = new ArrayList<>(7);

        // Erzeuge die Reihenfolge der Wochentage
        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = firstDayOfWeek.plus(i);
            switch (dayOfWeek.getValue()){
                case 1:
                    dayOfWeekArrayList.add(i,context.getString(R.string.MONDAY_short));
                    break;
                case 2:
                    dayOfWeekArrayList.add(i,context.getString(R.string.TUESDAY_short));
                    break;
                case 3:
                    dayOfWeekArrayList.add(i,context.getString(R.string.WEDNESDAY_short));
                    break;
                case 4:
                    dayOfWeekArrayList.add(i,context.getString(R.string.THURSDAY_short));
                    break;
                case 5:
                    dayOfWeekArrayList.add(i,context.getString(R.string.FRIDAY_short));
                    break;
                case 6:
                    dayOfWeekArrayList.add(i,context.getString(R.string.SATURDAY_short));
                    break;
                case 7:
                    dayOfWeekArrayList.add(i,context.getString(R.string.SUNDAY_short));
                    break;
                default:
                    break;
            }
        }
        dayOfWeekArrayList.trimToSize();
        return dayOfWeekArrayList;
    }

    /**
     * Gibt die Indexposition der Wochenendtage zurück
     * @return
     */
    public int[] getWeekendPosition() {
        if (weekFields != null) {
            int[] tmp = new int[2];
            tmp[0] = weekFields.indexOf(DayOfWeek.SATURDAY);
            tmp[1] = weekFields.indexOf(DayOfWeek.SUNDAY);
            return tmp;
        } else return null;
    }
}
