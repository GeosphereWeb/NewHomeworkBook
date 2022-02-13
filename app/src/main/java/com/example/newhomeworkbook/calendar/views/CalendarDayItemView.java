package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

    private boolean actualDay;

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

        rootView.setClickable(true);
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);

        dayTextView = viewBinding.calenderDayTextView;
        label = viewBinding.label;
        gradientDrawable = (GradientDrawable) dayTextView.getBackground();

        // Standartmäßig alles nicht darstellen
        this.setLabelVisibility(false);
        this.showRing(false);

        // Listener
        rootView.setOnFocusChangeListener((view, visibility) -> setAsSelected(visibility));
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("WERNER", "onClick: in CalenderDayItemView gecklicked");
            }
        });
        rootView.clearFocus();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Zugriffsmethoden
    ///////////////////////////////////////////////////////////////////////////
    public void setAsSelected(boolean selecting) {
        showRing(selecting);
        showBgrHighlighted(selecting);
        setDayTextViewColor(selecting);
    }


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
        if (visibility && !actualDay) {
            gradientDrawable.setStroke(6, ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_ActualDay));
        } else {
            gradientDrawable.setStroke(0, ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_ActualDay));
        }
    }

    private void showBgrHighlighted(boolean highlight) {
        gradientDrawable.mutate();
        if (highlight) {
            if (actualDay) {
                gradientDrawable.setColor(ContextCompat.getColor(getContext(),
                        R.color.calender_light_day_bubble_ActualDay));
            }

        } else {
//            int c = getResources().getColor(android.R.color.primary_text_dark);
//            dayTextView.setTextColor(ContextCompat.getColor(getContext(),
//                    android.R.color.primary_text_light));
//            dayTextView.setTextColor(ContextCompat.getColor(getContext(),
//                    android.R.color.primary_text_light));
            gradientDrawable.setColor(Color.TRANSPARENT);
        }
    }

    // // TEXTVIEW
    private void setDayTextViewColor(boolean highlighted) {
        dayTextView.setTextColor(ContextCompat.getColor(getContext(),
                android.R.color.primary_text_light));
        if (highlighted) {
            if (actualDay){
                dayTextView.setTextColor(ContextCompat.getColor(getContext(),  R.color.md_theme_light_surface));
                dayTextView.setTypeface(dayTextView.getTypeface(), Typeface.BOLD);
            }else {
                dayTextView.setTextColor(ContextCompat.getColor(getContext(),
                        android.R.color.primary_text_light));
                dayTextView.setTypeface(dayTextView.getTypeface(), Typeface.NORMAL);
            }
        } else {
            if (actualDay){
                dayTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_ActualDay));
                dayTextView.setTypeface(dayTextView.getTypeface(), Typeface.BOLD);
            }else {
                dayTextView.setTextColor(ContextCompat.getColor(getContext(),
                        android.R.color.primary_text_light));
            }
        }

        // textView.setTextColor(getResources().getColor(R.color.errorColor, getResources().newTheme()));

//        if (actualDay) {
//            dayTextView.setTypeface(dayTextView.getTypeface(), Typeface.BOLD);
//            dayTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.calender_light_day_bubble_ActualDay
//            ));
//        } else {
//            dayTextView.setTypeface(dayTextView.getTypeface(), Typeface.NORMAL);
//        }
//
//        if (highlighted && actualDay) {
//            dayTextView.setTextColor(ContextCompat.getColor(getContext(),
//                    R.color.calender_light_day_bubble_onActualDay));
//        } else {
//            dayTextView.setTextColor(ContextCompat.getColor(getContext(),
//                    android.R.color.primary_text_light));
//        }
    }

    public boolean isActualDay() {
        return actualDay;
    }

    public void setAsActualDay(boolean actualDay) {
        this.actualDay = actualDay;
        setDayTextViewColor(false);
    }

    // // LABEL
    private void setLabelType(int labelTurnedConst) {
        label.setImageResource(labelTurnedConst);
    }

    // // HINTERGRUND
    private GradientDrawable getGradientDrawable() {
        return gradientDrawable;
    }

    public void setOutAutActualMonth(boolean outAutActualMonth) {
        if (outAutActualMonth){
            dayTextView.setAlpha(1.0f);
        } else {
            dayTextView.setAlpha(0.2f);
        }
    }
}
