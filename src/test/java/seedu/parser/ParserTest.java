package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.commands.IncorrectCommand;
import seedu.commands.ListCommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    //@@ author ZIZI-czh
    @Test
    public void testProcessCommandIncorrectArguments() {
        String userInput = "/list 556";
        Parser testList = new Parser();
        Command result = testList.processCommand(userInput);
        assertTrue(result instanceof IncorrectCommand);
    }

}
