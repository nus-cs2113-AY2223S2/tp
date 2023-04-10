package seedu.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseDate {
    public static LocalDate parseDate(String dateVal) throws IndexOutOfBoundsException {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateVal, output);
    }
}
