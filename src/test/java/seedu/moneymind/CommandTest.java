package seedu.moneymind;

import org.junit.jupiter.api.Test;

import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.command.CategoryCommand;
import seedu.moneymind.command.Command;
import seedu.moneymind.command.Parser;
import seedu.moneymind.event.Event;
import seedu.moneymind.string.Strings;
import seedu.moneymind.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(3, CategoryList.categories.size());
        assertEquals("travel", CategoryList.categories.get(2).getName());
        clear();
    }

    @Test
    void addEvent_oneFoodEvent_expectThreeEventsInFoodCategory() {
        setup();
        String input = "event banana b/20 e/10";
        String categoryIndex = "1" + Strings.NEW_LINE; // replace with the correct input string
        Moneymind.in = new Scanner(categoryIndex);
        executeInput(input);
        Moneymind.in = new Scanner(System.in);
        assertEquals("banana", food.events.get(2).getDescription(), 
                "expected: banana, actual: " + food.events.get(2).getDescription());
        assertEquals(20, food.events.get(2).getBudget(), 
                "expected: 20, actual: " + food.events.get(2).getBudget());
        assertEquals(10, food.events.get(2).getExpense(), 
                "expected: 10, actual: " + food.events.get(2).getExpense());
        clear();
    }

    @Test
    void deleteWholeCategory_oneCategory_expectOneCategoryInList() {
        setup();
        String input = "delete c/food";
        executeInput(input);
        assertEquals(1, CategoryList.categories.size());
        assertEquals("book", CategoryList.categories.get(0).getName());
        clear();
    }

    @Test
    void deleteEvent_oneBookEvent_expectOneEventInBookCategory() {
        setup();
        String input = "delete c/book e/Harry Potter";
        executeInput(input);
        assertEquals(1, book.events.size());
        assertEquals("Lord of the Rings", book.events.get(0).getDescription());
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

        try {
            Command command = parser.parseNextCommand(input);
            command.execute(new Ui());
        } catch (Exception e) {
            System.exit(-1);
        }

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

        try {
            Command command = parser.parseNextCommand(input);
            command.execute(new Ui());
        } catch (Exception e) {
            System.exit(-1);
        }

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
        try {
            Command command = parser.parseNextCommand(input);
            command.execute(new Ui());
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    /**
     * Clears all static variables
     */
    private static void clear() {
        CategoryList.categories.clear();
        CategoryCommand.categoryMap.clear();
    }
}
