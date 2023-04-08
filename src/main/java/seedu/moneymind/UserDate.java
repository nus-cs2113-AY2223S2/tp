package seedu.moneymind;

import static java.time.temporal.ChronoUnit.DAYS;
import static seedu.moneymind.string.Strings.DATE_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deals with the current system date and time.
 */
public class UserDate {

    /**
     * Returns the current system date.
     *
     * @return The current system date.
     */
    public static String getSystemDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    /**
     * Returns true if the date is valid.
     *
     * @param date The date to be checked.
     * @return True if the date is valid.
     */
    public static Boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            LocalDateTime.parse(date, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if the date is approaching.
     *
     * @param date The date to be checked.
     * @return True if the date is approaching.
     */
    public static Boolean isApproaching(String date) {
        return (numberDaysAway(date) <= 5 && numberDaysAway(date) >= 0);
    }

    /**
     * Returns the number of days away from the current date.
     *
     * @param date The date to be checked.
     * @return The number of days away from the current date.
     */
    public static Integer numberDaysAway(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inputDate = LocalDateTime.parse(date, formatter);
        return (int) now.until(inputDate, DAYS);
    }

    /**
     * Returns the date of the next monthly expense.
     *
     * @param oldDate The date to be updated.
     * @return The date after updating.
     */
    public static String updateDate(String oldDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime inputDate = LocalDateTime.parse(oldDate, formatter);
        while (inputDate.isBefore(LocalDateTime.now())) {
            inputDate = inputDate.plusMonths(1);
        }
        return inputDate.format(formatter);
    }

}
