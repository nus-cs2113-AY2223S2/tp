package seedu.moneymind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.moneymind.Reminder.checkCategoryReminder;
import static seedu.moneymind.string.Strings.NEW_LINE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

public class ReminderTest {

    private ArrayList<Category> storageTestData() {
        LocalDate now = LocalDate.now().plusDays(2);
        String date = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ArrayList<Category> storageTestData = new ArrayList<Category>();
        Category food = new Category("food");
        food.addEvent(new Event("McDonalds", 10));
        food.addEvent(new Event("KFC", 20, "13/02/2024"));
        storageTestData.add(food);
        Category transport = new Category("transport");
        transport.addEvent(new Event("Grab", 10, "31/01/2023"));
        transport.addEvent(new Event("Uber", 20, date));
        storageTestData.add(transport);
        return storageTestData;
    }
    
    @Test
    void checkCategoryReminder_input_output() {
        String expectedOutput = "Approaching expenses:" + NEW_LINE
                + "transport has an event: Uber in 2 days" + NEW_LINE;
        String actualOutput = checkCategoryReminder(storageTestData());
        assertEquals(expectedOutput, actualOutput, actualOutput);
    }
}
