package com.example.newhomeworkbook.calender.calenderview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.calender.model.CalenderDay;
import com.example.newhomeworkbook.calender.model.DayInMonthStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalenderWeekItemRecyclerView extends RecyclerView {

    private ArrayList<CalenderDay> newWeekModel;
    private static LocalDate todayDate = LocalDate.now();

    public CalenderWeekItemRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CalenderWeekItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalenderWeekItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    ///////////////////////////////////////////////////////////////////////////
    // init
    private void init(Context context) {
        this.setLayoutManager(new GridLayoutManager(context, 7));
        this.setAdapter(new CalenderWeekAdapter());
//        this.setCalenderWeekModel(CalenderDayModelManager.createCalenderWeekModel(2022, Month.FEBRUARY, 1));
    }

    public void setCalenderWeekModel(ArrayList<CalenderDay> weekModel) {
        this.newWeekModel = weekModel;
        this.getAdapter().notifyDataSetChanged();
    }

    ///////////////////////////////////////////////////////////////////////////
    // InnerClass
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Klasse Adapter
     */
    private class CalenderWeekAdapter extends Adapter<CalenderWeekViewHolder> {

        @NonNull
        @Override
        public CalenderWeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CalendarDayItemView calendarDayItemView = new CalendarDayItemView(parent.getContext());
            return new CalenderWeekViewHolder(calendarDayItemView);
        }

        @Override
        public void onBindViewHolder(@NonNull CalenderWeekViewHolder holder, int position) {
            CalenderDay calenderDay = newWeekModel.get(position);
            holder.getCalendarDayItemView().setThisDay(calenderDay);
            holder.getCalendarDayItemView().setDatumString("" + calenderDay.getLocalDate().getDayOfMonth());

            /*
            Dif. für aktuellen Tag
             */
            if (calenderDay.isLocalDateActualDate(todayDate)) {
                holder.getCalendarDayItemView().setRingVisibility(false);
                holder.getCalendarDayItemView().showBgrHighlighted(true);
            } else {
                holder.getCalendarDayItemView().setRingVisibility(false);
                holder.getCalendarDayItemView().showBgrHighlighted(false);
            }

            /*
            Dif. für Anzege für die Tage innerhalb des anzuzeigenden Monats. Info ist aus der Klasse
            @see CalenderDayModelManager erzeugt.
             */
            if (calenderDay.getDayInMonthStatus() == DayInMonthStatus.IN) {
//                holder.getCalendarDayItemView().setDatumString("IN" + calenderDayModel.getLocalDate().getDayOfMonth());
            }
            if (calenderDay.getDayInMonthStatus() == DayInMonthStatus.PRE) {
//                holder.getCalendarDayItemView().setDatumString("PRE" + calenderDayModel.getLocalDate().getDayOfMonth());
                holder.getCalendarDayItemView().setDayTextViewColor(Color.LTGRAY);
            }
            if (calenderDay.getDayInMonthStatus() == DayInMonthStatus.POST) {
//                holder.getCalendarDayItemView().setDatumString("OUT" + calenderDayModel.getLocalDate().getDayOfMonth());
                holder.getCalendarDayItemView().setDayTextViewColor(Color.LTGRAY);
            }
        }

        @Override
        public int getItemCount() {
            if (newWeekModel == null) {
                return 0;
            }
            return newWeekModel.size();
        }
    }

    /**
     * Klasse ViewHolder
     */
    private class CalenderWeekViewHolder extends ViewHolder {
        private CalendarDayItemView calendarDayItemView;

        public CalenderWeekViewHolder(@NonNull View itemView) {
            super(itemView);
            calendarDayItemView = (CalendarDayItemView) itemView;
        }

        public CalendarDayItemView getCalendarDayItemView() {
            return calendarDayItemView;
        }
    }
}
