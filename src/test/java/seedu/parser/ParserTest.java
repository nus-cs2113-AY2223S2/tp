package seedu.parser;


import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.errorcommands.IncorrectSyntaxCommand;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;
import static org.testng.AssertJUnit.assertFalse;


//@@author ZIZI-czh
public class ParserTest {

    /**
     * Test the input for list command
     * if the user type in user /list 556 which is incorrect, then it will show error
     */
    //@@ author ZIZI-czh
    @Test
    public void testProcessCommandIncorrectArguments() {
        String userInput = "/wlist 556";
        Parser testList = new Parser();
        Command result;
        try {
            result = testList.processCommand(userInput);
        } catch (InvalidSyntaxException e) {
            result = new ExitCommand();
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
        //show error, if the result satisfy the condition in IncorrectCommand
        assertFalse(result instanceof IncorrectSyntaxCommand);
    }

}
