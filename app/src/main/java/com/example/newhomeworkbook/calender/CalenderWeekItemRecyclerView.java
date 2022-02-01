package com.example.newhomeworkbook.calender;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.calender.model.CalenderDayModel;
import com.example.newhomeworkbook.calender.model.CalenderDayModelManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalenderWeekItemRecyclerView extends RecyclerView {

    private ArrayList<CalenderDayModel> newWeekModel;
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
        this.setCalenderWeekModel(CalenderDayModelManager.createCalenderWeekModel(2022, 1));
    }

    public void setCalenderWeekModel(ArrayList<CalenderDayModel> weekModel) {
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
            CalenderDayModel calenderDayModel = newWeekModel.get(position);
            holder.getCalendarDayItemView().setDatumText("" + calenderDayModel.getLocalDate().getDayOfMonth());

            if (calenderDayModel.isLocalDateActualDate(todayDate)) {
//                holder.setText("xx");
//                holder.showLable(true);
//                holder.showCircle(true);
                holder.getCalendarDayItemView().showRing(true);

                // FIXME: 01.02.2022 Backgroundänderung wirkt sich nur global aus und überschreibt den showRing() Methode
//                holder.getCalendarDayItemView().setDayBackgroundColor(true);
//                holder.getCalendarDayItemView().getDayBackground().setTint(Color.BLUE);
            } else {
                holder.getCalendarDayItemView().showRing(false);

                // FIXME: 01.02.2022 Backgroundänderung wirkt sich nur global aus und überschreibt den showRing() Methode
//                holder.getCalendarDayItemView().setDayBackgroundColor(false);
//                holder.getCalendarDayItemView().getDayBackground().setTint(Color.GREEN);
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
