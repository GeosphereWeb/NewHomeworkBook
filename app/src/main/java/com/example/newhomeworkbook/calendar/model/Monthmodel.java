package com.example.newhomeworkbook.calendar.model;

import java.time.YearMonth;
import java.util.ArrayList;

public abstract class Monthmodel {

    private final YearMonth yearMonth;

    public Monthmodel(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public abstract ArrayList<Weekmodel> getWeekmodels();

    public YearMonth getYearMonth() {
        return yearMonth;
    }
}
