package seedu.apollo.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.task.DateOrderException;
import seedu.apollo.exception.task.DateOverException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTest {

    Event event = new Event("test", "2024-01-01T23:59", "2024-02-01T23:59");

    EventTest() throws DateOrderException, DateOverException {}

    @Test
    void newEvent_normalEvent_expectEvent() throws DateOverException, DateOrderException {
        event = new Event("test", "2025-01-01T23:59", "2025-02-01T23:59");
        assertEquals("[E][ ] test (from: Jan 01 2025, 11:59PM to: Feb 01 2025, 11:59PM)", event.toString());
    }

    @Test
    void newEvent_invalidFrom_expectException() {
        assertThrows(DateTimeParseException.class,
                () -> event = new Event("test", "today", "2025-02-01T23:59"));
    }

    @Test
    void newEvent_invalidTo_expectException() {
        assertThrows(DateTimeParseException.class,
                () -> event = new Event("test", "2025-01-01T23:59", "later"));
    }

    @Test
    void newEvent_overDate_expectException() {
        assertThrows(DateOverException.class,
                () -> event = new Event("test", "2022-01-01T23:59", "2022-02-01T23:59"));
    }

    @Test
    void newEvent_orderDate_expectException() {
        assertThrows(DateOrderException.class,
                () -> event = new Event("test", "2025-01-02T23:59", "2025-01-01T23:59"));
    }

    @Test
    void getType_normalDeadline_expectDeadline() {
        assertEquals("event", event.getType());
    }

    @Test
    void getFromDate_normalEvent_expectLocalDateTime() {
        LocalDateTime from = LocalDateTime.parse("2024-01-01T23:59");
        assertEquals(from, event.getFromDate());
    }

    @Test
    void getToDate_normalEvent_expectLocalDateTime() {
        LocalDateTime to = LocalDateTime.parse("2024-02-01T23:59");
        assertEquals(to, event.getToDate());
    }

    @Test
    void isOnDate_sameAsFrom_expectTrue() {
        LocalDate date = LocalDate.parse("2024-01-01");
        assertTrue(event.isOnDate(date));
    }

    @Test
    void isOnDate_sameAsTo_expectTrue() {
        LocalDate date = LocalDate.parse("2024-01-02");
        assertTrue(event.isOnDate(date));
    }

    @Test
    void isOnDate_betweenFromTo_expectTrue() {
        LocalDate date = LocalDate.parse("2024-01-15");
        assertTrue(event.isOnDate(date));
    }

    @Test
    void isOnDate_notOnDate_expectFalse() {
        LocalDate date = LocalDate.parse("2025-01-01");
        assertFalse(event.isOnDate(date));
    }

    @Test
    void testToString_markedEvent_expectString() {
        event.setDone(true);
        assertEquals("[E][X] test (from: Jan 01 2024, 11:59PM to: Feb 01 2024, 11:59PM)", event.toString());
    }
}
