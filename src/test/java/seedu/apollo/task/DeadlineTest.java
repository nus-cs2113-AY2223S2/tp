package seedu.apollo.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.task.DateOverException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DeadlineTest {

    Deadline deadline = new Deadline("test", "01-01-2024-23:59");
    public static DateTimeFormatter storePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm", Locale.ENGLISH);
    DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    DeadlineTest() throws DateOverException {}

    @Test
    void newDeadline_normalDeadline_expectDeadline() throws DateOverException {
        deadline = new Deadline("test", "01-01-2025-23:59");
        assertEquals("[D][ ] test (by: 01 Jan 2025, 11:59PM)", deadline.toString());
    }

    @Test
    void newDeadline_invalidDate_expectException() {
        assertThrows(DateTimeParseException.class,
                () -> deadline = new Deadline("test", "tomorrow"));
    }

    @Test
    void newDeadline_overDate_expectException() {
        assertThrows(DateOverException.class,
                () -> deadline = new Deadline("test", "01-01-2022-23:59"));
    }

    @Test
    void getType_normalDeadline_expectDeadlineString() {
        assertEquals("deadline", deadline.getType());
    }

    @Test
    void getByDate_normalDeadline_expectLocalDateTime() {
        LocalDateTime by = LocalDateTime.parse("01-01-2024-23:59",storePattern);
        assertEquals(by, deadline.getByDate());
    }

    @Test
    void isOnDate_onDate_expectTrue() {
        LocalDate date = LocalDate.parse("01-01-2024",datePattern);
        assertTrue(deadline.isOnDate(date));
    }

    @Test
    void isOnDate_notOnDate_expectFalse() {
        LocalDate date = LocalDate.parse("02-01-2024",datePattern);
        assertFalse(deadline.isOnDate(date));
    }

    @Test
    void testToString_markedDeadline_expectString() {
        deadline.setDone(true);
        assertEquals("[D][X] test (by: 01 Jan 2024, 11:59PM)", deadline.toString());
    }
}
