package pocketpal.data.entrylog;

import pocketpal.data.EntryTestUtil;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pocketpal.frontend.exceptions.InvalidDateException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntryLogTest extends EntryTestUtil {
    private EntryLog entryLog;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    @BeforeEach
    void init() {
        entryLog = new EntryLog();
        entryLog.addEntry(ENTRY_1);
        entryLog.addEntry(ENTRY_2);
        entryLog.addEntry(ENTRY_3);
    }

    @Nested
    @DisplayName("Test getters")
    class EntryLogGettersTest {
        @Test
        void getEntries() {
            List<Entry> expectedList = new ArrayList<>();
            expectedList.add(ENTRY_1);
            expectedList.add(ENTRY_2);
            expectedList.add(ENTRY_3);
            assertEquals(expectedList, entryLog.getEntriesList());
        }

        @Test
        void getSize() {
            assertEquals(entryLog.getSize(), entryLog.getEntriesList().size());
        }
    }

    @Nested
    @DisplayName("Test entry CRUD")
    class EntryLogCRUDTest {
        @Test
        void add_entry_noExceptionThrown() {
            assertDoesNotThrow(() -> {
                entryLog.addEntry(ENTRY_4);
                Entry addedEntry = entryLog.getEntry(entryLog.getSize());
                assertEquals(addedEntry, ENTRY_4);
            });
        }

        @Test
        void getEntry_negativeId_returnsNull() {
            assertNull(entryLog.getEntry(-1));
        }

        @Test
        void getEntry_idMoreThanSize_returnsNull() {
            assertNull(entryLog.getEntry(entryLog.getSize() + 10));
        }

        @Test
        void deleteEntry_validId_returnsEntry() {
            assertDoesNotThrow(() -> {
                Entry deletedEntry = entryLog.deleteEntry(0);
                assertEquals(entryLog.getSize(), 2);
                assertEquals(deletedEntry, ENTRY_1);
            });
        }

        @Test
        void deleteEntry_negativeId_exceptionThrown() {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> entryLog.deleteEntry(-1));
        }

        @Test
        void deleteEntry_idMoreThanSize_exceptionThrown() {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> entryLog.deleteEntry(entryLog.getSize() + 10));
        }
    }

    @Nested
    @DisplayName("Test filter methods")
    class EntryLogFilterTest {
        @BeforeEach
        void init() {
            entryLog.addEntry(ENTRY_4);
            entryLog.addEntry(ENTRY_5);
            entryLog.addEntry(ENTRY_6);
        }

        @Test
        void filterAmount_withinRange() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_1);
            expectedEntries.add(ENTRY_2);
            expectedEntries.add(ENTRY_4);
            expectedEntries.add(ENTRY_5);
            EntryLog filteredEntries = entryLog.filterByAmount(0, 90);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterAmount_minAmount() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_3);
            expectedEntries.add(ENTRY_6);
            EntryLog filteredEntries = entryLog.filterByAmount(90, Double.MAX_VALUE);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterAmount_maxAmount() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_1);
            expectedEntries.add(ENTRY_4);
            expectedEntries.add(ENTRY_5);
            EntryLog filteredEntries = entryLog.filterByAmount(Double.MIN_VALUE, 10);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryCaseInsensitive() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_1);
            expectedEntries.add(ENTRY_2);
            expectedEntries.add(ENTRY_4);
            EntryLog filteredEntries = entryLog.filterByQuery("MANGO");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryWithoutSpaces() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_1);
            expectedEntries.add(ENTRY_2);
            expectedEntries.add(ENTRY_4);
            EntryLog filteredEntries = entryLog.filterByQuery("mango");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryWithSpaces() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_2);
            EntryLog filteredEntries = entryLog.filterByQuery("mango farm");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryRegex() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_1);
            expectedEntries.add(ENTRY_2);
            EntryLog filteredEntries = entryLog.filterByQuery("\\d+");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterCategory() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(ENTRY_1);
            expectedEntries.add(ENTRY_4);
            EntryLog filteredEntries = entryLog.filterByCategory(Category.FOOD);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterBetweenDates_noEntriesBetweenDates() throws InvalidDateException {
            LocalDateTime startDateTime = LocalDateTime.parse("21/11/24 23:30", formatter);
            LocalDateTime endDateTime = LocalDateTime.parse("30/11/26 23:30", formatter);
            EntryLog filteredEntries = entryLog.filterBetweenDates(startDateTime, endDateTime);
            assertEquals(filteredEntries.getEntriesList(), new EntryLog().getEntriesList());
        }

        @Test
        void filterBetweenDates() throws InvalidDateException {
            LocalDateTime startDateTime = LocalDateTime.parse("28/03/23 10:53", formatter);
            LocalDateTime endDateTime = LocalDateTime.parse("30/11/26 23:30", formatter);
            EntryLog filteredEntries = entryLog.filterBetweenDates(startDateTime, endDateTime);
            assertEquals(filteredEntries.getEntriesList(), entryLog.getEntriesList());
        }
        // @@author
    }
}
