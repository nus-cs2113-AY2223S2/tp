package seedu.moneymind;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.moneymind.category.Category;
import seedu.moneymind.command.CategoryCommand;
import seedu.moneymind.event.Event;
import seedu.moneymind.storage.FormatToTxt;
import seedu.moneymind.storage.Storage;

public class StorageTest {
    private HashMap<String, Integer> testCaseHashMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("test1", 1);
        map.put("test2", 2);
        map.put("test3", 3);
        return map;
    }
    private ArrayList<Category> testCaseArrayList() {
        ArrayList<Category> list = new ArrayList<>();
        list.add(new Category("test1"));
        list.get(0).addEvent(new Event("event1", 1234, 5678));
        list.get(0).addEvent(new Event("event2", 9876, 5432));
        list.add(new Category("test2"));
        list.get(1).addEvent(new Event("event3", 1122, 3344));
        list.get(1).addEvent(new Event("event4", 5566, 7788));
        list.add(new Category("test3"));
        return list;
    }

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
        CategoryCommand.categoryMap = testCaseHashMap();

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
     * Expected outcome: Similar ArrayList of Events
     */
    @Test
    public void saveAndLoadFromFile_testCaseArrayList_testCaseArrayList() {
        try {
            Storage storage = new Storage();
            ArrayList<Category> list = testCaseArrayList();
            CategoryCommand.categoryMap = testCaseHashMap();
            
            storage.saveToFile(list);
            ArrayList<Category> list2 = storage.loadFromFile();

            
            // check if data is the same
            assertTrue(FormatToTxt.formatToTxt(list).equals(FormatToTxt.formatToTxt(list2)));
        } catch (Exception e) {
            assertTrue(false, e.getMessage());
        }
    }
}
