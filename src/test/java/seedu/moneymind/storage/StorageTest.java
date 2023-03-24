package seedu.moneymind.storage;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private ArrayList<Category> storageTestData() {
        ArrayList<Category> storageTestData = new ArrayList<Category>();
        Category food = new Category("food");
        food.addEvent(new Event("McDonalds", 10));
        food.addEvent(new Event("KFC", 20));
        storageTestData.add(food);
        Category transport = new Category("transport");
        transport.addEvent(new Event("Grab", 10));
        transport.addEvent(new Event("Uber", 20));
        storageTestData.add(transport);
        return storageTestData;
    }

    private HashMap<String, Integer> storageTestDataHashMap() {
        HashMap<String, Integer> storageTestDataHashMap = new HashMap<String, Integer>();
        storageTestDataHashMap.put("food", 0);
        storageTestDataHashMap.put("transport", 1);
        return storageTestDataHashMap;
    }

    @Test
    void constructor_nullFilePath_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    void constructor_validFilePath_fileCreated() {
        new Storage("testFile.txt");
        File testFile = new File("testFile.txt");
        assertTrue(testFile.exists());
        assertTrue(!testFile.isDirectory());
        testFile.delete();
    }

    @Test
    void save_nullArrayList_nullPointerException() {
        Storage storage = new Storage("testFile.txt");
        assertThrows(NullPointerException.class, () -> storage.save(null));
        new File("testFile.txt").delete();
    }
    
    @Test
    void save_emptyArrayList_noExceptionThrown() {
        Storage storage = new Storage("testFile.txt");
        assertDoesNotThrow(() -> storage.save(new ArrayList<Category>()));
        new File("testFile.txt").delete();
    }
    
    @Test
    void save_validArrayList_saveFileUpdated() throws InterruptedException {
        Storage storage = new Storage("testFile.txt");
        File testFile = new File("testFile.txt");
        Long timeStamp = testFile.lastModified();
        Thread.sleep(5);
        storage.save(storageTestData());
        assertTrue(testFile.lastModified() != timeStamp);
        testFile.delete();
    }
    
    @Test
    void load_noInput_noExceptionThrown() {
        Storage storage = new Storage("testFile.txt");
        assertDoesNotThrow(() -> storage.load());
        new File("testFile.txt").delete();
    }
    
    @Test
    void getSavedCategories_validArrayList_equalToInput() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Category> expectedOutput = storageTestData();
        Storage storage = new Storage("testFile.txt");
        Field fieldUnderTest = Storage.class.getDeclaredField("savedCategories");
        fieldUnderTest.setAccessible(true);
        fieldUnderTest.set(storage, expectedOutput);
        assertEquals(expectedOutput, storage.getSavedCategories());
        new File("testFile.txt").delete();
    }

    @Test
    void getSavedCategoryHashMap_validHashMap_equalToInput() throws NoSuchFieldException, 
            SecurityException, IllegalArgumentException, IllegalAccessException {
        HashMap<String, Integer> expectedOutput = storageTestDataHashMap();
        Storage storage = new Storage("testFile.txt");
        Field fieldUnderTest = Storage.class.getDeclaredField("savedCategoryHashMap");
        fieldUnderTest.setAccessible(true);
        fieldUnderTest.set(storage, expectedOutput);
        assertEquals(expectedOutput, storage.getSavedCategoryHashMap());
        new File("testFile.txt").delete();
    }
}
