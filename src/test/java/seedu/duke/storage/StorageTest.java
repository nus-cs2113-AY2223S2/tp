package seedu.duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;

import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;

@DisplayName("Test Storage")
public class StorageTest {
    private final String pathString = "./test/storage.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void init() {
        // setup streams
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void teardown() {
        // restore streams
        System.setOut(originalOut);
        System.setErr(originalErr);

    }

    @Nested
    @DisplayName("Create storage without existing file")
    class NoFileTest {
        @Test
        void createStorage(){
            File toBeDeleted = new File(pathString);
            toBeDeleted.delete();
            assertTrue(!Files.exists(Paths.get(pathString)));
            Storage storage = new Storage(pathString);
            storage.readFromDatabase();
            assertTrue(Files.exists(Paths.get(pathString)));
            toBeDeleted.delete();
        }
    }

    @Nested
    @DisplayName("Test base functionalities")
    class WriteReadTest {
        private final Storage storage = new Storage(pathString);
        private final Entry entry1 = new Entry("Mango juice", 2, Category.FOOD);
        private final Entry entry2 = new Entry("Apple juice", 3, Category.OTHERS);
        private final Entry entry3 = new Entry("Grape juice", 4, Category.CLOTHING);
        @Test
        public void writeToStorage(){
            storage.reset();
            EntryLog entrylog = new EntryLog();
            entrylog.add(entry1);
            entrylog.add(entry2);
            entrylog.add(entry3);
            storage.writeToDatabase(entrylog.getEntries());
        }
        @Test
        public void readFromStorage(){
            List<Entry> entries = storage.readFromDatabase();
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

}
