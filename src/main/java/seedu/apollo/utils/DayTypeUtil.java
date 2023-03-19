package seedu.apollo.utils;

import java.time.DayOfWeek;

public class DayTypeUtil {

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

}
