package com.example.newhomeworkbook.calendar.model;

import androidx.annotation.Nullable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

public abstract class ModelSupportClass {
    /**
     * <p>http://javatricks.de/tricks/kalenderwoche-von-datum-ermitteln-kalenderwoche-in-datum-umwandeln
     *
     * <blockquote>
     * <pre>
     *     LocalDate localDate = LocalDate.of(2016, Month.JANUARY, 3);
     *     int weekNumber = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
     *     System.out.println(weekNumber);
     *     </pre>
     * //Ausgabe: 53
     * </blockquote>
     *
     * <p>Die Zeile localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) gibt die Kalenderwoche für das Datum zurück.
     *
     * <p>Doch Achtung: Das gilt nur für das bei uns im Deutschsprachigen Raum gültige Kalendersystem laut ISO-8601.
     * Möchte man ein anderes System verwenden muss das Locale explizit angegeben werden:
     *
     * <p>LocalDate date= LocalDate.of(2016, Month.JANUARY, 3);<br>
     * TemporalField woy = WeekFields.of(Locale.CANADA).weekOfWeekBasedYear();<br>
     * System.out.println(date.get(woy));<br>
     * // Ausgabe: 2
     *
     * <p>Das Datum für eine bestimmte Kalenderwoche wird berechnet, indem ein beliebiges Datum im
     * Jahr erstellt wird und dann die Kalenderwoche gesetzt wird. um den ersten Tag der
     * Woche zu ermitteln, wird DayOfWeek.MONDAY gesetzt.
     *
     * <blockquote>
     * <pre>
     * int year = 2015;
     * int weekNumber = 53;
     * LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
     * LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber);
     * LocalDate start = dayInWeek.with(DayOfWeek.MONDAY);
     * System.out.println(start);
     * </pre>
     * // Ausgabe: 2015-12-28
     * </blockquote>
     * <p>
     * <p>
     * <blockquote>
     * <pre>
     * [Java 8]
     *
     * int year = 2018;
     * int weekNumber = 1;
     *
     * LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
     * LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber);
     * LocalDate start = dayInWeek.with(DayOfWeek.MONDAY);
     *
     * System.out.println(start);
     * </pre>
     * </blockquote>
     */
    // private static final ArrayList<Integer> weekendPosition = new ArrayList<>();
    private DayOfWeek firstDayOfWeek = WeekFields.ISO.getFirstDayOfWeek();
    private ArrayList<DayOfWeek> daysOfWeek = new ArrayList<>(7);
    //    private Locale LOCALE = Locale.getDefault();
    //    private  TemporalField weekOfYear = weekFields.weekOfWeekBasedYear();
    //    private WeekFields weekFields = WeekFields.of(LOCALE);
    //    private  TemporalField woy = WeekFields.of(LOCALE).weekOfWeekBasedYear();

    public static ModelSupportClass INSTANCE = new ModelSupportClass() {
    };

    /*
    Constructor
     */
    private ModelSupportClass() {
        daysOfWeek = getDayOfWeekAsSortedList();
    }

    /**
     * @return Liste mit Wochentagen {@link DayOfWeek}-Array
     */
    public ArrayList<DayOfWeek> getDayOfWeekAsSortedList() {
        ArrayList<DayOfWeek> dayOfWeekArrayList = new ArrayList<>(7);

        // Erzeuge die Reihenfolge der Wochentage
        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = firstDayOfWeek.plus(i);
            dayOfWeekArrayList.add(i, dayOfWeek);
        }
        dayOfWeekArrayList.trimToSize();

        return dayOfWeekArrayList;
    }

    //-------------------------------------------------------------------------

    /**
     * @param year
     * @param dayInWeek
     * @return
     */
    public Weekmodel instantiateWeekmodel(int year, LocalDate dayInWeek) {
        PrivateWeekmodel privateWeekmodel = new PrivateWeekmodel(year, dayInWeek);
        return privateWeekmodel;
    }

//    /**
//     * @param locale
//     */
//    public void switchToNewLocal(Locale locale) {
//        LOCALE = locale;
//        firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
//        woy = WeekFields.of(locale).weekOfWeekBasedYear();
//    }

    public Monthmodel instantiateMonthmodel(YearMonth yearMonth) {
        return new PrivateMonthmodel(yearMonth);
    }

    //-------------------------- INNER KLASS ----------------------------------
    /**
     *
     */
    private class PrivateWeekmodel extends Weekmodel {
        private final ArrayList<CalendarDay> calendarDays = new ArrayList<>(7);

        protected PrivateWeekmodel(int year, LocalDate dayInWeek) {
            this(year, dayInWeek, null);
        }

        protected PrivateWeekmodel(int year, LocalDate dayInWeek, @Nullable YearMonth month) {
            super(year, dayInWeek);

            //  LocalDate ersterTagDerWoche = dayInWeek.with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());
            LocalDate ersterTagDerWoche = dayInWeek.with(WeekFields.ISO.getFirstDayOfWeek());
            for (int i = 0; i < 7; i++) {
                LocalDate localDate = ersterTagDerWoche.plusDays(i);
                CalendarDay calendarDay = new CalendarDay(localDate);

                if (month != null) {
                    if (localDate.isBefore(month.atDay(1))){
                        calendarDay.setDayInMonthStatus(MonthStatus.BEFORE_ACTUAL_MONTH);
                    } else if (localDate.isAfter(month.atEndOfMonth())){
                        calendarDay.setDayInMonthStatus(MonthStatus.AFTER_ACTUAL_MONTH);
                    } else {
                        calendarDay.setDayInMonthStatus(MonthStatus.IN_ACTUAL_MONTH);
                    }

                }

                calendarDays.add(calendarDay);
            }
        }

        @Override
        public ArrayList<CalendarDay> getCalendarDays() {
            return calendarDays;
        }
    }

    /**
     *
     */
    private class PrivateMonthmodel extends Monthmodel {
        private ArrayList<Weekmodel> weekmodels = new ArrayList<>();

        public PrivateMonthmodel(YearMonth yearMonth) {
            super(yearMonth);

            int year = yearMonth.getYear();

            LocalDate firstDayInMonth = yearMonth.atDay(1);
            LocalDate lastDayInMonth = yearMonth.atEndOfMonth();

            // first Day in Week
            LocalDate tmp = firstDayInMonth.with(firstDayInMonth.with(WeekFields.ISO.getFirstDayOfWeek()));
            while (!tmp.isAfter(lastDayInMonth)) {
                weekmodels.add(new PrivateWeekmodel(year, tmp, yearMonth));
                tmp = tmp.plusWeeks(1);
            }

        }

        @Override
        public ArrayList<Weekmodel> getWeekmodels() {
            return weekmodels;
        }

    }
}
