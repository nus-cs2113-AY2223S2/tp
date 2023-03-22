package seedu.moneymind;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;

/**
 * Retrieves the current system date and time.
 */
public class UserDate {
    // TODO: Clean up and test code
    // private int day;
    // private int month;
    // private int year;

    public static String getSystemDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = now.format(formatter);
        return formattedDate;
    }
}
