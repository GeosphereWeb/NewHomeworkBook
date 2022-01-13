package com.example.newhomeworkbook.Kalender;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.NewHomeworkBook;

import java.time.LocalDateTime;
import java.time.YearMonth;

public class KalenderRecyclerView extends RecyclerView {
    private static LocalDateTime aktDatum = NewHomeworkBook.getAktDatum();
    private GridLayoutManager layoutManager;

    private YearMonth aktMonat;

    public KalenderRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public KalenderRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KalenderRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     *
     */
    protected void init() {
        // Layoutmanager
        layoutManager = new GridLayoutManager(this.getContext(), 7);
        this.setLayoutManager(layoutManager);

        this.aktMonat = YearMonth.now();

        this.setAdapter(new KalenderViewAdapter(aktMonat));
    }

    /* Delegated Methods */

    public void setSpanCount(int spanCount) {
        layoutManager.setSpanCount(spanCount);
    }
}
