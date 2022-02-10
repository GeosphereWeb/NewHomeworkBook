package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.newhomeworkbook.R;
import com.example.newhomeworkbook.databinding.CalenderDayViewBinding;

public class CalendarDayItemView extends ConstraintLayout {
    private static final int LABEL_TURNED_IN = R.drawable.ic_baseline_turned_in_24;
    private static final int LABEL_TURNED_IN_NOT = R.drawable.ic_baseline_turned_in_not_24;

    private TextView dayTextView;
    private ImageView label;
    private GradientDrawable gradientDrawable;

    private boolean aktualDay;

    public CalendarDayItemView(Context context) {
        super(context);
        init(context);
    }

    public CalendarDayItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarDayItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Initialisierung
    ///////////////////////////////////////////////////////////////////////////
    private void init(Context context) {

        // Initialisierung
        CalenderDayViewBinding viewBinding = CalenderDayViewBinding.inflate(LayoutInflater.from(context), this, false);
        ConstraintLayout rootView = viewBinding.getRoot();
        addView(rootView);

        dayTextView = viewBinding.calenderDayTextView;
        label = viewBinding.label;
        gradientDrawable = (GradientDrawable) dayTextView.getBackground();

        this.setLabelVisibility(false);
        this.showRing(false);

        // Listener
        rootView.setOnFocusChangeListener((view, visibility) -> showRing(visibility));
        rootView.clearFocus();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Zugriffsmethoden
    ///////////////////////////////////////////////////////////////////////////

    // // TEXT
    public void setDatumString(String datumText) {
        dayTextView.setText(datumText);
    }

    public void setLabelVisibility(Boolean visibility) {
        if (visibility) {
            label.setVisibility(VISIBLE);
        } else {
            label.setVisibility(INVISIBLE);
        }

    }

    // // RING
    private void showRing(boolean visibility) {
        //Stroke
        if (visibility && !aktualDay) {
            gradientDrawable.setStroke(6, ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_ActualDay));
        } else {
            gradientDrawable.setStroke(0, ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_ActualDay));
        }
    }

    private void showBgrHighlighted(boolean highlight) {
        gradientDrawable.mutate();
        if (highlight) {
            if (aktualDay) {
                dayTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.calender_light_day_bubble_onActualDay));
            }
            gradientDrawable.setColor(ContextCompat.getColor(getContext(),
                    R.color.calender_light_day_bubble_ActualDay));
            dayTextView.setTextColor(ContextCompat.getColor(getContext(),
                    R.color.calender_light_day_bubble_ActualDay));
        }
    }

    public boolean isAktualDay() {
        return aktualDay;
    }

    public void setAsAktualDay(boolean aktualDay) {
        this.aktualDay = aktualDay;
    }


    // // LABEL
    private void setLabelType(int labelTurnedConst) {
        label.setImageResource(labelTurnedConst);
    }

    // // TEXTVIEW
    private void setDayTextViewColor() {
        if (aktualDay){
            dayTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_onActualDay
            ));
        } else {
            dayTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.md_theme_light_onBackground
               ));
        }

    }

    // // HINTERGRUND
    private GradientDrawable getGradientDrawable() {
        return gradientDrawable;
    }
}
