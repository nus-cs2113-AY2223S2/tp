package seedu.apollo.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.task.DateOverException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DeadlineTest {

    Deadline deadline = new Deadline("test", "2024-01-01T23:59");

    DeadlineTest() throws DateOverException {}

    @Test
    void newDeadline_normalDeadline_expectDeadline() throws DateOverException {
        deadline = new Deadline("test", "2025-01-01T23:59");
        assertEquals("[D][ ] test (by: Jan 01 2025, 11:59PM)", deadline.toString());
    }

    @Test
    void newDeadline_invalidDate_expectException() {
        assertThrows(DateTimeParseException.class,
                () -> deadline = new Deadline("test", "tomorrow"));
    }

    @Test
    void newDeadline_overDate_expectException() {
        assertThrows(DateOverException.class,
                () -> deadline = new Deadline("test", "2022-01-01T23:59"));
    }

    @Test
    void getType_normalDeadline_expectDeadline() {
        assertEquals("deadline", deadline.getType());
    }

    @Test
    void getByDate_normalDeadline_expectLocalDateTime() {
        LocalDateTime by = LocalDateTime.parse("2024-01-01T23:59");
        assertEquals(by, deadline.getByDate());
    }

    @Test
    void isOnDate_onDate_expectTrue() {
        LocalDate date = LocalDate.parse("2024-01-01");
        assertTrue(deadline.isOnDate(date));
    }

    @Test
    void isOnDate_notOnDate_expectFalse() {
        LocalDate date = LocalDate.parse("2024-01-02");
        assertFalse(deadline.isOnDate(date));
    }

    @Test
    void testToString_markedDeadline_expectString() {
        deadline.setDone(true);
        assertEquals("[D][X] test (by: Jan 01 2024, 11:59PM)", deadline.toString());
    }
}
