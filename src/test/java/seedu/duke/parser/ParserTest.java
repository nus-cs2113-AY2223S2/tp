package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import seedu.duke.constants.MessageConstants;
import seedu.duke.exceptions.InvalidArgumentsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.MissingArgumentsException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseUserInput_emptyInput_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("");
        });

        String expectedMessage = MessageConstants.MESSAGE_EMPTY_INPUT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_invalidCommand_nullReturned() throws InvalidCommandException, MissingArgumentsException, InvalidArgumentsException {
        Parser parser = new Parser();
        assertEquals(parser.parseUserInput("/invalid"), null);
    }

    @Test
    public void parseUserInput_missingArguments_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/add");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_ARGS_ADD;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_validAddCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("/add coffee -c food -p 3.50");
    }

    @Test
    public void parseUserInput_validViewCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("/view");
    }

    @Test
    public void parseUserInput_validEditCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("/edit 1 -d new description");
    }

    @Test
    public void parseUserInput_validDeleteCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("/delete 1");
    }

    @Test
    public void parseUserInput_validHelpCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("/help");
    }

    @Test
    public void parseUserInput_validByeCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("/bye");
    }

    @Test
    public void parseUserInput_whitespaceTrimmed()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("   /view   ");
    }

    @Test
    public void parseDeleteCommand_missingArguments_exceptionThrown() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser parser = new Parser();
            parser.parseUserInput("/delete");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_ID;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseDeleteCommand_invalidArguments_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/delete abc");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_ID;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseDeleteCommand_validArguments_parsedSuccessfully()
            throws InvalidArgumentsException, MissingArgumentsException, InvalidCommandException {
        Parser parser = new Parser();
        parser.parseUserInput("/delete 1");
    }

    @Test
    public void parseAddCommand_missingArguments_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/add");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_ARGS_ADD;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseViewCommand_validArguments_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser parser = new Parser();
        parser.parseUserInput("/view 10");
    }
}
