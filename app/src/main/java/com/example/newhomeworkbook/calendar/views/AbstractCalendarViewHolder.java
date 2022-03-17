package com.example.newhomeworkbook.calendar.views;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.R;

public abstract class AbstractCalendarViewHolder extends RecyclerView.ViewHolder {

    private final int colorPrimary;
    private final int colorTertiary;

    public AbstractCalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        // Color
        TypedValue typedValue_colorPrimary = new TypedValue();
        itemView.getContext().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue_colorPrimary, true);
        colorPrimary = typedValue_colorPrimary.data;

        TypedValue typedValue_colorTertiary = new TypedValue();
        itemView.getContext().getTheme().resolveAttribute(R.attr.colorTertiary, typedValue_colorTertiary, true);
        colorTertiary = typedValue_colorTertiary.data;
    }



    public static class CalendarDayViewHolder extends AbstractCalendarViewHolder {
        private CalendarDayItemView calendarDayItemView;

        public CalendarDayViewHolder(@NonNull View itemView) {
            super(itemView);
            calendarDayItemView = (CalendarDayItemView) itemView;
        }

        public CalendarDayItemView getCalendarDayItemView() {
            return calendarDayItemView;
        }
    }

    public static class CalendarKwViewHolder extends AbstractCalendarViewHolder {
        private TextView textView;

        public CalendarKwViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setTextSize(9.0f);
            textView.setTextColor(super.colorPrimary);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public static class CalendarWeekNameViewHolder extends AbstractCalendarViewHolder {
        private TextView textView;

        public CalendarWeekNameViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setTextColor(super.colorTertiary);
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
