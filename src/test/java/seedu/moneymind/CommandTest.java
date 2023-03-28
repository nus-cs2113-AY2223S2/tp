package seedu.moneymind;

import org.junit.jupiter.api.Test;

import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.command.CategoryCommand;
import seedu.moneymind.command.Command;
import seedu.moneymind.command.Parser;
import seedu.moneymind.event.Event;
import seedu.moneymind.exceptions.InvalidCommandException;
import seedu.moneymind.string.Strings;
import seedu.moneymind.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    Category food = new Category("food");
    Category book = new Category("book");
    Event salad = new Event("salad", 100);
    Event pizza = new Event("pizza", 200);
    Event harryPotter = new Event("Harry Potter", 70);
    Event lordOfTheRings = new Event("Lord of the Rings", 90);

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
    void addCategory_oneCategoryAndNoBudget_expectThreeCategoryInCategoryList() {
        setup();
        executeInput("category travel");
        assertEquals(3, CategoryList.categories.size());
        assertEquals("travel", CategoryList.categories.get(2).getName());
        assertEquals(0, CategoryList.categories.get(2).getBudget());
        clear();
    }

    @Test
    void addCategory_sameCategory_expectCategoryExistsMessage() {
        setup();
        String terminalOutput = executeInput("category food").toString();
        assertEquals("Category already exists" + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        assertEquals("food", CategoryList.categories.get(0).getName());
        clear();
    }

    @Test
    void addCategory_dummyBudget_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("category travel b/ad").toString();
        assertEquals("Please give a positive integer for budget" + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        assertEquals("food", CategoryList.categories.get(0).getName());
        clear();
    }

    @Test
    void addCategory_negativeBudget_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("category travel b/-12").toString();
        assertEquals("Please give a positive integer for budget" + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void addCategory_oneCategoryWithBudget_expectThreeCategoryInCategoryList() {
        setup();
        executeInput("category travel b/100");
        assertEquals(3, CategoryList.categories.size());
        assertEquals("travel", CategoryList.categories.get(2).getName());
        assertEquals(100, CategoryList.categories.get(2).getBudget());
        clear();
    }

    @Test
    void addCategory_emptyDescription_expectEmptyDescriptionMessage() {
        setup();
        String terminalOutput = executeInput("category").toString();
        assertEquals("☹ OOPS!!! The description of a category cannot be empty."
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void addCategory_emptyCategoryName_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("category b/100").toString();
        assertEquals("Please following the correct format: category NAME [(optional) b/<budget number>]\n" +
                "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void addCategory_twoBudgetSpecifiers_expectMatchingTheLastSpecifier() {
        setup();
        String terminalOutput = executeInput("category travel b/12 b/12").toString();
        assertEquals(3, CategoryList.categories.size());
        assertEquals("travel b/12", CategoryList.categories.get(2).getName());
        assertEquals(12, CategoryList.categories.get(2).getBudget());
        clear();
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
        assertEquals("Please following the correct format: event NAME e/<expense number> [(optional) t/<time>]\n"
                + "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void addEvent_emptyDescription_expectEmptyEventMessage() {
        setup();
        String terminalOutput = executeInput("event").toString();
        assertEquals("☹ OOPS!!! The description of an event cannot be empty." +
                System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void addEvent_emptyEventName_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("event e/20").toString();
        assertEquals("Please following the correct format: event NAME e/<expense number> [(optional) t/<time>]\n"
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
        assertEquals("Please following the correct format: event NAME e/<expense number> [(optional) t/<time>]\n"
                        + "Remember do not leave any things inside the brackets empty!" + System.lineSeparator(),
                terminalOutput);
        clear();
    }

    @Test
    void addEvent_negativeExpense_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("event banana e/-20").toString();
        assertEquals("Please give a positive integer for expense" + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void addEvent_dummyExpense_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("event banana e/abc").toString();
        assertEquals("Please give a positive integer for expense" + System.lineSeparator(), terminalOutput);
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
                "Please try again or enter back to go back to the main program" + System.lineSeparator(), terminalOutput);
        assertEquals(0, travel.events.size());
        clear();
    }



    @Test
    void editEvent_oneEvent_expectEventEdited() {
        setup();
        String newExpense = "12" + Strings.NEW_LINE;
        Moneymind.in = new Scanner(newExpense);
        executeInput("edit c/food e/1");
        assertEquals(12, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void deleteWholeCategory_oneCategory_expectOneCategoryInList() {
        setup();
        executeInput("delete c/food");
        assertEquals(1, CategoryList.categories.size());
        assertEquals("book", CategoryList.categories.get(0).getName());
        clear();
    }

    @Test
    void deleteEvent_oneBookEvent_expectOneEventInBookCategory() {
        setup();
        executeInput("delete c/book e/1");
        assertEquals(1, book.events.size());
        assertEquals("Lord of the Rings", book.events.get(0).getDescription());
        clear();
    }

    @Test
    void deleteWholeCategory_nonExistedCategory_expectCategoryDoesNotExistMessage() {
        setup();
        String terminalOutput = executeInput("delete c/travel").toString();
        assertEquals("Category does not exist" + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void deleteCommand_emptyDescription_expectEmptyDeleteMessage() {
        setup();
        String terminalOutput = executeInput("delete").toString();
        assertEquals("☹ OOPS!!! The description of a delete cannot be empty." +
                System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void deleteEvent_emptyCategory_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("delete e/1").toString();
        assertEquals("Please following the correct format: delete c/<category name> [(optional) e/<event index>]\n"
                + "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void deleteEvent_outOfBoundEventIndex_expectEventDoesNotExist() {
        setup();
        String terminalOutput = executeInput("delete c/food e/3").toString();
        assertEquals("Event does not exist" + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void deleteEvent_dummyInputForEventIndex_expectPositiveIntegerForEventIndexMessage() {
        setup();
        String terminalOutput = executeInput("delete c/food e/abc").toString();
        assertEquals("Please give a positive integer for event index" + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void viewAll_expectEverythingToBePrintedOut() {
        setup();
        String terminalOutput = executeInput("view").toString();
        String expected = "1.food (budget: 0)" + System.lineSeparator()
                + "salad [expense]100" + System.lineSeparator()
                + "pizza [expense]200" + System.lineSeparator()
                + "2.book (budget: 0)" + System.lineSeparator()
                + "Harry Potter [expense]70" + System.lineSeparator()
                + "Lord of the Rings [expense]90" + System.lineSeparator();
        assertEquals(expected, terminalOutput);
        clear();
    }

    @Test
    void viewCategory_expectCategoryToBePrintedOut() {
        setup();
        String terminalOutput = executeInput("view food").toString();
        String expected = "1. salad [expense]100" + System.lineSeparator()
                + "2. pizza [expense]200" + System.lineSeparator();
        assertEquals(expected, terminalOutput);
        clear();
    }

    private ByteArrayOutputStream executeInput(String input) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        // Get help from chatGPT for the next 2 lines
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            Command command = parser.parseNextCommand(input);
            command.execute(ui);
        } catch (InvalidCommandException error) {
            error.showErrorMessage();
        } catch (Exception e) {
            // no code needed here
        }
        return outputStream;
    }

    /**
     * Clears all static variables
     */
    private static void clear() {
        CategoryList.categories.clear();
        CategoryCommand.categoryMap.clear();
    }

}
