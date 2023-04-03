package utils.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;

public class KeywordParserTest {

    private Parser parser;

    /**
     * Each test should have a new instance of all these
     */
    @BeforeEach
    void init() {
        parser = new Parser();
    }

    //
    //  Formatting tests for error messages
    //

    @Test
    public void parse_alreadySelected_format() {
        String testInput = "card delete -i 1 -c 00000000-0000-0000-0000-000000000000";
        String expectedMessage = InvalidSyntaxException.buildAlreadySelectedMessage("-c / -i").getUiMessage();

        InkaException ex = assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                "Should be invalid syntax");

        assertEquals(ex.getUiMessage(), expectedMessage);
    }

    @Test
    public void parse_missingArgument_format() {
        String[] testInputs = {"card add -q QUESTION -a", "card add -q -a ANSWER"};
        String[] expectedFlags = {"-a", "-q"};

        for (int i = 0; i < testInputs.length; i++) {
            final String testInput = testInputs[i];
            InkaException ex = assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput));

            String expected = InvalidSyntaxException.buildMissingArgumentMessage(expectedFlags[i]).getUiMessage();
            assertEquals(ex.getUiMessage(), expected);
        }
    }

    @Test
    public void parse_missingOption_format() {
        String[] testInputs = {"card add -q QUESTION", "card delete", "tag edit"};
        List<List<String>> expectedFlags = Arrays.asList(
                List.of("-a"),
                List.of("-c / -i"),
                Arrays.asList("-o", "-n")
        );

        for (int i = 0; i < testInputs.length; i++) {
            final String testInput = testInputs[i];
            InkaException ex = assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput));

            String expected = InvalidSyntaxException.buildMissingOptionMessage(expectedFlags.get(i)).getUiMessage();
            assertEquals(ex.getUiMessage(), expected);
        }
    }

    @Test
    public void parse_unrecognizedOption_format() {
        String[] testInputs = {"card add -i 1 -q QUESTION -a ANSWER", "card add -c -q -a ANSWER", "card delete -x 1"};
        String[] expectedFlags = {"-i", "-c", "-x"};

        for (int i = 0; i < testInputs.length; i++) {
            final String testInput = testInputs[i];
            InkaException ex = assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput));

            String expected = InvalidSyntaxException.buildUnrecognizedOptionMessage(expectedFlags[i]).getUiMessage();
            assertEquals(ex.getUiMessage(), expected);
        }
    }
}
