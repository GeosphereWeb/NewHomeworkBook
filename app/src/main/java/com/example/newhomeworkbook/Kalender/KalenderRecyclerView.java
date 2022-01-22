package com.example.newhomeworkbook.Kalender;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.YearMonth;

public class KalenderRecyclerView extends RecyclerView {
    private YearMonth aktMonat;
    private KalenderViewAdapter kalenderViewAdapter;
    private GridLayoutManager gridLayoutManager;

    public KalenderRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public KalenderRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KalenderRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        gridLayoutManager = new GridLayoutManager(this.getContext(), 7);
        this.setLayoutManager(gridLayoutManager);
        aktMonat = YearMonth.now();
        kalenderViewAdapter = new KalenderViewAdapter(aktMonat);
        this.setAdapter(kalenderViewAdapter);
    }

    public void setAktMonat(YearMonth month) {
        this.aktMonat = month;
        kalenderViewAdapter.setAktMonth(month);
        kalenderViewAdapter.notifyDataSetChanged();
    }

    public YearMonth getAktMonat() {
        return aktMonat;
    }
}
