package com.example.newhomeworkbook.Model;

import java.time.LocalDate;

public class DayModel {
    private LocalDate date;
    private Zugehoerigkeit zugehoerigkeit;

    public DayModel(LocalDate date, Zugehoerigkeit zugehoerigkeit) {

        this.date = date;
        this.zugehoerigkeit = zugehoerigkeit;
    }
}
