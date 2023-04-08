package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;
import seedu.moneymind.category.CategoryList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest extends CommandTest {

    public DeleteCommandTest() {
        super();
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
        assertEquals("OOPS!!! The description of a delete cannot be empty." +
                System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void deleteEvent_emptyCategory_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("delete c/ e/1").toString();
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
    void deleteEvent_negativeEventIndex_expectPositiveIntegerForEventIndexMessage() {
        setup();
        String terminalOutput = executeInput("delete c/food e/-1").toString();
        assertEquals("Please give a positive integer for event index" + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void deleteEvent_emptyEventIndex_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("delete c/food e/").toString();
        assertEquals("Please following the correct format: delete c/<category name> [(optional) e/<event index>]\n"
                + "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

    @Test
    void deleteEvent_spareSlash_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("delete c/food/ e/1").toString();
        assertEquals("Please following the correct format: delete c/<category name> [(optional) e/<event index>]\n"
                + "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, food.events.size());
        clear();
    }

}
