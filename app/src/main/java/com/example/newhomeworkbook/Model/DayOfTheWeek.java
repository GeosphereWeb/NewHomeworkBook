package com.example.newhomeworkbook.Model;

import android.util.Log;

import com.example.newhomeworkbook.R;

public enum DayOfTheWeek implements KalenderDayModel{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    private static final DayOfTheWeek[] values = DayOfTheWeek.values();//cache for optimization

    public static final DayOfTheWeek getDayOfTheWeek(int value) {
        try {
            return values[value];//OOB might get triggered
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("WERNER", e.toString());
            return values[0];
        }
    }

    public int getNameReferenceAsShortName() {
        switch (this) {
            case MONDAY:
                return R.string.MON;
            case TUESDAY:
                return R.string.TUE;
            case WEDNESDAY:
                return R.string.WED;
            case THURSDAY:
                return R.string.THU;
            case FRIDAY:
                return R.string.FRI;
            case SATURDAY:
                return R.string.SAT;
            case SUNDAY:
                return R.string.SUN;
            default:
                return 0;
        }
    }

    public int getNameReferenceAsLongName() {
        switch (this) {
            case MONDAY:
                return R.string.MONDAY;
            case TUESDAY:
                return R.string.TUESDAY;
            case WEDNESDAY:
                return R.string.WEDNESDAY;
            case THURSDAY:
                return R.string.THURSDAY;
            case FRIDAY:
                return R.string.FRIDAY;
            case SATURDAY:
                return R.string.SATURDAY;
            case SUNDAY:
                return R.string.SUNDAY;
            default:
                return 0;
        }
    }
}