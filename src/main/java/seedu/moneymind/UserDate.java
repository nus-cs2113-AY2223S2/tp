package seedu.moneymind;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
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
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(date, formatter);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return (int) now.until(inputDate, DAYS);
    }

    /**
     * Returns the date of the next monthly expense.
     *
     * @param oldDate The date to be updated.
     * @return The date after updating.
     */
    public static String updateDate(String oldDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(oldDate, formatter);
        while (inputDate.isBefore(LocalDate.now())) {
            inputDate = inputDate.plusMonths(1);
        }
        return inputDate.format(formatter);
    }

}
