package seedu.moneymind.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.moneymind.storage.CategoriesToString.categoriesToString;
import static seedu.moneymind.storage.StringToCategories.stringToCategories;
import static seedu.moneymind.string.Strings.STORAGE_CATEGORY_NAME;
import static seedu.moneymind.string.Strings.NEW_LINE;
import static seedu.moneymind.string.Strings.STORAGE_NEXT_VARIABLE;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

public class FormatStorageTest {
    
    private ArrayList<Category> storageTestData() {
        ArrayList<Category> storageTestData = new ArrayList<Category>();
        Category food = new Category("food");
        food.addEvent(new Event("McDonalds", 10, "13/02/2030 12:00"));
        food.addEvent(new Event("KFC", 20, "13/02/2030 12:00"));
        storageTestData.add(food);
        Category transport = new Category("transport");
        transport.addEvent(new Event("Grab", 10, "13/02/2030 12:00"));
        transport.addEvent(new Event("Uber", 20, "13/02/2030 12:00"));
        storageTestData.add(transport);
        return storageTestData;
    }

    @Test
    void categoriesToString_storageTestData_formattedString() {
        String expected = STORAGE_CATEGORY_NAME + "food" + NEW_LINE 
                + STORAGE_NEXT_VARIABLE + "McDonalds" + STORAGE_NEXT_VARIABLE + "10" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE + STORAGE_NEXT_VARIABLE + "KFC" + STORAGE_NEXT_VARIABLE + "20" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE + STORAGE_CATEGORY_NAME + "transport" + NEW_LINE 
                + STORAGE_NEXT_VARIABLE + "Grab" + STORAGE_NEXT_VARIABLE + "10" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE + STORAGE_NEXT_VARIABLE + "Uber" + STORAGE_NEXT_VARIABLE + "20" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE;
        assertEquals(expected, categoriesToString(storageTestData()));
    }
            
    @Test
    void stringToCategories_storageTestData_formattedString() {
        String testSavedText = STORAGE_CATEGORY_NAME + "food" + NEW_LINE 
                + STORAGE_NEXT_VARIABLE + "McDonalds" + STORAGE_NEXT_VARIABLE + "10" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE + STORAGE_NEXT_VARIABLE + "KFC" + STORAGE_NEXT_VARIABLE + "20" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE + STORAGE_CATEGORY_NAME + "transport" + NEW_LINE 
                + STORAGE_NEXT_VARIABLE + "Grab" + STORAGE_NEXT_VARIABLE + "10" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE + STORAGE_NEXT_VARIABLE + "Uber" + STORAGE_NEXT_VARIABLE + "20" 
                + STORAGE_NEXT_VARIABLE + "13/02/2030 12:00" + STORAGE_NEXT_VARIABLE + "false"
                + NEW_LINE;
        ArrayList<Category> newList = stringToCategories(testSavedText);
        assertTrue(newList.size() == 2);
        assertTrue(newList.get(0).getName().equals("food"));
        assertTrue(newList.get(0).getEvents().size() == 2);
        assertTrue(newList.get(0).getEvents().get(0).getDescription().equals("McDonalds"));
        assertTrue(newList.get(0).getEvents().get(0).getExpense() == 10);
        assertTrue(newList.get(0).getEvents().get(1).getDescription().equals("KFC"));
        assertTrue(newList.get(0).getEvents().get(1).getExpense() == 20);
        assertTrue(newList.get(1).getName().equals("transport"));
        assertTrue(newList.get(1).getEvents().size() == 2);
        assertTrue(newList.get(1).getEvents().get(0).getDescription().equals("Grab"));
        assertTrue(newList.get(1).getEvents().get(0).getExpense() == 10);
        assertTrue(newList.get(1).getEvents().get(1).getDescription().equals("Uber"));
        assertTrue(newList.get(1).getEvents().get(1).getExpense() == 20);
    }
}
