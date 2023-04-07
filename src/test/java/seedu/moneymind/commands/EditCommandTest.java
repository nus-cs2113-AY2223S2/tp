package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;
import seedu.moneymind.Moneymind;
import seedu.moneymind.string.Strings;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCommandTest extends CommandTest {

    public EditCommandTest() {
        super();
    }

    @Test
    void editEvent_oneEvent_expectEventEdited() {
        setup();
        String newExpense = "12" + Strings.NEW_LINE;
        Moneymind.in = new Scanner(newExpense);
        String terminalOutput = executeInput("edit c/food e/1").toString();
        assertEquals("The current event expense for salad is: 100" + System.lineSeparator() +
                "Your new expense would be:" + System.lineSeparator() +
                "Ok, the new expense is now changed to: 12" + System.lineSeparator(), terminalOutput);
        assertEquals(12, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_dummyInput_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("edit hsadis").toString();
        assertEquals("Please following the correct format: edit c/<category name> e/<event index>\n"
                + "Remember do not leave any things inside the brackets empty!" +
                System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_emptyDescription_expectEmptyEditCommandMessage() {
        setup();
        String terminalOutput = executeInput("edit").toString();
        assertEquals("OOPS!!! The description of an edit cannot be empty." +
                System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_emptyCategory_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("edit c/ e/1").toString();
        assertEquals("Please following the correct format: edit c/<category name> e/<event index>\n"
                + "Remember do not leave any things inside the brackets empty!" +
                System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_emptyEventIndex_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("edit c/food e/").toString();
        assertEquals("Please following the correct format: edit c/<category name> e/<event index>\n"
                + "Remember do not leave any things inside the brackets empty!" +
                System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_negativeEventIndex_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("edit c/food e/-1").toString();
        assertEquals("Please give a positive integer for event index" + System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_dummyEventIndex_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("edit c/food e/abc").toString();
        assertEquals("Please give a positive integer for event index" + System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_nonExistedEventIndex_expectEventDoesNotExistMessage() {
        setup();
        String terminalOutput = executeInput("edit c/food e/3").toString();
        assertEquals("Event does not exist" + System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_nonExistedCategory_expectCategoryDoesNotExistMessage() {
        setup();
        String terminalOutput = executeInput("edit c/travel e/1").toString();
        assertEquals("Category does not exist" + System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_negativeEditAmount_expectPositiveNumberMessage() {
        setup();
        String newExpense = "-12" + Strings.NEW_LINE;
        Moneymind.in = new Scanner(newExpense);
        String terminalOutput = executeInput("edit c/food e/1").toString();
        assertEquals("The current event expense for salad is: 100" + System.lineSeparator() +
                "Your new expense would be:" + System.lineSeparator() +
                "Please enter a non-negative number or enter back to go back to the main program"
                + System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

    @Test
    void editEvent_dummyEditAmount_expectNumberMessage() {
        setup();
        String newExpense = "asfd" + Strings.NEW_LINE;
        Moneymind.in = new Scanner(newExpense);
        String terminalOutput = executeInput("edit c/food e/1").toString();
        assertEquals("The current event expense for salad is: 100" + System.lineSeparator() +
                "Your new expense would be:" + System.lineSeparator() +
                "Please enter a non-negative number or enter back to go back to the main program"
                + System.lineSeparator(), terminalOutput);
        assertEquals(100, food.events.get(0).getExpense());
        clear();
    }

}
