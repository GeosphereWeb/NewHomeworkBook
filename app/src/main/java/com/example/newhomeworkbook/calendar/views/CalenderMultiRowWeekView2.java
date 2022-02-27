package com.example.newhomeworkbook.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.calendar.model.Day;
import com.example.newhomeworkbook.calendar.model.ModelSupportClass;
import com.example.newhomeworkbook.calendar.model.Monthmodel;
import com.example.newhomeworkbook.calendar.model.Weekmodel;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;

public class CalenderMultiRowWeekView2 extends RecyclerView {
    private boolean showCalenderWeek = true;
    private boolean showWeekname = true;

    private static final int CALENDER_KWEEK_NUMBER = 0;
    private static final int CALENDER_WEEKNAME = 1;
    private static final int CALENDER_DAY = 2;

    public CalenderMultiRowWeekView2(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CalenderMultiRowWeekView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalenderMultiRowWeekView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, (1 + 5 + 2), LinearLayoutManager.VERTICAL, false);
        this.setLayoutManager(gridLayoutManager);

        CalenderAdapter calenderAdapter = new CalenderAdapter();

        // TEST
        Monthmodel monthmodel = ModelSupportClass.INSTANCE.instantiateMonthmodel(YearMonth.of(2022, Month.FEBRUARY));
        calenderAdapter.setModel(monthmodel);
        // ENDE TEST

        this.setAdapter(calenderAdapter);
    }


//    public static ArrayList<Object> buildModel(Context context) {
//        ArrayList<String> dayOfWeekAsSortedList = Weekmodel.getDayOfWeekAsStrings(context);
//
//        return null;
//    }


    ///////////////////////////////////////////////////////////////////////////
    // Adapter
    ///////////////////////////////////////////////////////////////////////////
    private class CalenderAdapter extends Adapter<AbstractCalendarViewHolder> {
        private Monthmodel monthmodel;
        private ArrayList<Object> dataModel = new ArrayList<>();

        public void setModel(Monthmodel monthmodel) {
            this.monthmodel = monthmodel;

            // erzeuge das dataModel für die RecyclerView
            ArrayList<String> dayOfWeekAsSortedList = Weekmodel.getDayOfWeekAsStrings(CalenderMultiRowWeekView2.this.getContext());
            dataModel.clear();
            dataModel.add(null);
            dataModel.addAll(dayOfWeekAsSortedList);

            for (Weekmodel weekmodel : monthmodel.getWeekmodels()
            ) {
                dataModel.add("KW " + weekmodel.getKw());
                dataModel.addAll(weekmodel.getCalendarDays());
            }
            // ende

            this.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public AbstractCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == CALENDER_WEEKNAME || viewType == CALENDER_KWEEK_NUMBER) {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                return new CalendarWeekNameViewHolder(textView);
            }
            if (viewType == CALENDER_DAY) {
                CalendarDayItemView dayItemView = new CalendarDayItemView(parent.getContext());
                return new CalendarDayViewHolder(dayItemView);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull AbstractCalendarViewHolder holder, int position) {
            if (dataModel != null) {
                if (holder instanceof CalendarDayViewHolder) {

                    CalendarDayViewHolder calendarDayViewHolder = (CalendarDayViewHolder) holder;
                    Day calendarDay = (Day) dataModel.get(position);
                    calendarDayViewHolder.calendarDayItemView.setDatumString("" + calendarDay.getDay());

                    if (calendarDay.isToday()) {
                        calendarDayViewHolder.calendarDayItemView.setAsActualDay(true);
                        calendarDayViewHolder.calendarDayItemView.requestFocus();
                    }

                    LocalDate firstDayInMonth = monthmodel.getYearMonth().atDay(1);
                    LocalDate lastDayInMonth = monthmodel.getYearMonth().atEndOfMonth();
                    if(calendarDay.getLocalDate().isBefore(firstDayInMonth)){
                        calendarDayViewHolder.calendarDayItemView.setClickbar(false);
                        calendarDayViewHolder.calendarDayItemView.setOutAutActualMonth(true);
                    } else
                    if(calendarDay.getLocalDate().isAfter(lastDayInMonth)){
                        calendarDayViewHolder.calendarDayItemView.setClickbar(false);
                        calendarDayViewHolder.calendarDayItemView.setOutAutActualMonth(true);
                    } else {
                        calendarDayViewHolder.calendarDayItemView.setClickbar(true);
                        calendarDayViewHolder.calendarDayItemView.setOutAutActualMonth(false);
                    }

                } else if (holder instanceof CalendarWeekNameViewHolder) {
                    CalendarWeekNameViewHolder calendarWeekNameViewHolder = (CalendarWeekNameViewHolder) holder;
                    String o = (String) dataModel.get(position);
                    calendarWeekNameViewHolder.textView.setText(o);
                }
            }
        }

        @Override
        public int getItemCount() {
            return dataModel.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position <= 7) {
                return CALENDER_WEEKNAME;
            }
            if (position % 8 == 0) {
                return CALENDER_KWEEK_NUMBER;
            } else {
                return CALENDER_DAY;
            }
//            return super.getItemViewType(position);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // ViewHolder
    ///////////////////////////////////////////////////////////////////////////
    private abstract class AbstractCalendarViewHolder extends ViewHolder {
        public AbstractCalendarViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class CalendarWeekNameViewHolder extends AbstractCalendarViewHolder {
        private TextView textView;

        public CalendarWeekNameViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    private class CalendarDayViewHolder extends AbstractCalendarViewHolder {
        private CalendarDayItemView calendarDayItemView;

        public CalendarDayViewHolder(@NonNull View itemView) {
            super(itemView);
            calendarDayItemView = (CalendarDayItemView) itemView;
        }
    }
}
