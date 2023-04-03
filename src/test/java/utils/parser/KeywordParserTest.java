package utils.parser;

import model.Card;
import model.CardList;
import model.DeckList;
import model.TagList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.Command;
import utils.command.ViewCardCommand;
import utils.exceptions.InkaException;
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

    @Test
    public void parse_missingArgs() throws InkaException {

    }

}
