package seedu.moneymind;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    Category food = new Category("food");
    Category book = new Category("book");
    Event salad = new Event("salad", 100, 50);
    Event pizza = new Event("pizza", 200, 100);
    Event harryPotter = new Event("Harry Potter", 70, 50);
    Event lordOfTheRings = new Event("Lord of the Rings", 90, 20);

    private void setup() {
        food.addEvent(salad);
        food.addEvent(pizza);
        book.addEvent(harryPotter);
        book.addEvent(lordOfTheRings);
        CategoryList.categories.add(food);
        CategoryList.categories.add(book);
        CategoryCommand.categoryMap.put("food", 0);
        CategoryCommand.categoryMap.put("book", 1);
    }

    @Test
    void addCategory_oneCategory_expectThreeCategoryInCategoryList() {
        setup();
        String input = "category travel";
        executeInput(input);
        assertTrue(CategoryList.categories.size() == 3);
        assertTrue(CategoryList.categories.get(2).getName().equals("travel"));
        clear();
    }

    @Test
    void addEvent_oneFoodEvent_expectThreeEventsInFoodCategory() {
        setup();
        String input = "event banana b/20 e/10";
        String categoryIndex = "1\n"; // replace with the correct input string
        InputStream in = new ByteArrayInputStream(categoryIndex.getBytes());
        System.setIn(in);
        executeInput(input);
        assertTrue(food.events.get(2).getDescription().equals("banana"));
        assertTrue(food.events.get(2).getBudget() == 20);
        assertTrue(food.events.get(2).getExpense() == 10);
        clear();
    }

    @Test
    void deleteWholeCategory_oneCategory_expectOneCategoryInList() {
        setup();
        String input = "delete c/food";
        executeInput(input);
        assertTrue(CategoryList.categories.size() == 1);
        assertTrue(CategoryList.categories.get(0).getName().equals("book"));
        clear();
    }

    @Test
    void deleteEvent_oneBookEvent_expectOneEventInBookCategory() {
        setup();
        String input = "delete c/book e/Harry Potter";
        executeInput(input);
        assertTrue(book.events.size() == 1);
        assertTrue(book.events.get(0).getDescription().equals("Lord of the Rings"));
        clear();
    }

    @Test
    void viewAll_expectEverythingToBePrintedOut() {
        setup();
        String input = "view";
        Parser parser = new Parser();
        // Get help from chatGPT for the next 2 lines
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.splitKeywordAndDescription(input);
        parser.executeUserInput();
        String actual = outputStream.toString();
        String expected = "1.food" + System.lineSeparator()
                + "salad [budget]100 [expense]50" + System.lineSeparator()
                + "pizza [budget]200 [expense]100" + System.lineSeparator()
                + "2.book" + System.lineSeparator()
                + "Harry Potter [budget]70 [expense]50" + System.lineSeparator()
                + "Lord of the Rings [budget]90 [expense]20" + System.lineSeparator();
        assertEquals(expected, actual);
        clear();
    }

    @Test
    void viewCategory_expectCategoryToBePrintedOut() {
        setup();
        String input = "view food";
        Parser parser = new Parser();
        // Get help from chatGPT for the next 2 lines
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.splitKeywordAndDescription(input);
        parser.executeUserInput();
        String actual = outputStream.toString();
        String expected = "1. salad [budget]100 [expense]50" + System.lineSeparator()
                + "2. pizza [budget]200 [expense]100" + System.lineSeparator();
        assertEquals(expected, actual);
        clear();
    }

    private static void executeInput(String input) {
        Parser parser = new Parser();
        // Get help from chatGPT for the next 2 lines
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.splitKeywordAndDescription(input);
        parser.executeUserInput();
    }

    /**
     * Clears all static variables
     */
    private static void clear() {
        CategoryList.categories.clear();
        CategoryCommand.categoryMap.clear();
    }

}
