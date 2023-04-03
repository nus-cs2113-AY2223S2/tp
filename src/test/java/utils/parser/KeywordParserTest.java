package utils.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.CardList;
import model.DeckList;
import model.TagList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.storage.FakeStorage;
import utils.storage.Storage;

public class KeywordParserTest {

    private CardList cardList;
    private TagList tagList;
    private UserInterface ui;
    private Storage storage;
    private Parser parser;
    private DeckList deckList;

    /**
     * Each test should have a new instance of all these
     */
    @BeforeEach
    void init() {
        cardList = new CardList();
        tagList = new TagList();
        ui = new UserInterface();
        storage = new FakeStorage();
        parser = new Parser();
        deckList = new DeckList();
    }

    //
    //  Formatting tests for error messages
    //

    @Test
    public void parse_alreadySelected_format() throws InkaException {
        String testInput = "card delete -i 1 -c 00000000-0000-0000-0000-000000000000";
        String expectedMessage = InvalidSyntaxException.buildAlreadySelectedMessage("-c / -i").getUiMessage();

        InkaException ex = assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                "Should be invalid syntax");

        assertEquals(ex.getUiMessage(), expectedMessage);
    }

    @Test
    public void parse_missingArgument_format() {
        String[] testInputs = {"card add -q QUESTION -a", "card add -q -a ANSWER"};
        String[] expectedMessages = {
                InvalidSyntaxException.buildMissingArgumentMessage("-a").getUiMessage(),
                InvalidSyntaxException.buildMissingArgumentMessage("-q").getUiMessage()
        };
        assert testInputs.length == expectedMessages.length;

        for (int i = 0; i < testInputs.length; i++) {
            final String testInput = testInputs[i];
            InkaException ex = assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput));
            assertEquals(ex.getUiMessage(), expectedMessages[i]);
        }
    }

    @Test
    public void parse_unrecognizedOption_format() {
        String[] testInputs = {"card add -i 1 -q QUESTION -a ANSWER", "card add -c -q -a ANSWER", "card delete -x 1"};
        String[] expectedMessages = {
                InvalidSyntaxException.buildUnrecognizedOptionMessage("-i").getUiMessage(),
                InvalidSyntaxException.buildUnrecognizedOptionMessage("-c").getUiMessage(),
                InvalidSyntaxException.buildUnrecognizedOptionMessage("-x").getUiMessage()
        };
        assert testInputs.length == expectedMessages.length;

        for (int i = 0; i < testInputs.length; i++) {
            final String testInput = testInputs[i];
            InkaException ex = assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput));
            assertEquals(ex.getUiMessage(), expectedMessages[i]);
        }
    }
}
