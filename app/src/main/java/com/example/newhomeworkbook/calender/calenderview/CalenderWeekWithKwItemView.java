package com.example.newhomeworkbook.calender.calenderview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.newhomeworkbook.calender.model.CalenderDayModel;
import com.example.newhomeworkbook.calender.model.CalenderDayModelManager;
import com.example.newhomeworkbook.databinding.CalenderWeekWithKwViewBinding;

import java.time.Month;
import java.util.ArrayList;


public class CalenderWeekWithKwItemView extends ConstraintLayout {
    private CalenderWeekItemRecyclerView calenderWeekItemRecyclerView;
    private TextView calenderWeekTextView;

    public CalenderWeekWithKwItemView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CalenderWeekWithKwItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalenderWeekWithKwItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CalenderWeekWithKwItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        CalenderWeekWithKwViewBinding viewBinding = CalenderWeekWithKwViewBinding.inflate(LayoutInflater.from(context), this, false);
        ConstraintLayout rootView = viewBinding.getRoot();
        addView(rootView);

        calenderWeekItemRecyclerView = viewBinding.calenderWeekIRecyclerView;
        calenderWeekTextView = viewBinding.kalenderWeekTextView;

        calenderWeekItemRecyclerView.setCalenderWeekModel(CalenderDayModelManager.createCalenderWeekModel(2022, Month.FEBRUARY, 5));
    }

    public void setCalenderWeekModel(ArrayList<CalenderDayModel> weekModel){
        calenderWeekItemRecyclerView.setCalenderWeekModel(weekModel);
        calenderWeekTextView.setText("KW 00");
    }

}
