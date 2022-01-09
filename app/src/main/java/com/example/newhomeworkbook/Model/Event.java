package com.example.newhomeworkbook.Model;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Locale;

public class Event {
    public enum Category {
        EXAM, HOMEWORK, OTHER;

       private Category() {
        }
    }

    private String title;
    private LocalDateTime start;
    @Nullable
    private LocalDateTime end;
    private String comment;

    public Event(String title, LocalDateTime start) {
        this.title = title;
        this.start = start;
    }
}
