package seedu.apollo.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.task.DateOrderException;
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

class EventTest {
    public static DateTimeFormatter storePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm", Locale.ENGLISH);
    Event event = new Event("test", "01-01-2024-23:59", "01-02-2024-23:59");

    DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    EventTest() throws DateOrderException, DateOverException {
    }

    @Test
    void newEvent_normalEvent_expectEvent() throws DateOverException, DateOrderException {
        event = new Event("test", "01-01-2025-23:59", "01-02-2025-23:59");
        assertEquals("[E][ ] test (from: 01 Jan 2025, 11:59PM to: 01 Feb 2025, 11:59PM)", event.toString());
    }

    @Test
    void newEvent_invalidFrom_expectException() {
        assertThrows(DateTimeParseException.class,
                () -> event = new Event("test", "today", "01-02-2025-23:59"));
    }

    @Test
    void newEvent_invalidTo_expectException() {
        assertThrows(DateTimeParseException.class,
                () -> event = new Event("test", "01-01-2025-23:59", "later"));
    }

    @Test
    void newEvent_overDate_expectException() {
        assertThrows(DateOverException.class,
                () -> event = new Event("test", "01-01-2022-23:59", "01-02-2022-23:59"));
    }

    @Test
    void newEvent_orderDate_expectException() {
        assertThrows(DateOrderException.class,
                () -> event = new Event("test", "02-01-2025-23:59", "01-01-2025-23:59"));
    }

    @Test
    void getType_normalEvent_expectEventString() {
        assertEquals("event", event.getType());
    }

    @Test
    void getFromDate_normalEvent_expectLocalDateTime() {
        LocalDateTime from = LocalDateTime.parse("01-01-2024-23:59", storePattern);
        assertEquals(from, event.getFromDate());
    }

    @Test
    void getToDate_normalEvent_expectLocalDateTime() {
        LocalDateTime to = LocalDateTime.parse("01-02-2024-23:59", storePattern);
        assertEquals(to, event.getToDate());
    }

    @Test
    void isOnDate_sameAsFrom_expectTrue() {
        LocalDate date = LocalDate.parse("01-01-2024", datePattern);
        assertTrue(event.isOnDate(date));
    }

    @Test
    void isOnDate_sameAsTo_expectTrue() {
        LocalDate date = LocalDate.parse("02-01-2024", datePattern);
        assertTrue(event.isOnDate(date));
    }

    @Test
    void isOnDate_betweenFromTo_expectTrue() {
        LocalDate date = LocalDate.parse("15-01-2024", datePattern);
        assertTrue(event.isOnDate(date));
    }

    @Test
    void isOnDate_notOnDate_expectFalse() {
        LocalDate date = LocalDate.parse("01-01-2025", datePattern);
        assertFalse(event.isOnDate(date));
    }

    @Test
    void testToString_markedEvent_expectString() {
        event.setDone(true);
        assertEquals("[E][X] test (from: 01 Jan 2024, 11:59PM to: 01 Feb 2024, 11:59PM)", event.toString());
    }
}
