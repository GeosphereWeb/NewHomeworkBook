package com.example.newhomeworkbook.Kalender;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.Model.DayModel;
import com.example.newhomeworkbook.Model.DayOfTheWeek;
import com.example.newhomeworkbook.Model.KalenderDayModel;
import com.example.newhomeworkbook.Model.Zugehoerigkeit;
import com.example.newhomeworkbook.R;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;


public class KalenderViewAdapter extends RecyclerView.Adapter {

    private static final LocalDate aktDatum = LocalDate.now();
    private YearMonth aktMonth;
    private final int DAY_OF_THE_WEEK = 1;
    private final int DAYS_IN_MONTH = 2;

    private ArrayList<KalenderDayModel> monatsModell = new ArrayList<>();

    /**
     * Konstruktor
     *
     * @param aktMonth
     */
    public KalenderViewAdapter(YearMonth aktMonth) {
        setAktMonth(aktMonth);
    }

    /**
     * @param newMonth
     */
    public void setAktMonth(YearMonth newMonth) {
        this.aktMonth = newMonth;
        monatsModell = MonthModel.createModel(newMonth);
    }


    @Override
    public int getItemViewType(int position) {
        if (monatsModell.get(position) instanceof DayOfTheWeek) {
            return DAY_OF_THE_WEEK;
        } else return DAYS_IN_MONTH;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // diese Methode erzeugt die View
        if (viewType == DAY_OF_THE_WEEK) {
            ConstraintLayout inflate = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_kalender_weekname_item, parent, false);
            return new KalenderItemWeeknameViewHolder(inflate);
        } else {
            ConstraintLayout inflate = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_kalender_day_item, parent, false);
            return new KalenderItemDayViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Diese Methode bindet die Infos auf die ItemView
        KalenderDayModel kalenderDayModel = monatsModell.get(position);
        if (kalenderDayModel instanceof DayOfTheWeek) {
            DayOfTheWeek tmpDayOfTheWeek = (DayOfTheWeek) monatsModell.get(position);

            ((KalenderItemWeeknameViewHolder) holder).setDayOfWeekValue(tmpDayOfTheWeek.ordinal());

        } else if (kalenderDayModel instanceof DayModel) {
            DayModel tmpDayModel = (DayModel) monatsModell.get(position);
            int dayOfMonth = tmpDayModel.getDate().getDayOfMonth();

            KalenderItemDayViewHolder kalenderItemDayViewHolder = ((KalenderItemDayViewHolder) holder);
            TextView tagesDatumTextView = kalenderItemDayViewHolder.getTagesDatum();
            LinearLayout addonsLinearLayout = kalenderItemDayViewHolder.getAddons();

            /*
              Was nicht zum aktuellen Monat gehört wird anders angezeigt.
             */
            if (tmpDayModel.getZugehoerigkeit() != Zugehoerigkeit.AKTUELLN_MONAT) {
//                tagesDatumTextView.setVisibility(View.INVISIBLE);
                addonsLinearLayout.setVisibility(View.GONE);
                tagesDatumTextView.getBackground().setColorFilter(new BlendModeColorFilter(Color.LTGRAY, BlendMode.COLOR));

//                tagesDatumTextView.getForeground().setColorFilter(new BlendModeColorFilter(Color.GREEN, BlendMode.OVERLAY));

//                Or if you have defined color code in resource's color.xml file than
//
//                (From API >= 23)
//                mTextView.setTextColor(ContextCompat.getColor(context, R.color.<name_of_color>));
//
//                (For API < 23)
//                mTextView.setTextColor(getResources().getColor(R.color.<name_of_color>));
//                tagesDatumTextView.setTextColor(Color.GRAY);
                tagesDatumTextView.setTextColor(ContextCompat.getColor(tagesDatumTextView.getContext(), R.color.light_blue_400));
            }

            // Das Aktuelle Datum wird hervorgehoben
            if (tmpDayModel.getDate().isEqual(aktDatum)) {
                tagesDatumTextView.setText(String.valueOf(dayOfMonth));
                tagesDatumTextView.getBackground().setColorFilter(new BlendModeColorFilter(Color.DKGRAY, BlendMode.COLOR));
            } else {
                tagesDatumTextView.setText(String.valueOf(dayOfMonth));
                tagesDatumTextView.getBackground().setColorFilter(new BlendModeColorFilter(Color.BLUE, BlendMode.COLOR));
            }

        }
    }

    @Override
    public int getItemCount() {
        // gibt die Anzahl der Items zurück
        return monatsModell.size();
    }



    /*
     *******************************************************************************
     */

    /**
     * Klasse für den ViewHolder
     * Über den View Holder können auf die Unterelemente in der View zugegriffen und dadurch verändert werden.
     */
    public static class KalenderItemDayViewHolder extends RecyclerView.ViewHolder {
        TextView tagesDatum;
        LinearLayout addons;
        View clickArea;

        public KalenderItemDayViewHolder(@NonNull View itemView) {
            super(itemView);
            tagesDatum = itemView.findViewById(R.id.item_kalender_dayitem);
            addons = itemView.findViewById((R.id.item_kalender_addons));
            clickArea = itemView.findViewById(R.id.kalenderItemDay_clickArea);
        }

        public TextView getTagesDatum() {
            return tagesDatum;
        }

        public void setTagesDatum(int tagesDatum) {
            this.tagesDatum.setText("" + tagesDatum);
        }

        public LinearLayout getAddons() {
            return addons;
        }
    }

    /**
     * Klasse für den ViewHolder
     * Über den View Holder können auf die Unterelemente in der View zugegriffen und dadurch verändert werden.
     */
    public static class KalenderItemWeeknameViewHolder extends RecyclerView.ViewHolder {
        private final TextView weeknameTextView;

        public KalenderItemWeeknameViewHolder(@NonNull View itemView) {
            super(itemView);
            weeknameTextView = itemView.findViewById(R.id.item_kalender_weekname);
        }

        public void setDayOfWeekValue(int pos) {
            weeknameTextView.setText(DayOfTheWeek.getDayOfTheWeek(pos).getNameReferenceAsShortName());
        }
    }

    /**
     * Erzeugt ein ArrayList dass das Monats-Modell repräsentiert.
     * <pre><code>
     * ----------------------------------------------------
     * MO DI MI DO FR SA SO   <-- {@link DayOfTheWeek}
     *          01 02 03 04   <-- {@link DayModel}
     * 05 06 07 08 09 10 11   <-- DayModel
     * .. .. .. .. .. .. ..   <-- DayModel
     * 28 29 30 31            <-- DayModel
     * ----------------------------------------------------
     * </code></pre>
     * Die Enum {@link DayOfTheWeek} und die Klasse {@link DayModel} implementieren die Schnittstelle {@link KalenderDayModel}
     */
    public static class MonthModel {
        private static ArrayList<KalenderDayModel> monthModel;

        private MonthModel() {
        }

        /**
         * Diese Methode erzeugt das Monatsmodell und wird als ein ArrayList zurückgegeben.
         * Die ersten 7 (0-6) Einträge beinhalten die WochenTagsNamen.
         * Ab der Stelle 7 werden die Tage eingetragen.
         * Beginnt der der erste Tag des Monats später als am Montag, dann werden bis dahin
         * die Tage mit dem Vormonatstage aufgefüll.
         * Das gleiche wird auch am Ende des Monats durchgeführt. Ist der Letzte Tag des Monats
         * früher als der Sonntag, dann werden diese mit den Tagen des Folgemonats aufgefüllt.
         * <pre><code>
         * ----------------------------------------------------
         * MO DI MI DO FR SA SO   <-- {@link DayOfTheWeek}
         * 28 29 30 01 02 03 04   <-- {@link DayModel}
         * 05 06 07 08 09 10 11   <-- DayModel
         * .. .. .. .. .. .. ..   <-- DayModel
         * 28 29 30 31 01 02 03   <-- DayModel
         * ----------------------------------------------------
         * </code></pre>
         *
         * @param month
         * @return ArrayListe {MO,DI,MI,DO,FR,SA,SO,  ,  ,  ,  ,01,02,...,30,31,  ,  ,  ,  }
         */
        public static ArrayList<KalenderDayModel> createModel(YearMonth month) {
            monthModel = new ArrayList<KalenderDayModel>();
            // Erzeuge die Wochenbezeichnungen
            for (int i = 0; i < 7; i++) {
                monthModel.add(DayOfTheWeek.getDayOfTheWeek(i));
            }

            LocalDate firstDayInMonth = month.atDay(1);
            int firstDayOfWeek = firstDayInMonth.getDayOfWeek().getValue();

            // Tage vor dem aktuellen Monat werden eingetragen
            for (int i = 1; i < firstDayOfWeek; i++) {
                monthModel.add(new DayModel(firstDayInMonth.minusDays(firstDayOfWeek-i), Zugehoerigkeit.VORHERIGER_MONAT));
            }

            // Tage im aktuellen Monat werden eingetragen
            for (int i = 1; i <= month.lengthOfMonth(); i++) {
                monthModel.add(new DayModel(month.atDay(i), Zugehoerigkeit.AKTUELLN_MONAT));
            }

            LocalDate lastDayInMonth = month.atEndOfMonth();
            int lastDayOfWeek = lastDayInMonth.getDayOfWeek().getValue();

            for (int i = 1; i <= 7 - lastDayOfWeek; i++) {
                monthModel.add(new DayModel(lastDayInMonth.plusDays(i), Zugehoerigkeit.NAECHSTER_MONAT));
            }

            return monthModel;
        }

    }
}
