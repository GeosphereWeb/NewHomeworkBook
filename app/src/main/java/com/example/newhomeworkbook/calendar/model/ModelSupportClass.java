package com.example.newhomeworkbook.calendar.model;

import androidx.annotation.Nullable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public abstract class ModelSupportClass {
    /**
     * <p>http://javatricks.de/tricks/kalenderwoche-von-datum-ermitteln-kalenderwoche-in-datum-umwandeln
     *
     * <blockquote>
     *     <pre>
     *     LocalDate localDate = LocalDate.of(2016, Month.JANUARY, 3);
     *     int weekNumber = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
     *     System.out.println(weekNumber);
     *     </pre>
     * //Ausgabe: 53
     * </blockquote>
     *
     * Die Zeile localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) gibt die Kalenderwoche für das Datum zurück.
     *
     * Doch Achtung: Das gilt nur für das bei uns im Deutschsprachigen Raum gültige Kalendersystem laut ISO-8601. Möchte man ein anderes System verwenden muss das Locale explizit angegeben werden:
     *
     * LocalDate date= LocalDate.of(2016, Month.JANUARY, 3);
     * TemporalField woy = WeekFields.of(Locale.CANADA).weekOfWeekBasedYear();
     * System.out.println(date.get(woy));
     * // Ausgabe: 2
     *
     * Das Datum für eine bestimmte Kalenderwoche wird berechnet, indem ein beliebiges Datum im Jahr erstellt wird und dann die Kalenderwoche gesetzt wird. um den ersten Tag der Woche zu ermitteln, wird DayOfWeek.MONDAY gesetzt.
     *
     * int year = 2015;
     * int weekNumber = 53;
     * LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
     * LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber);
     * LocalDate start = dayInWeek.with(DayOfWeek.MONDAY);
     * System.out.println(start);
     * // Ausgabe: 2015-12-28
     *
     * <P>
     * <P>
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
    private static Locale LOCALE = Locale.getDefault();
    private static WeekFields weekFields = WeekFields.of(LOCALE);

    private static TemporalField weekOfYear = weekFields.weekOfWeekBasedYear();

    private static ArrayList<DayOfWeek> daysOfWeek = new ArrayList<>(7);


    private static DayOfWeek firstDayOfWeek = WeekFields.of(LOCALE).getFirstDayOfWeek();
    private static TemporalField woy = WeekFields.of(LOCALE).weekOfWeekBasedYear();

    public static ModelSupportClass INSTANCE = new ModelSupportClass() {
    };

    //    static {
//
//    }
    /*
    Constructor
     */
    private ModelSupportClass() {
        daysOfWeek = getDayOfWeekSortedList();
    }

    /**
     * @return Liste mit Wochentagen {@link DayOfWeek}-Array
     */
    public ArrayList<DayOfWeek> getDayOfWeekSortedList() {
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
     * @param kw
     * @return
     */
    public Weekmodel instantiateWeekmodel(int year, int kw) {
        PrivateWeekmodel privateWeekmodel = new PrivateWeekmodel(year, kw, null);
        return privateWeekmodel;
    }

    /**
     * @param locale
     */
    public void switchToNewLocal(Locale locale) {
        LOCALE = locale;
        firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
        woy = WeekFields.of(locale).weekOfWeekBasedYear();
    }

//    public Monthmodel instantiateMonthmodel(YearMonth yearMonth) {
//        LocalDate firstDayInMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
//        int lengthofMonth = yearMonth.lengthOfMonth();
//        return new InitMonthmodel();
//    }

    //-------------------------- INNER KLASS ----------------------------------

    /**
     *
     */
    private class PrivateWeekmodel extends Weekmodel {
        private final ArrayList<CalendarDay> calendarDays = new ArrayList<>();

        public PrivateWeekmodel(int year, int weeknumber, @Nullable Month month) {
            super(year, weeknumber);

            LocalDate firstDayInWeek = LocalDate.now().withYear(year)
                    .with(weekFields.weekOfYear(), weeknumber)
                    .with(weekFields.dayOfWeek(), 1);


            LocalDate firstDay = null;
            if (month != null) {
                firstDay = YearMonth.of(year, month).atDay(1);
            }

            for (int i = 0; i < 7; i++) {
                LocalDate localDateInWeek = firstDayInWeek.plusDays(i);
                CalendarDay calendarDay = new CalendarDay(localDateInWeek);

                if (month != null) {
                    if (localDateInWeek.isBefore(firstDay)) {
                        calendarDay.setDayInMonthStatus(MonthStatus.BEFORE_ACTUAL_MONTH);
                    } else if (localDateInWeek.isEqual(firstDay)) {
                        calendarDay.setDayInMonthStatus(MonthStatus.IN_ACTUAL_MONTH);
                    } else if (localDateInWeek.isAfter(firstDay)) {
                        calendarDay.setDayInMonthStatus(MonthStatus.AFTER_ACTUAL_MONTH);
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
        public PrivateMonthmodel(YearMonth yearMonth) {
            super(yearMonth);
            init();
        }

        private void init() {

        }

        @Override
        public ArrayList<Weekmodel> getWeekmodels() {
            return null;
        }


    }
}
