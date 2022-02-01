package com.example.newhomeworkbook.calender;

import android.content.Context;
import android.content.res.ColorStateList;
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
import com.example.newhomeworkbook.calender.model.CalenderDayModel;
import com.example.newhomeworkbook.databinding.CalenderDayViewBinding;

public class CalendarDayItemView extends ConstraintLayout {
    private static final int LABLE_TURNED_IN = R.drawable.ic_baseline_turned_in_24;
    private static final int LABLE_TURNED_IN_NOT = R.drawable.ic_baseline_turned_in_not_24;

    private CalenderDayModel thisDay;

    private TextView dayTextView;
    private ImageView label;
    private GradientDrawable dayBackground;

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
        dayBackground = (GradientDrawable) dayTextView.getBackground();

        this.hideLabel();
        this.showRing(false);

//        background.setColor(Color.YELLOW);

//        // SetText
//        if (thisDay != null) {
//            dayTextView.setText(String.valueOf(thisDay.getLocalDate().getDayOfMonth()));
//
//            if (thisDay.isLocalDateActualDate(nowDay)) { ;
//                this.getBackground().setColor(Color.BLUE);
//            }else {
//                this.getBackground().setColor(Color.YELLOW);
//            }
//        }


//        // Listener
//        rootView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                label.setColorFilter(ContextCompat.getColor(getContext(), R.color.design_default_color_error));
//            }
//        });

        rootView.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                //                    label.setColorFilter(ContextCompat.getColor(getContext(), R.color.test_farbe1));
                if (b) showRing(true);
                else {
//                    label.setColorFilter(ContextCompat.getColor(getContext(), R.color.Custom0));
                    showRing(false);
                }

            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////
    // Zugriffsmethoden
    ///////////////////////////////////////////////////////////////////////////
    public void showLabel(int lableTurnedConst) {
        label.setImageResource(lableTurnedConst);
        label.setVisibility(VISIBLE);
    }

    public void hideLabel() {
        label.setVisibility(INVISIBLE);
    }

    public void showRing(boolean visibility) {
        //Stroke
        //show Ring
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled} // enabled
//                new int[] {-android.R.attr.state_enabled}, // disabled
//                new int[] {-android.R.attr.state_checked}, // unchecked
//                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[]{
                ContextCompat.getColor(getContext(), R.color.test_farbe1)
//                Color.RED,
//                Color.GREEN,
//                Color.BLUE
        };

        ColorStateList myList = new ColorStateList(states, colors);
        if (visibility) {
            dayBackground.setStroke(6, myList);
        } else {
            dayBackground.setStroke(0, myList);
        }
    }

    public void setDayBackgroundColor(boolean setbackground){
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled} // enabled
//                new int[] {-android.R.attr.state_enabled}, // disabled
//                new int[] {-android.R.attr.state_checked}, // unchecked
//                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] colorsOn = new int[]{
                ContextCompat.getColor(getContext(), R.color.test_farbe1)
//                Color.RED,
//                Color.GREEN,
//                Color.BLUE
        };

        int[] colorsOff = new int[]{
                ContextCompat.getColor(getContext(), R.color.Custom0)
//                Color.RED,
//                Color.GREEN,
//                Color.BLUE
        };

        ColorStateList myList;
        if (setbackground){
            myList = new ColorStateList(states, colorsOn);
        }else {
            myList = new ColorStateList(states, colorsOff);
        }
        dayBackground.setColor(myList);
    }

    public void setDatumText(String datumText) {
        dayTextView.setText(datumText);
    }

    public GradientDrawable getDayBackground() {
        return dayBackground;
    }
}
