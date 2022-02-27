package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            setModel(ModelSupportClass.INSTANCE.instantiateMonthmodel(YearMonth.of(2022, Month.FEBRUARY)));
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
    private class MultiWeeksAdapter extends Adapter<MyAbstractViewHolder> {

        @NonNull
        @Override
        public MyAbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == CALENDAR_HEADER) {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                MyWochenTageViewHolder textViewHolder = new MyWochenTageViewHolder(textView);
                return textViewHolder;
            } else {
                CalendarSingleRowWeekView calendarSingleRowWeekView = new CalendarSingleRowWeekView(parent.getContext());
                MyCalenderSingleRowViewHolder myCalenderSingleRowViewHolder = new MyCalenderSingleRowViewHolder(calendarSingleRowWeekView);
                return myCalenderSingleRowViewHolder;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyAbstractViewHolder holder, int position) {
            if (holder instanceof MyWochenTageViewHolder) {
                MyWochenTageViewHolder my = (MyWochenTageViewHolder) holder;
            }

            if (holder instanceof MyCalenderSingleRowViewHolder) {
                MyCalenderSingleRowViewHolder my = (MyCalenderSingleRowViewHolder) holder;
                Weekmodel weekmodel = weekmodels.get(position);
                my.calendarSingleRowWeekView.setCalenderWeekmodel(weekmodel);
            }
        }

        @Override
        public int getItemCount() {
            if (weekmodels != null) {
                return weekmodels.size();
            } else return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return CALENDAR_DAYS;
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

    //-------------------------------------------------------------------------
    private abstract class MyAbstractViewHolder extends ViewHolder {
        public MyAbstractViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     *
     */
    private class MyCalenderSingleRowViewHolder extends MyAbstractViewHolder {
        CalendarSingleRowWeekView calendarSingleRowWeekView;

        public MyCalenderSingleRowViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView instanceof CalendarSingleRowWeekView) {
                calendarSingleRowWeekView = (CalendarSingleRowWeekView) itemView;
            }
        }
    }

    /**
     *
     */
    private class MyWochenTageViewHolder extends MyAbstractViewHolder {
        TextView textView;

        public MyWochenTageViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView instanceof TextView) {
                textView = (TextView) itemView;
            }
        }
    }

}
