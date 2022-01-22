package com.example.newhomeworkbook.Kalender;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.newhomeworkbook.databinding.ViewKalenderBinding;

import java.time.LocalDate;
import java.time.YearMonth;

public class KalenderView extends ConstraintLayout {
    private ViewKalenderBinding inflate;
    private LocalDate aktDatum;
    private YearMonth aktMonat;


    public KalenderView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public KalenderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KalenderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public KalenderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        inflate = ViewKalenderBinding.inflate(LayoutInflater.from(context), this, false);
        View view = inflate.getRoot();
        addView(view);
        aktMonat = inflate.kalenderViewRecyclerview.getAktMonat();
        updateText();

        inflate.kalenderViewAddMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                aktMonat = inflate.kalenderViewRecyclerview.getAktMonat().plusMonths(1);
                inflate.kalenderViewRecyclerview.setAktMonat(aktMonat);
                updateText();
            }
        });

        inflate.kalenderViewMinMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                aktMonat = inflate.kalenderViewRecyclerview.getAktMonat().minusMonths(1);
                inflate.kalenderViewRecyclerview.setAktMonat(aktMonat);
                updateText();
            }
        });

    }

    /*

     */
    private void updateText() {
        inflate.kalenderViewTextJahr.setText(String.valueOf(aktMonat.getYear()));
        inflate.kalenderViewMonatsName.setText(String.valueOf(aktMonat.getMonth()));
    }
}
