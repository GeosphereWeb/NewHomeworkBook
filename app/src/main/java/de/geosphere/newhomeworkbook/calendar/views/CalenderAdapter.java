package de.geosphere.newhomeworkbook.calendar.views;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.geosphere.newhomeworkbook.calendar.model.Day;
import de.geosphere.newhomeworkbook.calendar.model.ModelSupportClass;
import de.geosphere.newhomeworkbook.calendar.model.Monthmodel;
import de.geosphere.newhomeworkbook.calendar.model.Weekmodel;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<AbstractCalendarViewHolder> {
    private static final int CALENDER_KWEEK_NUMBER = 0;
    private static final int CALENDER_WEEKNAME = 1;
    private static final int CALENDER_DAY = 2;

    private boolean showWeekname = true;

    private boolean showWeekend = true;
    private boolean showWeeknumber = true;

    public static int GRID_COLUMN_COUNT_Weeknumber = 1;
    public static int GRID_COLUMN_COUNT_Weekend = 2;
    public static int GRID_COLUMN_COUNT_Week = 5;

    private Monthmodel monthmodel;
    private ArrayList<Object> dataModel = new ArrayList<>();
    List<Object> filteredDataModel = new ArrayList<>();


    public CalenderAdapter(Context context) {
        Monthmodel monthmodel = ModelSupportClass.INSTANCE.instantiateMonthmodel(YearMonth.now());
        this.setModel(monthmodel, context);
    }

    public void setModel(Monthmodel monthmodel, Context context) {
        this.monthmodel = monthmodel;
        createDatamodel(monthmodel, context);

        //////
        filteredDataModel = dataModel;
        //////

        this.notifyDataSetChanged();
    }

    /*

     */
    private void createDatamodel(Monthmodel monthmodel, Context context) {
        // erzeuge das dataModel für die RecyclerView
        // Das Datamodel ist das alumfassende model. beinhaltet neben den Tagen auch die
        // Wochennamen und die KW. Dies sind in einem Array abgelegt.
        ArrayList<String> dayOfWeekAsSortedList = Weekmodel.getDayOfWeekAsStrings(context);
        dataModel.clear();
        dataModel.add(null);
        dataModel.addAll(dayOfWeekAsSortedList);

        for (Weekmodel weekmodel : monthmodel.getWeekmodels()
        ) {
            dataModel.add("" + weekmodel.getKw());
            dataModel.addAll(weekmodel.getCalendarDays());
        }
    }


    @NonNull
    @Override
    public AbstractCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CALENDER_WEEKNAME) {
            TextView textView = new TextView(parent.getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            return new AbstractCalendarViewHolder.CalendarWeekNameViewHolder(textView);
        }
        if (viewType == CALENDER_KWEEK_NUMBER) {
            TextView textView = new TextView(parent.getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
//            textView.setTextSize(10);
            return new AbstractCalendarViewHolder.CalendarKwViewHolder(textView);
        }
        if (viewType == CALENDER_DAY) {
            CalendarDayItemView dayItemView = new CalendarDayItemView(parent.getContext());
            return new AbstractCalendarViewHolder.CalendarDayViewHolder(dayItemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractCalendarViewHolder holder, int position) {
        if (filteredDataModel != null) {

            if (holder instanceof AbstractCalendarViewHolder.CalendarDayViewHolder) {

                AbstractCalendarViewHolder.CalendarDayViewHolder calendarDayViewHolder = (AbstractCalendarViewHolder.CalendarDayViewHolder) holder;
                Day calendarDay = (Day) filteredDataModel.get(position);
                calendarDayViewHolder.getCalendarDayItemView().setDatumString("" + calendarDay.getDay());

                if (calendarDay.isToday()) {
                    calendarDayViewHolder.getCalendarDayItemView().setAsActualDay(true);
                    calendarDayViewHolder.getCalendarDayItemView().requestFocus();
                }

                LocalDate firstDayInMonth = monthmodel.getYearMonth().atDay(1);
                LocalDate lastDayInMonth = monthmodel.getYearMonth().atEndOfMonth();
                if (calendarDay.getLocalDate().isBefore(firstDayInMonth)) {
                    calendarDayViewHolder.getCalendarDayItemView().setClickbar(false);
                    calendarDayViewHolder.getCalendarDayItemView().setOutAutActualMonth(true);
                } else if (calendarDay.getLocalDate().isAfter(lastDayInMonth)) {
                    calendarDayViewHolder.getCalendarDayItemView().setClickbar(false);
                    calendarDayViewHolder.getCalendarDayItemView().setOutAutActualMonth(true);
                } else {
                    calendarDayViewHolder.getCalendarDayItemView().setClickbar(true);
                    calendarDayViewHolder.getCalendarDayItemView().setOutAutActualMonth(false);
                }

            } else if (holder instanceof AbstractCalendarViewHolder.CalendarWeekNameViewHolder) {
                AbstractCalendarViewHolder.CalendarWeekNameViewHolder calendarWeekNameViewHolder = (AbstractCalendarViewHolder.CalendarWeekNameViewHolder) holder;
                String o = (String) filteredDataModel.get(position);
                calendarWeekNameViewHolder.getTextView().setText(o);

            }
            else if (holder instanceof AbstractCalendarViewHolder.CalendarKwViewHolder) {
                AbstractCalendarViewHolder.CalendarKwViewHolder calendarKwViewHolder = (AbstractCalendarViewHolder.CalendarKwViewHolder) holder;
                String o = (String) filteredDataModel.get(position);
                calendarKwViewHolder.getTextView().setText("KW " + o);
            }
        }
    }

    @Override
    public int getItemCount() {
        return filteredDataModel.size();
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

    public int getColumCount() {
        int tmp = 0;
        tmp += GRID_COLUMN_COUNT_Week;
        if (showWeekend) tmp += GRID_COLUMN_COUNT_Weekend;
        if (showWeeknumber) tmp += GRID_COLUMN_COUNT_Weeknumber;

        return tmp;
    }

    public void filterModel() {
        filteredDataModel = dataModel.subList(0,16);
        notifyDataSetChanged();
    }
}