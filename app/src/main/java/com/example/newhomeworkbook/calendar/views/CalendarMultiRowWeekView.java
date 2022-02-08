package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.calendar.model.CalendarWeek;
import com.example.newhomeworkbook.calendar.model.CalendarWeekManager;

import java.time.Month;

public class CalendarMultiRowWeekView extends RecyclerView {
    CalendarWeekManager calendarWeekManager;
    private MultiWeeksAdapter multiWeeksAdapter;

    public CalendarMultiRowWeekView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CalendarMultiRowWeekView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarMultiRowWeekView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        multiWeeksAdapter = new MultiWeeksAdapter();
        this.setAdapter(multiWeeksAdapter);

        if (calendarWeekManager == null) {
            ////
            // START Testbereich
            CalendarWeekManager calendarWeekManager = new CalendarWeekManager(2022, Month.APRIL);
            setCalendarWeekManager(calendarWeekManager);
            // ENDE Testbereich
        }
    }

    public void setCalendarWeekManager(CalendarWeekManager calendarWeekManager) {
        multiWeeksAdapter.setCalendarWeekManager(calendarWeekManager);
    }

    private class MultiWeeksAdapter extends Adapter<MyViewHolder> {

        public void setCalendarWeekManager(CalendarWeekManager calendarWeekManager) {
            CalendarMultiRowWeekView.this.calendarWeekManager = calendarWeekManager;
            this.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CalendarSingleRowWeekView calendarSingleRowWeekView = new CalendarSingleRowWeekView(parent.getContext());
            return new MyViewHolder(calendarSingleRowWeekView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            // FIXME: 08.02.2022 Läuft auf Fehler. vielleicht ist das kalendermanager null
            if (calendarWeekManager != null) {
                CalendarWeek calendarWeekModel = calendarWeekManager.get(position);
                holder.calendarSingleRowWeekView.setCalenderWeek(calendarWeekModel);
            }
        }

        @Override
        public int getItemCount() {
            if (calendarWeekManager != null) {
                return calendarWeekManager.size();
            } else return 0;

        }
    }

    private class MyViewHolder extends ViewHolder {
        CalendarSingleRowWeekView calendarSingleRowWeekView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            CalendarSingleRowWeekView calendarSingleRowWeekView = (CalendarSingleRowWeekView) itemView;
        }
    }

}
