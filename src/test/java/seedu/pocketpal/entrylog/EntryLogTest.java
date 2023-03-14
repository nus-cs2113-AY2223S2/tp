package seedu.pocketpal.entrylog;

import seedu.pocketpal.constants.MessageConstants;
import seedu.pocketpal.entries.Category;
import seedu.pocketpal.entries.Entry;
import seedu.pocketpal.exceptions.InvalidArgumentsException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntryLogTest {
    private static final Entry entry1 = new Entry(
            "5 packets of dried mango",
            5.43,
            Category.FOOD);
    private static final Entry entry2 = new Entry(
            "Grab ride to mango farm at 2am",
            16.40,
            Category.TRANSPORTATION);
    private static final Entry entry3 = new Entry(
            "Food poisoning",
            99.99,
            Category.MEDICAL);
    private static final Entry entry4 = new Entry(
            "Mango juice",
            3.50,
            Category.FOOD);
    private static final Entry entry5 = new Entry(
            "Bus ride home",
            1.45,
            Category.TRANSPORTATION);
    private static final Entry entry6 = new Entry(
            "Ambulance bill",
            399,
            Category.MEDICAL);
    private EntryLog entryLog;

    @BeforeEach
    void init() {
        entryLog = new EntryLog();
        entryLog.addEntry(entry1);
        entryLog.addEntry(entry2);
        entryLog.addEntry(entry3);
    }

    @Nested
    @DisplayName("Test getters")
    class EntryLogGettersTest {
        @Test
        void getEntries() {
            List<Entry> expectedList = new ArrayList<>();
            expectedList.add(entry1);
            expectedList.add(entry2);
            expectedList.add(entry3);
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
                entryLog.addEntry(entry4);
                Entry addedEntry = entryLog.getEntry(entryLog.getSize() - 1);
                assertEquals(addedEntry, entry4);
            });
        }

        @Test
        void getEntry_negativeId_exceptionThrown() {
            Exception invalidIdException = assertThrows(
                    InvalidArgumentsException.class,
                    () -> entryLog.getEntry(-1));
            String exceptionMessage = invalidIdException.getMessage();
            assertTrue(exceptionMessage.contains(MessageConstants.MESSAGE_INVALID_ID));
        }

        @Test
        void getEntry_idMoreThanSize_exceptionThrown() {
            Exception invalidIdException = assertThrows(
                    InvalidArgumentsException.class,
                    () -> entryLog.getEntry(entryLog.getSize() + 10));
            String exceptionMessage = invalidIdException.getMessage();
            assertTrue(exceptionMessage.contains(MessageConstants.MESSAGE_INVALID_ID));
        }

        @Test
        void deleteEntry_validId_returnsEntry() {
            assertDoesNotThrow(() -> {
                Entry deletedEntry = entryLog.deleteEntry(0);
                assertEquals(entryLog.getSize(), 2);
                assertEquals(deletedEntry, entry1);
            });
        }

        @Test
        void deleteEntry_negativeId_exceptionThrown() {
            Exception invalidIdException = assertThrows(
                    InvalidArgumentsException.class,
                    () -> entryLog.deleteEntry(-1));
            String exceptionMessage = invalidIdException.getMessage();
            assertTrue(exceptionMessage.contains(MessageConstants.MESSAGE_INVALID_ID));
        }

        @Test
        void deleteEntry_idMoreThanSize_exceptionThrown() {
            Exception invalidIdException = assertThrows(
                    InvalidArgumentsException.class,
                    () -> entryLog.deleteEntry(entryLog.getSize() + 10));
            String exceptionMessage = invalidIdException.getMessage();
            assertTrue(exceptionMessage.contains(MessageConstants.MESSAGE_INVALID_ID));
        }
    }

    @Nested
    @DisplayName("Test filter methods")
    class EntryLogFilterTest {
        @BeforeEach
        void init() {
            entryLog.addEntry(entry4);
            entryLog.addEntry(entry5);
            entryLog.addEntry(entry6);
        }

        @Test
        void filterAmount_withinRange() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry1);
            expectedEntries.add(entry2);
            expectedEntries.add(entry4);
            expectedEntries.add(entry5);
            EntryLog filteredEntries = entryLog.filterByAmount(0, 90);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterAmount_minAmount() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry3);
            expectedEntries.add(entry6);
            EntryLog filteredEntries = entryLog.filterByAmount(90, Double.MAX_VALUE);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterAmount_maxAmount() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry1);
            expectedEntries.add(entry4);
            expectedEntries.add(entry5);
            EntryLog filteredEntries = entryLog.filterByAmount(Double.MIN_VALUE, 10);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryCaseInsensitive() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry1);
            expectedEntries.add(entry2);
            expectedEntries.add(entry4);
            EntryLog filteredEntries = entryLog.filterByQuery("MANGO");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryWithoutSpaces() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry1);
            expectedEntries.add(entry2);
            expectedEntries.add(entry4);
            EntryLog filteredEntries = entryLog.filterByQuery("mango");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryWithSpaces() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry2);
            EntryLog filteredEntries = entryLog.filterByQuery("mango farm");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterByQuery_queryRegex() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry1);
            expectedEntries.add(entry2);
            EntryLog filteredEntries = entryLog.filterByQuery("\\d+");
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }

        @Test
        void filterCategory() {
            List<Entry> expectedEntries = new ArrayList<>();
            expectedEntries.add(entry1);
            expectedEntries.add(entry4);
            EntryLog filteredEntries = entryLog.filterByCategory(Category.FOOD);
            assertEquals(filteredEntries.getEntriesList(), expectedEntries);
        }
    }
}
