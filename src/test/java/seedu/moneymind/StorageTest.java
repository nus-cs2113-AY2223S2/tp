package seedu.moneymind;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.moneymind.storage.Storage;

public class StorageTest {
    /** 
     * Tests setupFile() method<p>
     * Expected outcome: No exception thrown
     */
    @Test
    public void setupFile_null_noExceptionThrown() {
        assertDoesNotThrow(() -> {
            Storage storage = new Storage();
            System.out.println(storage.getClass());
        });
    }

    /**
     * Tests saveToFile() method<p>
     * Input: ArrayList of 1 Event(description: "test", budget: 1234, expense: 5678)<p>
     * Expected outcome: No exception thrown
     */
    @Test
    public void saveToFile_descInput_exptOutcome() {
        Storage storage = new Storage();
        ArrayList<Category> list = new ArrayList<>();
        // TODO: Add a test case for saving a list of events
        // list.add(new Event("test", 1234, 5678));
        assertDoesNotThrow(() -> {
            storage.saveToFile(list);
        });
    }

    /** 
     * Tests loadFromFile() method<p>
     * Expected outcome: No exception thrown
     */
    @Test
    public void loadFromFile_null_noExceptionThrown() {
        try {
            Storage storage = new Storage();
            ArrayList<Category> list = storage.loadFromFile();
            System.out.println(list);
        } catch (Exception e) {
            assertTrue(false, e.getMessage());
        }
    }

    /** 
     * Tests save and load from file sequence<p>
     * Input: ArrayList of Events<p>
     * Expected outcome: No exception thrown
     */
    @Test
    public void saveAndLoadFromFile_descInput_exptOutcome() {
        try {
            Storage storage = new Storage();
            ArrayList<Category> list = new ArrayList<>();
            // TODO: Add a test case for saving a list of events
            // list.add(new Event("test", 1234, 5678));
            // list.add(new Event("test2", 9876, 5432));
            storage.saveToFile(list);
            ArrayList<Category> list2 = storage.loadFromFile();
            System.out.println(list2);
        } catch (Exception e) {
            assertTrue(false, e.getMessage());
        }
    }
}
