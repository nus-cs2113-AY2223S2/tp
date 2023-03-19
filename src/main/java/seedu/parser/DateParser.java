package seedu.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.exceptions.InvalidDateException;

public class DateParser {
    public static LocalDate parse(String dateString, DateTimeFormatter dtf) throws InvalidDateException {
        try {
            return LocalDate.parse(dateString, dtf);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(dateString);
        }
    }
}
