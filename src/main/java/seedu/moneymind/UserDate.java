package seedu.moneymind;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;

/**
 * Retrieves the current system date and time.
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
}
