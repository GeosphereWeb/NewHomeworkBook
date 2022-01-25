package com.example.newhomeworkbook.calender;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
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

    private TextView dayTextView;
    private ImageView label;

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

    private void init(Context context){
        CalenderDayViewBinding viewBinding = CalenderDayViewBinding.inflate(LayoutInflater.from(context), this, false);
        ConstraintLayout constraintLayout = viewBinding.getRoot();
        addView(constraintLayout);
        dayTextView = viewBinding.calenderDayTextView;
        label = viewBinding.label;

        constraintLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLabelToTurnedInNot();
                label.setColorFilter(ContextCompat.getColor(getContext(), R.color.test_farbe1));

                GradientDrawable background = (GradientDrawable) dayTextView.getBackground();
                background.setColor(Color.YELLOW);

                background.setStroke(0, null);

            }
        });
    }

    public TextView getDayTextView() {
        return dayTextView;
    }

    public ImageView getLabel() {
        return label;
    }

    public void setLabelToTurnedIn(){
        label.setImageResource(R.drawable.ic_baseline_turned_in_24);
    }
    public void setLabelToTurnedInNot(){
        label.setImageResource(R.drawable.ic_baseline_turned_in_not_24);
    }

    public void showLabel(boolean visibility){
        if(visibility){
            label.setVisibility(VISIBLE);
        } else {
            label.setVisibility(INVISIBLE);
        }
    }
}
