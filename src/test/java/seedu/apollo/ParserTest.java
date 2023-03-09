package seedu.apollo;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.InvalidDeadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseDateTime_dateLocalDateTime_expectDateString() {

        LocalDateTime date = LocalDateTime.parse("2023-01-01T23:59");
        String dateString = null;
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
        String output = Parser.parseDateTime(date, dateString, pattern);
        assertEquals("Jan 01 2023, 11:59PM", output);

    }

    @Test
    void parseDateTime_dateString_expectDateString() {

        LocalDateTime date = null;
        String dateString = "test";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
        String output = Parser.parseDateTime(date, dateString, pattern);
        assertEquals("test", output);

    }

}
