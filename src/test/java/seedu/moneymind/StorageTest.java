package seedu.moneymind;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.moneymind.storage.FormatToTxt;
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

        // add test data
        list.add(new Category("test cat"));
        list.get(0).addEvent(new Event("test1", 1234, 5678));
        list.get(0).addEvent(new Event("test2", 9876, 5432));
        list.add(new Category("test dog"));
        list.get(1).addEvent(new Event("test3", 1234, 5678));
        list.get(1).addEvent(new Event("test4", 9876, 5432));

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

            // add test data
            list.add(new Category("test_cat"));
            list.get(0).addEvent(new Event("test1", 1234, 5678));
            list.get(0).addEvent(new Event("test2", 9876, 5432));
            list.add(new Category("test_dog"));
            list.get(1).addEvent(new Event("test3", 1122, 3344));
            list.get(1).addEvent(new Event("test4", 5566, 7788));
            
            storage.saveToFile(list);
            ArrayList<Category> list2 = storage.loadFromFile();

            
            // check if data is the same
            assertTrue(FormatToTxt.formatToTxt(list).equals(FormatToTxt.formatToTxt(list2)));
        } catch (Exception e) {
            assertTrue(false, e.getMessage());
        }
    }
}
