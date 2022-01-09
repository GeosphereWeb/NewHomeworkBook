package com.example.newhomeworkbook;

import android.app.Application;

import java.time.LocalDateTime;

public class NewHomeworkBook extends Application {


    private static LocalDateTime aktDatum;

    public NewHomeworkBook() {
        this.aktDatum = LocalDateTime.now();

    }

    public static LocalDateTime getAktDatum() {
        return aktDatum;
    }
}
