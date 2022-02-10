package com.example.newhomeworkbook.calendar.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

/**
 * CalenderWeekModel repräsentiert eine oder mehrere Kalenderwochen.
 */
public class CalendarWeekManager {
    private static WeekFields weekFields = WeekFields.of(Locale.getDefault());
    // Or use a specific locale, or configure your own rules
    private static final TemporalField weekOfYear = weekFields.weekOfWeekBasedYear();

    private final ArrayList<CalendarWeek> calendarWeeks = new ArrayList<>();

    /**
     * @param year       Das Jahr als Integer. Z.B. '2022'
     * @param weekNumber Wochennummer als Integer. '1' entspricht die erste Woche im Jahr
     */
    public CalendarWeekManager(int year, int weekNumber) {
        calendarWeeks.add(createCalendarWeek(year, weekNumber));
    }

    /**
     * fix
     * @param year  Das Jahr als Integer. Z.B. '2022'
     * @param month Monat{@link Month} als Monat
     */
    public CalendarWeekManager(int year, Month month) {
        // FIXME: 10.02.2022 funktioniert nur in Deutschsprachigen Bereich. Die Datumsberechnung muss generischer werden.
        // erster und letzter Tag des Monats
        LocalDate firstDayInMonth = LocalDate.now().withYear(year).withMonth(month.getValue()).withDayOfMonth(1);
        LocalDate lastDayInMonth = firstDayInMonth.withDayOfMonth(firstDayInMonth.lengthOfMonth());

//        // Erste und letzte Wochennummer des Monats
//        int firstWeekNrInMonth = firstDayInMonth.get(weekOfYear);
//        int lastWeekNrInMonth = lastDayInMonth.get(weekOfYear);

        LocalDate firstDayInFullMonth = firstDayInMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayInFullMonth = lastDayInMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Period diffDays = Period.between(firstDayInFullMonth, lastDayInFullMonth);
        int fullDaysInMonth = diffDays.getDays() + firstDayInMonth.lengthOfMonth();

        long weekCount = (fullDaysInMonth / 7) + 1; // +1 wird benötigt um die vollen Wochen anzeigen zu können

        /*
        LocalDate lastVisible = LocalDate.of(2017, 3, 12);
        LocalDate dateToSelect = LocalDate.of(2017, 3, 13).minusDays(1);

        long weeks = ChronoUnit.WEEKS.between(lastVisible, dateToSelect);
        System.out.println("number of weeks "+ weeks);

        dateToSelect = LocalDate.of(2017, 3, 19).minusDays(1);
        weeks = ChronoUnit.WEEKS.between(lastVisible, dateToSelect);
        System.out.println("number of weeks "+ weeks);

        dateToSelect = LocalDate.of(2017, 3, 20).minusDays(1);
        weeks = ChronoUnit.WEEKS.between(lastVisible, dateToSelect);
        System.out.println("number of weeks "+ weeks);
        */

        // Schleife überarbeiten.
        for (int i = 0; i < weekCount; i++) {
            LocalDate plusWeeks = firstDayInMonth.plusWeeks(i);
            int firstWeekNrInMonth2 = plusWeeks.get(weekOfYear);

            CalendarWeek calendarWeek = createCalendarWeek(year, firstWeekNrInMonth2);
            calendarWeeks.add(calendarWeek);
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // Getter und Setter
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Die Methode getModel liefert das ArrayList Objekt, die mit {@link CalendarWeek} befüllt sind aus.
     *
     * @return ArrayList mit {@link CalendarWeek}-Items
     */
    public ArrayList<CalendarWeek> getCalenderWeeks() {
        return calendarWeeks;
    }

    /**
     * Gibt das {@link CalendarWeek}-Model zurück
     *
     * @param position
     * @return Kalenderwochenmodell
     */
    public CalendarWeek get(int position) {
        return calendarWeeks.get(position);
    }

    /**
     * @return Gibt die Anzahl der Kalenderwochen zurück.
     */
    public int size() {
        calendarWeeks.trimToSize();
        return calendarWeeks.size();
    }

    ///////////////////////////////////////////////////////////////////////////
    // innere Methoden
    ///////////////////////////////////////////////////////////////////////////
    /*
     * Erzeugt eine Kalenderwoche. Das Objekt Kalenderwoche
     */
    private CalendarWeek createCalendarWeek(int year, int weekNumber) {
        LocalDate firstDayInWeek = LocalDate.now().withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), 1);

        ArrayList<CalendarDay> weekModel = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate localDate = firstDayInWeek.plusDays(i);
            CalendarDay calendarDay = new CalendarDay(localDate);
            weekModel.add(calendarDay);
        }

        return new CalendarWeek(year, weekModel, weekNumber);
    }
}
