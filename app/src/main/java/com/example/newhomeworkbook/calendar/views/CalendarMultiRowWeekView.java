package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.calendar.model.ModelSupportClass;
import com.example.newhomeworkbook.calendar.model.Monthmodel;
import com.example.newhomeworkbook.calendar.model.Weekmodel;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;

public class CalendarMultiRowWeekView extends RecyclerView {
    private MultiWeeksAdapter multiWeeksAdapter;
    private ArrayList<Weekmodel> weekmodels;

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

        // TESTBEREICH
        if (weekmodels == null) {
            ////
            // START Testbereich
            setModel(ModelSupportClass.INSTANCE.instantiateMonthmodel(YearMonth.of(2022, Month.MARCH)));
            this.refreshDrawableState();
            // ENDE Testbereich
        }

    }

    public void setModel(Monthmodel monthmodel) {
        multiWeeksAdapter.setMonthmodel(monthmodel);
    }

    public void setModel(ArrayList<Weekmodel> weekmodels) {
        multiWeeksAdapter.setWeekmodels(weekmodels);
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
            return new MyViewHolder(calendarSingleRowWeekView);

//            calendarSingleRowWeekView.setFocusable(true);
//            calendarSingleRowWeekView.setClickable(true);
//            calendarSingleRowWeekView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i("WERNER", "onClick: remove Kalenderwoche");
//                    calendarWeekManager.getCalenderWeeks().remove(2);
//                }
//            });
//            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_week_header, parent, false);
//
////            return new MyViewHolder(calendarSingleRowWeekView);
//            MyViewHolder myViewHolder;
//            if (viewType == CALENDAR_HEADER) {
//                myViewHolder = new MyViewHolder(headerView);
//            } else {
//                myViewHolder = new MyViewHolder(calendarSingleRowWeekView);
//            }
//            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Weekmodel weekmodel = weekmodels.get(position);
            holder.calendarSingleRowWeekView.setCalenderWeekmodel(weekmodel);
//            if (holder.calendarSingleRowWeekView != null) {
////                CalendarWeek calendarWeek = calendarWeekManager.get(position - 1);
////                holder.calendarSingleRowWeekView.setCalenderWeek(calendarWeek);
//            }
        }

        @Override
        public int getItemCount() {
            if (weekmodels != null) {
                return weekmodels.size();
            } else return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
////            return super.getItemViewType(position);
//            if (position == 0) {
//                return CALENDAR_HEADER;
//            } else {
//                return CALENDAR_DAYS;
//            }
        }

        public void setWeekmodels(ArrayList<Weekmodel> weekmodels) {
            CalendarMultiRowWeekView.this.weekmodels = weekmodels;
            this.notifyDataSetChanged();
        }

        public void setMonthmodel(Monthmodel monthmodel) {
            setWeekmodels(monthmodel.getWeekmodels());
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
