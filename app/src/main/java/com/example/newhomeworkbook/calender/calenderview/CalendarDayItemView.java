package com.example.newhomeworkbook.calender.calenderview;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.newhomeworkbook.R;
import com.example.newhomeworkbook.calender.model.CalenderDayModel;
import com.example.newhomeworkbook.databinding.CalenderDayViewBinding;

public class CalendarDayItemView extends ConstraintLayout {
    private static final int LABEL_TURNED_IN = R.drawable.ic_baseline_turned_in_24;
    private static final int LABEL_TURNED_IN_NOT = R.drawable.ic_baseline_turned_in_not_24;

    private static CalenderDayModel selectedDay;

    private CalenderDayModel thisDay;

    private TextView dayTextView;
    private ImageView label;
    private GradientDrawable gradientDrawable;

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

    public CalendarDayItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
        this.setRingVisibility(false);

        rootView.setOnFocusChangeListener((view, b) -> {
            if (b) {
                selectedDay = thisDay;
                setRingVisibility(true);
            } else {
                setRingVisibility(false);
            }
        });

        rootView.clearFocus();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Zugriffsmethoden
    ///////////////////////////////////////////////////////////////////////////
    public void setThisDay(CalenderDayModel thisDay) {
        this.thisDay = thisDay;
    }

    // // LABEL
    public void setLabelType(int labelTurnedConst) {
        label.setImageResource(labelTurnedConst);
    }

    public void setLabelVisibility(Boolean visibility) {
        if (visibility) {
            label.setVisibility(VISIBLE);
        } else {
            label.setVisibility(INVISIBLE);
        }

    }

    // // RING
    public void setRingVisibility(boolean visibility) {
        //Stroke
        if (visibility) {
            gradientDrawable.setStroke(6, ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_bgr_inverseActualDay));
        } else {
            gradientDrawable.setStroke(0, ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_bgr_inverseActualDay));
        }
    }

    // // TEXT
    public void setDatumString(String datumText) {
        dayTextView.setText(datumText);
    }

    // // TEXTVIEW
    public void setDayTextViewColor(@ColorInt int color) {
        dayTextView.setTextColor(color);
    }

    // // HINTERGRUND
    public GradientDrawable getGradientDrawable() {
        return gradientDrawable;
    }
    public void showBgrHighlighted(boolean highlight) {
        gradientDrawable.mutate();
        if (highlight) {
            gradientDrawable.setColor(ContextCompat.getColor(getContext(),
                    R.color.calender_light_day_bubble_bgr_actualDay));
        }
    }


}
