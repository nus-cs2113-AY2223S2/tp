package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;
import seedu.moneymind.Moneymind;
import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.command.CategoryCommand;
import seedu.moneymind.string.Strings;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest extends CommandTest {
    public EventCommandTest() {
        super();
    }

    @Test
    void addEvent_oneNonRecurringEvent_expectThreeEventsInFoodCategory() {
        setup();
        String categoryName = "food" + Strings.NEW_LINE; // replace with the correct input string
        Moneymind.in = new Scanner(categoryName);
        executeInput("event banana e/20");
        assertEquals("banana", food.events.get(2).getDescription(),
                "expected: banana, actual: " + food.events.get(2).getDescription());
        assertEquals(20, food.events.get(2).getExpense(),
                "expected: 20, actual: " + food.events.get(2).getExpense());
        clear();
    }

    @Test
    void addEvent_oneRecurringEvent_expectThreeEventsInFoodCategory() {
        setup();
        String categoryName = "food" + Strings.NEW_LINE; // replace with the correct input string
        Moneymind.in = new Scanner(categoryName);
        executeInput("event banana e/20 t/123");
        assertEquals("banana", food.events.get(2).getDescription(),
                "expected: banana, actual: " + food.events.get(2).getDescription());
        assertEquals(20, food.events.get(2).getExpense(),
                "expected: 20, actual: " + food.events.get(2).getExpense());
        clear();
    }

    @Test
    void addEvent_oneEventWithNoExpense_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("event banana").toString();
        assertEquals("Please following the correct format: event <name> e/<expense number> [(optional) t/<time>]\n"
                + "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void addEvent_emptyDescription_expectEmptyEventMessage() {
        setup();
        String terminalOutput = executeInput("event").toString();
        assertEquals("OOPS!!! The description of an event cannot be empty." +
                System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void addEvent_emptyEventName_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("event e/20").toString();
        assertEquals("Please following the correct format: event <name> e/<expense number> [(optional) t/<time>]\n"
                + "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void addEvent_emptyExpense_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("event banana e/").toString();
        assertEquals(2, food.events.size());
        assertEquals("Please following the correct format: event <name> e/<expense number> [(optional) t/<time>]\n"
                        + "Remember do not leave any things inside the brackets empty!" + System.lineSeparator(),
                terminalOutput);
        clear();
    }

    @Test
    void addEvent_negativeExpense_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("event banana e/-20").toString();
        assertEquals("Please give a non-negative integer for expense" + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void addEvent_dummyExpense_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("event banana e/abc").toString();
        assertEquals("Please give a non-negative integer for expense" + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }


    @Test
    void addEvent_oneEventToNewCategory_expectNewCategoryInCategoryList() {
        setup();
        Category travel = new Category("travel", 200);
        CategoryList.categories.add(travel);
        CategoryCommand.categoryMap.put("travel", 2);
        String categoryName = "travel" + Strings.NEW_LINE; // replace with the correct input string
        Moneymind.in = new Scanner(categoryName);
        String terminalOutput = executeInput("event buy tent e/20").toString();
        assertEquals("Please select the category you want to add the event to:" + System.lineSeparator() +
                "New event added: buy tent" + System.lineSeparator(), terminalOutput);
        assertEquals(3, CategoryList.categories.size());
        assertEquals("buy tent", travel.events.get(0).getDescription());
        clear();
    }

    @Test
    void addEvent_oneEventToNewCategoryWithDummyCategoryInput_expectTryAgainMessage() {
        setup();
        Category travel = new Category("travel", 200);
        CategoryList.categories.add(travel);
        CategoryCommand.categoryMap.put("travel", 2);
        String categoryName = "haha" + Strings.NEW_LINE; // replace with the correct input string
        Moneymind.in = new Scanner(categoryName);
        String terminalOutput = executeInput("event buy tent e/20").toString();
        assertEquals("Please select the category you want to add the event to:" + System.lineSeparator() +
                "Category does not exist!" + System.lineSeparator() +
                "Please try again or enter back to go back to the main program" +
                System.lineSeparator(), terminalOutput);
        assertEquals(0, travel.events.size());
        clear();
    }

    @Test
    void addEvent_spareSlash_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("event banana/ e/20").toString();
        assertEquals("Please following the correct format: event <name> e/<expense number> [(optional) t/<time>]\n"
                + "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

}
