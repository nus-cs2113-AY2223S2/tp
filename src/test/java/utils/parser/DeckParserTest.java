package utils.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.CardList;
import model.Deck;
import model.DeckList;
import model.TagList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.Command;
import utils.command.RunCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.storage.FakeStorage;
import utils.storage.Storage;

public class DeckParserTest {

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

    @Test
    public void parser_deck_run() throws InkaException {
        deckList.addDeck(new Deck("testDeck"));
        Command cmd = parser.parseCommand("deck run -d testDeck");
        assert cmd instanceof RunCommand;
    }

    @Test
    public void parser_deck_noSuchDeck() throws InkaException {
        Command cmd = parser.parseCommand("deck run -d testDeck");
        assert cmd instanceof RunCommand;
    }

    @Test
    public void parser_deck_invalidSyntax() {
        String[] testCases = {"deck run", "deck run -d", "deck run -d test test"};
        for (String testCase : testCases) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testCase), "Invalid syntax");
        }
    }

}
