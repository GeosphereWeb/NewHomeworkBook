package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.R;
import com.example.newhomeworkbook.calendar.model.CalendarWeek;
import com.example.newhomeworkbook.calendar.model.CalendarWeekManager;

import java.time.Month;

public class CalendarMultiRowWeekView extends RecyclerView {
    private CalendarWeekManager calendarWeekManager;
    private MultiWeeksAdapter multiWeeksAdapter;

    private static int CALENDAR_HEADER = 0;
    private static int CALENDAR_DAYS = 1;

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
            CalendarWeekManager calendarWeekManager = new CalendarWeekManager(2022, Month.FEBRUARY);
            setCalendarWeekManager(calendarWeekManager);
            // ENDE Testbereich
        }

    }

    public void setCalendarWeekManager(CalendarWeekManager calendarWeekManager) {
        multiWeeksAdapter.setCalendarWeekManager(calendarWeekManager);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Inner Class
    ///////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    private class MultiWeeksAdapter extends Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CalendarSingleRowWeekView calendarSingleRowWeekView = new CalendarSingleRowWeekView(parent.getContext());
            calendarSingleRowWeekView.setFocusable(true);
            calendarSingleRowWeekView.setClickable(true);
            calendarSingleRowWeekView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("WERNER", "onClick: remove Kalenderwoche");
                    calendarWeekManager.getCalenderWeeks().remove(2);
                }
            });
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_week_header, parent, false);

//            return new MyViewHolder(calendarSingleRowWeekView);
            MyViewHolder myViewHolder;
            if (viewType == CALENDAR_HEADER) {
                 myViewHolder = new MyViewHolder(headerView);
            } else {
                 myViewHolder = new MyViewHolder(calendarSingleRowWeekView);
            }
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if (holder.calendarSingleRowWeekView != null) {
                CalendarWeek calendarWeek = calendarWeekManager.get(position - 1);
                holder.calendarSingleRowWeekView.setCalenderWeek(calendarWeek);
            }
        }

        @Override
        public int getItemCount() {
            if (calendarWeekManager != null) {
                return calendarWeekManager.size() + 1;
            } else return 0;
        }

        @Override
        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
            if (position == 0) {
                return CALENDAR_HEADER;
            } else {
                return CALENDAR_DAYS;
            }
        }

        public void setCalendarWeekManager(CalendarWeekManager calendarWeekManager) {
            CalendarMultiRowWeekView.this.calendarWeekManager = calendarWeekManager;
            this.notifyDataSetChanged();
        }
    }

    /**
     *
     */
    private static class MyViewHolder extends ViewHolder {
        CalendarSingleRowWeekView calendarSingleRowWeekView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView instanceof CalendarSingleRowWeekView) {
                calendarSingleRowWeekView = (CalendarSingleRowWeekView) itemView;
            }
        }

    }

}
