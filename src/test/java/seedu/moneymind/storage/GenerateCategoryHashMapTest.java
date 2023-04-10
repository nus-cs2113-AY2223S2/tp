package seedu.moneymind.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.moneymind.storage.GenerateCategoryHashMap.generateCategoryHashMap;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

public class GenerateCategoryHashMapTest {

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
    void generateCategoryHashMap_storageTestCase_equalToInput() {
        assertEquals(storageTestDataHashMap(), generateCategoryHashMap(storageTestData()));
    }
}
