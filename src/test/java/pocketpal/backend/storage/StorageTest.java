// @@author nghochi123
package pocketpal.backend.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import pocketpal.backend.constants.Config;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.backend.exceptions.InvalidReadFileException;

import java.util.List;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Storage")
public class StorageTest {
    private static final String PATH_STRING = "./test/storage.txt";
    @Nested
    @DisplayName("Test create storage without existing file")
    class NoFileTest {
        @Test
        void testCreateStorage() {
            File toBeDeleted = new File(PATH_STRING);
            toBeDeleted.delete();
            assertFalse(Files.exists(Paths.get(PATH_STRING)));
            Storage storage = new Storage(PATH_STRING);
            assertDoesNotThrow(storage::readFromDatabase);
            assertTrue(Files.exists(Paths.get(PATH_STRING)));
            toBeDeleted.delete();
        }
    }

    @Nested
    @DisplayName("Test base read and write functionalities")
    class WriteReadTest {
        private final Storage storage = new Storage(PATH_STRING);
        private final Entry entry1 = new Entry("Mango juice", 2, Category.FOOD);
        private final Entry entry2 = new Entry("Apple juice", 3, Category.OTHERS);
        private final Entry entry3 = new Entry("Grape juice", 4, Category.CLOTHING);

        @Test
        public void testWriteToStorage() {
            assertDoesNotThrow(storage::reset);
            EntryLog entrylog = new EntryLog();
            entrylog.addEntry(entry1);
            entrylog.addEntry(entry2);
            entrylog.addEntry(entry3);
            assertDoesNotThrow(() -> storage.writeToDatabase(entrylog.getEntriesList()));
        }

        @Test
        public void testReadFromStorage() {
            List<Entry> entries = assertDoesNotThrow(storage::readFromDatabase);
            assertEquals(entry1.getCategory(), entries.get(0).getCategory());
            assertEquals(entry1.getDescription(), entries.get(0).getDescription());
            assertEquals(entry1.getAmount(), entries.get(0).getAmount());
            assertEquals(entry2.getCategory(), entries.get(1).getCategory());
            assertEquals(entry2.getDescription(), entries.get(1).getDescription());
            assertEquals(entry2.getAmount(), entries.get(1).getAmount());
            assertEquals(entry3.getCategory(), entries.get(2).getCategory());
            assertEquals(entry3.getDescription(), entries.get(2).getDescription());
            assertEquals(entry3.getAmount(), entries.get(2).getAmount());

        }

        @Test
        public void testStorageNoOverwrite() {
            Storage newStorage = new Storage(PATH_STRING);
            List<Entry> entries = assertDoesNotThrow(newStorage::readFromDatabase);
            assertEquals(entry1.getCategory(), entries.get(0).getCategory());
            assertEquals(entry1.getDescription(), entries.get(0).getDescription());
            assertEquals(entry1.getAmount(), entries.get(0).getAmount());
            assertEquals(entry2.getCategory(), entries.get(1).getCategory());
            assertEquals(entry2.getDescription(), entries.get(1).getDescription());
            assertEquals(entry2.getAmount(), entries.get(1).getAmount());
            assertEquals(entry3.getCategory(), entries.get(2).getCategory());
            assertEquals(entry3.getDescription(), entries.get(2).getDescription());
            assertEquals(entry3.getAmount(), entries.get(2).getAmount());
        }

    }

    @Nested
    @DisplayName("Test invalid read code")
    class ExceptionTests {
        private static final String TEST_DELIMITER = Config.DELIMITER;
        private static final String TEST_DESCRIPTION = "Mango Juice";
        private static final String TEST_AMOUNT_STRING = "4";
        private static final String TEST_CATEGORY_STRING = "Food";
        private static final String TEST_DATE_STRING = "29 Mar 2023; 18:36";
        private static final String INVALID_DELIMITER = ";";
        private static final String INVALID_AMOUNT_STRING = "ABC123";
        private static final String INVALID_CATEGORY_STRING = "ABC123";
        private static final String INVALID_DATE_STRING = "ABC123";
        private static final String BLANK_INPUT = " ";
        FileWriter writer;

        @BeforeEach
        void init() {
            File file = new File(PATH_STRING);
            file.getParentFile().mkdirs();
            assertDoesNotThrow(file::createNewFile);
            assertDoesNotThrow(() -> new FileOutputStream(file, true).close());

        }

        @AfterEach
        void teardown() {
            File toBeDeleted = new File(PATH_STRING);
            toBeDeleted.delete();
        }

        @Test
        public void testInvalidDelimiter() {
            writer = assertDoesNotThrow(() -> new FileWriter(PATH_STRING));
            String writeString = String.join(
                    INVALID_DELIMITER,
                    TEST_DESCRIPTION,
                    TEST_AMOUNT_STRING,
                    TEST_CATEGORY_STRING
            );
            assertDoesNotThrow(() -> writer.append(writeString));
            assertDoesNotThrow(() -> writer.close());

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(
                InvalidReadFileException.class, 
                storage::readFromDatabase
            );
            String expectedMessage = MiscellaneousConstants.GENERAL_STORAGE_ERROR_MESSAGE;
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testInvalidAmount() {
            writer = assertDoesNotThrow(() -> new FileWriter(PATH_STRING));
            String writeString = String.join(
                    TEST_DELIMITER,
                    TEST_DESCRIPTION,
                    INVALID_AMOUNT_STRING,
                    TEST_CATEGORY_STRING,
                    TEST_DATE_STRING
            );
            assertDoesNotThrow(() -> writer.append(writeString));
            assertDoesNotThrow(() -> writer.close());

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(
                InvalidReadFileException.class, 
                storage::readFromDatabase
            );
            String expectedMessage = MiscellaneousConstants.INVALID_AMOUNT_ERROR_MESSAGE;
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testInvalidCategory() {
            writer = assertDoesNotThrow(() -> new FileWriter(PATH_STRING));
            String writeString = String.join(
                    TEST_DELIMITER,
                    TEST_DESCRIPTION,
                    TEST_AMOUNT_STRING,
                    INVALID_CATEGORY_STRING,
                    TEST_DATE_STRING
            );
            assertDoesNotThrow(() -> writer.append(writeString));
            assertDoesNotThrow(() -> writer.close());

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(
                InvalidReadFileException.class, 
                storage::readFromDatabase
            );
            String expectedMessage = MiscellaneousConstants.INVALID_CATEGORY_ERROR_MESSAGE;
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
        
        @Test
        public void testInvalidDate() {
            writer = assertDoesNotThrow(() -> new FileWriter(PATH_STRING));
            String writeString = String.join(
                    TEST_DELIMITER,
                    TEST_DESCRIPTION,
                    TEST_AMOUNT_STRING,
                    TEST_CATEGORY_STRING,
                    INVALID_DATE_STRING
            );
            assertDoesNotThrow(() -> writer.append(writeString));
            assertDoesNotThrow(() -> writer.close());

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(
                InvalidReadFileException.class, 
                storage::readFromDatabase
            );
            String expectedMessage = MiscellaneousConstants.INVALID_DATE_ERROR_MESSAGE;
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testInsufficientColumnsForEntry() {
            writer = assertDoesNotThrow(() -> new FileWriter(PATH_STRING));
            String writeString = String.join(
                    TEST_DELIMITER,
                    TEST_DESCRIPTION,
                    TEST_AMOUNT_STRING
            );
            assertDoesNotThrow(() -> writer.append(writeString));
            assertDoesNotThrow(() -> writer.close());

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(
                InvalidReadFileException.class, 
                storage::readFromDatabase
            );
            String expectedMessage = MiscellaneousConstants.GENERAL_STORAGE_ERROR_MESSAGE;
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testBlankInput() {
            writer = assertDoesNotThrow(() -> new FileWriter(PATH_STRING));
            assertDoesNotThrow(() -> writer.append(BLANK_INPUT));
            assertDoesNotThrow(() -> writer.close());

            Storage storage = new Storage(PATH_STRING);
            AssertionError assertion = assertThrows(
                AssertionError.class, 
                storage::readFromDatabase
            );
            assertEquals(
                MiscellaneousConstants.NO_BLANK_STRING_ALLOWED, 
                assertion.getMessage()
            );
        }
    }

}
// @@author
