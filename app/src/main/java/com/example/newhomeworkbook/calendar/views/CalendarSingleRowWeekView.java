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

import com.example.newhomeworkbook.calendar.model.CalendarWeek;
import com.example.newhomeworkbook.calendar.model.CalendarWeekManager;
import com.example.newhomeworkbook.databinding.CalenderWeekWithKwViewBinding;


public class CalendarSingleRowWeekView extends ConstraintLayout {
    private CalendarWeek calendarWeek;

    private RecyclerView calendarWeekItemRecyclerView;
    private TextView calendarWeekTextView;


    public CalendarSingleRowWeekView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CalendarSingleRowWeekView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarSingleRowWeekView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CalendarSingleRowWeekView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        CalenderWeekWithKwViewBinding viewBinding = CalenderWeekWithKwViewBinding.inflate(LayoutInflater.from(context), this, false);
        ConstraintLayout rootView = viewBinding.getRoot();
        addView(rootView);

        calendarWeekItemRecyclerView = viewBinding.calendarWeekRecyclerView;
        calendarWeekItemRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        calendarWeekItemRecyclerView.setAdapter(new WeekItemsAdapter());

        calendarWeekTextView = viewBinding.calendarWeekTextView;


        if (calendarWeek == null) {
            ////
            // START Testbereich
            CalendarWeekManager calendarWeekManager = new CalendarWeekManager(2022, 12);
            setCalenderWeek(calendarWeekManager.get(0));
            // ENDE Testbereich
        }

    }

    /**
     * Setz ein neues Modell für die Kalenderwoche
     *
     * @param calendarWeek
     */
    public void setCalenderWeek(CalendarWeek calendarWeek) {
        WeekItemsAdapter adapter = (WeekItemsAdapter) calendarWeekItemRecyclerView.getAdapter();
        adapter.setCalenderWeek(calendarWeek);

        calendarWeekTextView.setText(calendarWeek.getWeekNumber() + "");
    }

    public TextView getCalendarWeekTextView() {
        return calendarWeekTextView;
    }

    private RecyclerView getCalendarWeekItemRecyclerView() {
        return calendarWeekItemRecyclerView;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Inner Class
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Darstellund der Wochentage
     */
    public class WeekItemsAdapter extends RecyclerView.Adapter<CalenderDayViewHolder> {

        public WeekItemsAdapter() {
            super();
        }

        public void setCalenderWeek(CalendarWeek calendarWeek) {
            CalendarSingleRowWeekView.this.calendarWeek = calendarWeek;
            this.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CalenderDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CalendarDayItemView dayItemView = new CalendarDayItemView(parent.getContext());
            return new CalenderDayViewHolder(dayItemView);
        }

        @Override
        public void onBindViewHolder(@NonNull CalenderDayViewHolder holder, int position) {
            holder.dayItemView.setDatumString(calendarWeek.getCalendarDayAt(position).getDay() + "");
        }

        @Override
        public int getItemCount() {
            return calendarWeek.getCalendarDaysInWeek().size();
        }
    }

    /**
     * Einzelner Kalendertag als View
     */
    public class CalenderDayViewHolder extends RecyclerView.ViewHolder {

        private final CalendarDayItemView dayItemView;

        public CalenderDayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayItemView = (CalendarDayItemView) itemView;
        }

    }

}
