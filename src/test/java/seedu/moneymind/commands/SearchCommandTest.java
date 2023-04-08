package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;
import seedu.moneymind.command.SearchCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchCommandTest extends CommandTest {

    public SearchCommandTest() {
        super();
    }

    @Test
    void searchCommand_withQuery_expectCorrectOutput() {
        setup();
        String terminalOutput = executeInput("search hello").toString();
        String expected = "Matching Categories:" + System.lineSeparator() +
                "No matching search results." + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Matching Events:" + System.lineSeparator() +
                "No matching search results." + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Similar Categories:" + System.lineSeparator() +
                "2.book" + System.lineSeparator() +
                "1.food" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Similar Events:" + System.lineSeparator() +
                "Lord of the Rings (Category: book)" + System.lineSeparator() +
                "Harry Potter (Category: book)" + System.lineSeparator() +
                "salad (Category: food)" + System.lineSeparator();
        assertEquals(expected, terminalOutput);
        clear();
    }

    void test() {
        String terminalOutput = executeInput("search hello").toString();
        System.out.println(terminalOutput);
    }

    @Test
    void searchCommand_noQuery_expectErrorMessage() {
        setup();
        String terminalOutput = executeInput("search").toString();
        String expected = "Please use the format: search <keyword>" + System.lineSeparator();
        assertEquals(expected, terminalOutput);
        clear();
    }
}
