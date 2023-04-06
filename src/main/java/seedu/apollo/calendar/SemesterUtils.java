package seedu.apollo.calendar;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class SemesterUtils {

    private static final LocalDate SEMESTER_START = LocalDate.of(2023, 1, 9);
    private static final LocalDate SEMESTER_END = LocalDate.of(2023, 5, 6);
    private static final LocalDate RECESS_START = LocalDate.of(2023, 2, 19);
    private static final LocalDate RECESS_END = LocalDate.of(2023, 2, 27);

    /**
     * Returns the week number of the semester for a particular date.
     *
     * @param date The date to be checked.
     * @return The week number of the semester.
     */
    public static int getWeekNumber(LocalDate date) {

        int daysBetween;
        int weekNumber;

        if (date.isBefore(SEMESTER_START) || date.isAfter(SEMESTER_END)) {
            return 0;
        }

        if (date.isBefore(RECESS_END) && date.isAfter(RECESS_START)) {
            return -1;
        }

        if (date.isAfter(RECESS_END) || date.isEqual(RECESS_END)) {
            daysBetween = (int) DAYS.between(RECESS_END, date);

            weekNumber = determineWeekNumber(daysBetween);
            weekNumber += 6;
        } else {
            daysBetween = (int) DAYS.between(SEMESTER_START, date);
            weekNumber = determineWeekNumber(daysBetween);
        }

        return weekNumber;
    }

    /**
     * Returns the calculated week number based on the day interval between two dates.
     *
     * @param daysBetween The day interval between two dates.
     * @return The calculated week number of the semester.
     */
    private static int determineWeekNumber(int daysBetween) {
        int weekNumber;

        weekNumber = daysBetween / 7 + 1;

        return weekNumber;
    }
}
