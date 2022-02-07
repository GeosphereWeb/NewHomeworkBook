package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.calendar.model.CalendarWeekModel;
import com.example.newhomeworkbook.databinding.CalenderWeekWithKwViewBinding;


public class CalenderWeekWithKwItemView extends ConstraintLayout {
    private RecyclerView calenderWeekItemRecyclerView;
    private TextView calenderWeekTextView;
    private CalendarWeekModel calenderWeekModel;

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

        calenderWeekItemRecyclerView = viewBinding.calenderWeekRecyclerView;
        calenderWeekItemRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        calenderWeekItemRecyclerView.setAdapter(new WeekItemsAdapter());

        calenderWeekTextView = viewBinding.kalenderWeekTextView;


        // TESTBEREICH
        setCalenderWeekModel(new CalendarWeekModel(2022, 12));
//        calenderWeekItemRecyclerView.setCalenderWeekModel(CalenderDayModelManager.createCalenderWeekModel(2022, Month.FEBRUARY, 5));
    }

    public void setCalenderWeekModel(CalendarWeekModel calenderWeekModel) {
        this.calenderWeekModel = calenderWeekModel;
        calenderWeekItemRecyclerView.getAdapter().notifyDataSetChanged();

    }

    ///////////////////////////////////////////////////////////////////////////
    // Inner Class
    ///////////////////////////////////////////////////////////////////////////
    private class WeekItemsAdapter extends RecyclerView.Adapter<CalenderDayViewHolder> {

        @NonNull
        @Override
        public CalenderDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (calenderWeekModel != null) {
                CalendarDayItemView dayItemView = new CalendarDayItemView(parent.getContext());
                return new CalenderDayViewHolder(dayItemView);
            } else return null;
        }

        @Override
        public void onBindViewHolder(@NonNull CalenderDayViewHolder holder, int position) {
            // FIXME: 07.02.2022 
            holder.getDayItemView().setDatumString("11");
        }

        @Override
        public int getItemCount() {
            // FIXME: 07.02.2022 
            return 7;
        }
    }

    private class CalenderDayViewHolder extends RecyclerView.ViewHolder {

        private final CalendarDayItemView dayItemView;

        public CalenderDayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayItemView = (CalendarDayItemView) itemView;
        }

        public CalendarDayItemView getDayItemView() {
            return dayItemView;
        }
    }

}
