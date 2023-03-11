package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidArgumentsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.MissingArgumentsException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseUserInput_emptyInput_exceptionThrown() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser.parseUserInput("");
        });

        String expectedMessage = Parser.MESSAGE_EMPTY_INPUT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_invalidCommand_exceptionThrown() {
        Exception exception = assertThrows(InvalidCommandException.class, () -> {
            Parser.parseUserInput("/invalid");
        });
        String expectedMessage = Parser.MESSAGE_INVALID_COMMAND;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_missingArguments_exceptionThrown() {
        assertThrows(MissingArgumentsException.class, () -> {Parser.parseUserInput("/add");});
    }

    @Test
    public void parseUserInput_invalidArguments_exceptionThrown() {
        assertThrows(InvalidArgumentsException.class, () -> Parser.parseUserInput("/add desc cat"));
    }

    @Test
    public void parseUserInput_validAddCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/add buy coffee -c food -p 3.50");
    }

    @Test
    public void testParseUserInput_validViewCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/view");
    }

    @Test
    public void testParseUserInput_validEditCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/edit 1 desc new description");
    }

    @Test
    public void testParseUserInput_validDeleteCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/delete 1");
    }

    @Test
    public void testParseUserInput_validHelpCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/help");
    }

    @Test
    public void testParseUserInput_validByeCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/bye");
    }

    @Test
    public void testParseUserInput_whitespaceTrimmed()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("   /view   ");
    }

    @Test
    public void testParseDeleteCommand_missingArguments_exceptionThrown() {
        assertThrows(MissingArgumentsException.class, () -> Parser.parseUserInput("/delete"));
    }

    @Test
    public void testParseDeleteCommand_invalidArguments_exceptionThrown() {
        assertThrows(InvalidArgumentsException.class, () -> Parser.parseUserInput("/delete abc"));
    }

    @Test
    public void testParseDeleteCommand_validArguments_parsedSuccessfully()
            throws InvalidArgumentsException, MissingArgumentsException, InvalidCommandException {
        Parser.parseUserInput("/delete 1");
    }

    @Test
    public void testParseAddCommand_missingArguments_exceptionThrown() {
        assertThrows(MissingArgumentsException.class, () -> Parser.parseUserInput("/add"));
    }

    @Test
    public void testParseAddCommand_invalidArguments_exceptionThrown() {
        assertThrows(InvalidArgumentsException.class, () -> Parser.parseUserInput("/add desc cat"));
    }

    @Test
    public void testParseAddCommand_validArguments_parsedSuccessfully()
            throws InvalidArgumentsException, MissingArgumentsException, InvalidCommandException {
        Parser.parseUserInput("/add buy coffee food 3.50");
    }

    @Test
    public void testParseViewCommand_validArguments_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        // Parser.parse
    }
}