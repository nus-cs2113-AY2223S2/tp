package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewCommandTest extends CommandTest {

    public ViewCommandTest() {
        super();
    }

    @Test
    void viewCommand_viewAll_expectEverythingToBePrintedOut() {
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
    void viewCommand_viewOneCategory_expectCategoryToBePrintedOut() {
        setup();
        String terminalOutput = executeInput("view food").toString();
        String expected = "1. salad [expense]100" + System.lineSeparator()
                + "2. pizza [expense]200" + System.lineSeparator();
        assertEquals(expected, terminalOutput);
        clear();
    }

}
