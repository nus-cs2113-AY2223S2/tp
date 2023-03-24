package seedu.apollo.utils;

import java.time.DayOfWeek;

public class DayTypeUtil {

    /**
     * Converts a DayOfWeek enum to an integer.
     *
     * @param dayInt The integer to be converted.
     * @return The  DayFfWeek enum representation of the integer.
     */
    public static DayOfWeek determineDay(int dayInt) {
        switch (dayInt) {
        case 0:
            return DayOfWeek.MONDAY;
        case 1:
            return DayOfWeek.TUESDAY;
        case 2:
            return DayOfWeek.WEDNESDAY;
        case 3:
            return DayOfWeek.THURSDAY;
        case 4:
            return DayOfWeek.FRIDAY;
        case 5:
            return DayOfWeek.SATURDAY;
        case 6:
            return DayOfWeek.SUNDAY;
        default:
            return null;
        }
    }

    /**
     * Converts a day string to an integer.
     *
     * @param day The string to be converted.
     * @return The integer representation of the day string.
     */
    public static int determineDay(String day) {
        switch (day) {
        case "Monday":
            return 0;
        case "Tuesday":
            return 1;
        case "Wednesday":
            return 2;
        case "Thursday":
            return 3;
        case "Friday":
            return 4;
        case "Saturday":
            return 5;
        case "Sunday":
            return 6;
        default:
            return -1;
        }
    }

}
