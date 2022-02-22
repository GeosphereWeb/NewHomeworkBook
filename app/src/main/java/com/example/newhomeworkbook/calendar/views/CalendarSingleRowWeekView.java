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

import com.example.newhomeworkbook.calendar.model.CalendarDay;
import com.example.newhomeworkbook.calendar.model.ModelSupportClass;
import com.example.newhomeworkbook.calendar.model.Weekmodel;

import java.time.LocalDate;


public class CalendarSingleRowWeekView extends RecyclerView {
    //    private CalendarWeek calendarWeek;
    private Weekmodel weekmodel = ModelSupportClass.INSTANCE.instantiateWeekmodel(2022, LocalDate.now());

    private boolean showCalenderWeekNumber = true;

    private TextView kwTextView;
    private WeekItemsAdapter weekItemsAdapter;

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

    private void init(Context context) {

        weekItemsAdapter = new WeekItemsAdapter();
        this.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        this.setAdapter(weekItemsAdapter);

        if (weekmodel == null) {
            ////
            // START Testbereich
//            CalendarWeekManager calendarWeekManager = new CalendarWeekManager(2021, 52);
//            setCalenderWeek(calendarWeekManager.get(0));
            weekmodel = ModelSupportClass.INSTANCE.instantiateWeekmodel(2022, LocalDate.now());
            setCalenderWeekmodel(weekmodel);
            // ENDE Testbereich
        }
    }

//    /**
//     * Setz ein neues Modell für die Kalenderwoche
//     *
//     * @param calendarWeek
//     */
//    @Deprecated
//    public void setCalenderWeek(CalendarWeek calendarWeek) {
//        WeekItemsAdapter adapter = (WeekItemsAdapter) calendarWeekItemRecyclerView.getAdapter();
//        adapter.setWeekmodel(calendarWeek);
//
//        calendarWeekTextView.setText(calendarWeek.getWeekNumber() + "");
//    }

    /**
     * @param weekmodel
     */
    public void setCalenderWeekmodel(Weekmodel weekmodel) {
        this.weekmodel = weekmodel;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Inner Class
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Darstellund der Wochentage
     */
    private class WeekItemsAdapter extends RecyclerView.Adapter<AbstractCalenderViewHolder> {

        public WeekItemsAdapter() {
            super();
        }

        @NonNull
        @Override
        public AbstractCalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 0) {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                TextViewHolder textViewHolder = new TextViewHolder(textView);
                return textViewHolder;
            } else {
                CalendarDayItemView dayItemView = new CalendarDayItemView(parent.getContext());
                return new CalenderDayViewHolder(dayItemView);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull AbstractCalenderViewHolder holder, int position) {
            if (holder instanceof TextViewHolder) {
                TextViewHolder o = (TextViewHolder) holder;
                o.textView.setText("KW " + weekmodel.getKw());
            }

            if (holder instanceof CalenderDayViewHolder) {
                CalenderDayViewHolder calenderDayViewHolder = (CalenderDayViewHolder) holder;
                CalendarDay calendarDay = weekmodel.getCalendarDays().get(position - 1);

                calenderDayViewHolder.dayItemView.setDatumString(calendarDay.getDay()+"");
                if (calendarDay.isToday())  {
                    calenderDayViewHolder.dayItemView.setAsActualDay(true);
                    calenderDayViewHolder.dayItemView.requestFocus();
                }


            }
        }

        @Override
        public int getItemCount() {
            if (showCalenderWeekNumber) {
                return weekmodel.getCalendarDays().size() + 1;
            } else {
                return weekmodel.getCalendarDays().size();
            }
        }

        @Override
        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
            if (position == 0) {
                return 0;
            } else return 1;
        }

        //
//
//
//
//
//
//        public void setWeekmodel(Weekmodel weekmodel) {
//            CalendarSingleRowWeekView.this.weekmodel = weekmodel;
//            this.notifyDataSetChanged();
//        }
//
//        @NonNull
//        @Override
//        public CalenderDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//            return new TextViewHolder()
////            if (viewType == 0) {
////                return new CalenderDayViewHolder(new TextView(parent.getContext()));
////            } else {
////                // viewType = 1
////                CalendarDayItemView dayItemView = new CalendarDayItemView(parent.getContext());
////                return new CalenderDayViewHolder(dayItemView);
////            }
//        }
//
//        public void onBindViewHolder(@NonNull AbstractCalenderViewHolder holder, int position) {
//            if (holder instanceof TextViewHolder) {
//                TextViewHolder te = (TextViewHolder) holder;
//                te.textView.setText("KW" + weekmodel.getKw());
//            }
//
//            if (holder instanceof CalenderDayViewHolder) {
//                CalenderDayViewHolder calenderDayViewHolder = (CalenderDayViewHolder) holder;
//                CalendarDay calendarDayAt = weekmodel.getCalendarDays().get(position);
//
//                calenderDayViewHolder.dayItemView.setDatumString(calendarDayAt.getDay() + "");
//                calenderDayViewHolder.dayItemView.setAsActualDay(calendarDayAt.isToday());
//
//                calenderDayViewHolder.dayItemView.setClickbar(true);
//
//                // Ausserhalb des Monats die Erscheinung verblasst anzeigen
//                if (calendarDayAt.getDayInMonthStatus() != null) {
//                    switch (calendarDayAt.getDayInMonthStatus()) {
//                        case IN_ACTUAL_MONTH:
//                            calenderDayViewHolder.dayItemView.setOutAutActualMonth(true);
//                            calenderDayViewHolder.dayItemView.setClickbar(true);
//                            break;
////                    case BEFORE_ACTUAL_MONTH:
////                        holder.dayItemView.setOutAutActualMonth(true);
////                        break;
////                    case AFTER_ACTUAL_MONTH:
////                        holder.dayItemView.setOutAutActualMonth(true);
////                        break;
//                        default:
//                            calenderDayViewHolder.dayItemView.setOutAutActualMonth(false);
//                            calenderDayViewHolder.dayItemView.setClickbar(false);
//                    }
//                }
//
//                // Aktuelles(heutiges) Datum gleich hervorheben
//                if (calendarDayAt.isToday()) {
//                    calenderDayViewHolder.dayItemView.requestFocus();
//                }
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            if (weekmodel != null) {
//                return weekmodel.getCalendarDays().size() + 1; // +1, da KW am Anfang noch mit
//                // angezeigt werden muss
//            }
//            return 0;
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            if (position == 0) {
//                return 0;
//            } else {
//                // position > 0
//                return 1;
//            }
//        }
    }


    /**
     * Einzelner Kalendertag als View
     */
    private class CalenderDayViewHolder extends AbstractCalenderViewHolder {

        private final CalendarDayItemView dayItemView;

        public CalenderDayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayItemView = (CalendarDayItemView) itemView;
        }
    }

    /**
     * Einzelner Kalendertag als View
     */
    private class TextViewHolder extends AbstractCalenderViewHolder {
        private final TextView textView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    private abstract class AbstractCalenderViewHolder extends RecyclerView.ViewHolder {
        public AbstractCalenderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
