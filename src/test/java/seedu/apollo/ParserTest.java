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

    @Test
    void parseDeadline_normalDeadline_expectDescriptionAndBy() throws InvalidDeadline {
        String param = "test /by tomorrow";
        String[] descriptionAndBy = Parser.parseDeadline(param);
        assertEquals("test", descriptionAndBy[0]);
        assertEquals("tomorrow", descriptionAndBy[1]);
    }
    @Test
    void parseDeadline_noBy_expectException() {
        String param = "test";
        assertThrows(InvalidDeadline.class,
                () -> Parser.parseDeadline(param));
    }

    @Test
    void parseDeadline_emptyBy_expectException() {
        String param = "test /by ";
        assertThrows(InvalidDeadline.class,
                () -> Parser.parseDeadline(param));
    }

    @Test
    void parseDeadline_noDescription_expectException() {
        String param = "  /by tomorrow";
        assertThrows(InvalidDeadline.class,
                () -> Parser.parseDeadline(param));
    }

}
