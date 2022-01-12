package com.example.newhomeworkbook.Kalender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.Model.DayOfTheWeek;
import com.example.newhomeworkbook.R;

import java.time.YearMonth;
import java.util.ArrayList;


public class KalenderViewAdapter extends RecyclerView.Adapter {

    YearMonth aktMonth;
    private int DAY_OF_THE_WEEK = 1;
    private int DAYS_IN_MONTH = 2;


    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if (position < 7) {
            return DAY_OF_THE_WEEK;
        } else return DAYS_IN_MONTH;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // diese Methode erzeugt die View
        if (viewType == DAY_OF_THE_WEEK) {
            ConstraintLayout inflate = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kalender_weekname, parent, false);
            return new KalenderItemWeeknameViewHolder(inflate);
        } else {
            ConstraintLayout inflate = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kalender_day, parent, false);
            return new KalenderItemDayViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Diese Methode bindet die Infos auf die ItemView
        if (getItemViewType(position) == DAY_OF_THE_WEEK) {
            ((KalenderItemWeeknameViewHolder)holder).setDayOfWeekValue(position);
        } else {
            ((KalenderItemDayViewHolder)holder).setTagesDatum(position);
        }


    }

    @Override
    public int getItemCount() {
        // gibt die Anzahl der Items zurück
        return 31;
    }

    public YearMonth getAktMonth() {
        return aktMonth;
    }

    public void setAktMonth(YearMonth aktMonth) {
        this.aktMonth = aktMonth;
        int monthValue = aktMonth.getMonthValue();

    }

    /**
     * Klasse für den ViewHolder
     * Über den View Holder können auf die Unterelemente in der View zugegriffen und dadurch verändert werden.
     */
    public static class KalenderItemDayViewHolder extends RecyclerView.ViewHolder {
        TextView tagesDatum;

        public KalenderItemDayViewHolder(@NonNull View itemView) {
            super(itemView);
            tagesDatum = itemView.findViewById(R.id.item_kalender_dayitem);
        }
        public void setTagesDatum(int tagesDatum) {
            this.tagesDatum.setText("" + tagesDatum);
        }
    }

    /**
     * Klasse für den ViewHolder
     * Über den View Holder können auf die Unterelemente in der View zugegriffen und dadurch verändert werden.
     */
    public static class KalenderItemWeeknameViewHolder extends RecyclerView.ViewHolder {
        private TextView weeknameTextView;

        public KalenderItemWeeknameViewHolder(@NonNull View itemView) {
            super(itemView);
            weeknameTextView = itemView.findViewById(R.id.item_kalender_weekname);
        }

        public void setDayOfWeekValue(int pos) {
            weeknameTextView.setText(DayOfTheWeek.getDayOfTheWeek(pos).getNameReferenceAsShort());
        }
    }

    /**
     *
     */
    public class MonthModel {

        private YearMonth aktMonth;
        private ArrayList<Integer> monthAsList = new ArrayList<>();


        public MonthModel(YearMonth aktMonth) {
            this.aktMonth = aktMonth;
        }

        public int getMonthValue() {
            return aktMonth.getMonthValue();
        }

        public ArrayList<Integer> getMonthAsList() {
            //TODO Monat ausgeben; evtl. muss ein tagesobjekt erzeugt werden.
            return null;
        }

    }
}
