package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.MissingArgumentsException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_emptyInput_returns() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser.parseUserInput("");
        });

        String expectedMessage = Parser.MESSAGE_EMPTY_INPUT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}