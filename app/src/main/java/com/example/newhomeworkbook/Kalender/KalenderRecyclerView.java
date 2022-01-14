package com.example.newhomeworkbook.Kalender;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.newhomeworkbook.NewHomeworkBook;
import com.example.newhomeworkbook.databinding.ViewKalenderBinding;

import java.time.LocalDateTime;
import java.time.YearMonth;

public class KalenderRecyclerView extends ConstraintLayout {
    private static LocalDateTime aktDatum = NewHomeworkBook.getAktDatum();
    private View mainArea;

    private YearMonth aktMonat;

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

    public KalenderRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     *
     */
    protected void init(Context context) {
        // Inflate the Layout
        ViewKalenderBinding binding = ViewKalenderBinding.inflate(LayoutInflater.from(context), this, false);
        View root = binding.getRoot();
        addView(root);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 7);
        binding.kalenderViewRecyclerview.setLayoutManager(gridLayoutManager);

        this.aktMonat = YearMonth.now();

        binding.kalenderViewRecyclerview.setAdapter(new KalenderViewAdapter(aktMonat));
    }
// TODO:
//    public void setSpanCount(int spanCount) {
//        setSpanCount(spanCount);
//    }
}
