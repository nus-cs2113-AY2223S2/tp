package seedu.parser;

import org.junit.jupiter.api.Test;

import seedu.commands.Command;
import seedu.commands.IncorrectCommand;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    /**
     * Test the input for list command
     * if the user type in user /list 556 which is incorrect, then it will show error
     */
    //@@ author ZIZI-czh
    @Test
    public void testProcessCommandIncorrectArguments() {
        String userInput = "/list 556";
        Parser testList = new Parser();
        Command result = testList.processCommand(userInput);
        //show error, if the result satisfy the condition in IncorrectCommand
        assertTrue(result instanceof IncorrectCommand);
    }

}
