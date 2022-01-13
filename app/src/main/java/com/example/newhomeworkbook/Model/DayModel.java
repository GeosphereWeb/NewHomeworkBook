package com.example.newhomeworkbook.Model;

import java.time.LocalDate;

public class DayModel implements KalenderDayModel{
    private LocalDate date;
    private Zugehoerigkeit zugehoerigkeit;

    public DayModel(LocalDate date, Zugehoerigkeit zugehoerigkeit) {
        this.date = date;
        this.zugehoerigkeit = zugehoerigkeit;
    }

    public LocalDate getDate() {
        return date;
    }

    public Zugehoerigkeit getZugehoerigkeit() {
        return zugehoerigkeit;
    }

    public void setZugehoerigkeit(Zugehoerigkeit zugehoerigkeit) {
        this.zugehoerigkeit = zugehoerigkeit;
    }
}
