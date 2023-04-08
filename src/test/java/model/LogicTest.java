package model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.AddCardCommand;
import utils.command.AddCardToDeckCommand;
import utils.command.AddCardToTagCommand;
import utils.command.AddTagToDeckCommand;
import utils.command.Command;
import utils.command.RemoveCardFromDeckCommand;
import utils.command.RemoveTagFromDeckCommand;
import utils.exceptions.CardNotInDeck;
import utils.exceptions.InkaException;
import utils.exceptions.TagNeverWasInDeck;
import utils.parser.Parser;
import utils.storage.FakeStorage;
import utils.storage.Storage;

/**
 * Integration tests
 */
public class LogicTest {

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

    void parseAndExecute(String userInput, Class expectedClass) throws InkaException {
        Command cmd = parser.parseCommand(userInput);
        assert expectedClass.isInstance(cmd);
        cmd.execute(cardList, tagList, deckList, ui, storage);
    }

    /**
     * Card that is added through CardUUID and Tag should only appear once
     */
    @Test
    public void logic_noDuplicateCard() throws InkaException {
        // Create new card
        parseAndExecute("card add -q test -a test", AddCardCommand.class);
        assert cardList.size() == 1;

        // Create new tag
        parseAndExecute("card tag -i 1 -t testTag", AddCardToTagCommand.class);
        assert tagList.size() == 1;

        // Add via CardUUID to deck
        parseAndExecute("card deck -d testDeck -i 1", AddCardToDeckCommand.class);
        assert deckList.size() == 1;
        assert deckList.get(0).getCardsSet().size() == 1;

        // Add via Tag to deck
        parseAndExecute("tag deck -d testDeck -x 1", AddTagToDeckCommand.class);
        assert deckList.get(0).getCardsSet().size() == 1;
    }

    /**
     * Card that is added through CardUUID and Tag should remain in Deck if only either is removed
     */
    @Disabled
    @Test
    public void logic_noDoubleDelete_deleteByUuidFirst() throws InkaException {
        // Copy state
        logic_noDuplicateCard();

        // Delete by CardUUID
        CardUUID cardUUID = deckList.get(0).getCardsUUID().get(0);
        parseAndExecute("deck delete -d testDeck -c " + cardUUID.toString(), RemoveCardFromDeckCommand.class);

        // Should not remove card; card still included via Tag
        assert deckList.get(0).getCardsSet().size() == 1;

        // Delete by Tag
        parseAndExecute("deck delete -d testDeck -t testTag", RemoveTagFromDeckCommand.class);

        // Card no longer added by CardUUID or Tag
        // TODO: Failing!
        assert deckList.get(0).getCardsSet().size() == 0;
    }

    @Disabled
    @Test
    public void logic_noDoubleDelete_deleteByTagFirst() throws InkaException {
        // Copy state
        logic_noDuplicateCard();

        // Delete by Tag
        parseAndExecute("deck delete -d testDeck -t testTag", RemoveTagFromDeckCommand.class);

        // Should not remove card; card still included via CardUUID
        assert deckList.get(0).getCardsSet().size() == 1;

        // Delete by CardUUID
        CardUUID cardUUID = deckList.get(0).getCardsUUID().get(0);
        parseAndExecute("deck delete -d testDeck -c " + cardUUID.toString(), RemoveCardFromDeckCommand.class);

        // Card no longer added by CardUUID or Tag
        // TODO: Failing!
        assert deckList.get(0).getCardsSet().size() == 0;
    }

    /**
     * Add Card 1 via CardUUID, then Card 2 via Tag
     */
    @Test
    public void logic_addCards() throws InkaException {
        // Create new cards
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        parseAndExecute("card add -q test2 -a test2", AddCardCommand.class);

        assert cardList.size() == 2;

        // Tag card 1
        parseAndExecute("card tag -i 1 -t testTag", AddCardToTagCommand.class);
        assert tagList.size() == 1;

        // Add card 1 via Tag
        parseAndExecute("tag deck -d testDeck -t testTag", AddTagToDeckCommand.class);

        // Add card 2 via CardUUID
        parseAndExecute("card deck -d testDeck -i 2", AddCardToDeckCommand.class);
        assert deckList.get(0).getCardsSet().size() == 2;
    }

    /**
     * If card is added to Deck via Tag, should not be able to delete via CardUUID
     */
    @Test
    public void logic_addByTagDeleteByCardUUID() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        CardUUID cardUUID = cardList.get(0).getUuid();

        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);

        // Add card via Tag
        parseAndExecute("tag deck -t testTag -d testDeck", AddTagToDeckCommand.class);
        assert deckList.get(0).getCardsSet().size() == 1;

        // Remove card via CardUUID
        try {
            parseAndExecute("deck delete -d testDeck -c " + cardUUID, RemoveCardFromDeckCommand.class);
        } catch (CardNotInDeck ex) {
            // TODO: This shows "The card is not in the deck" which might be misleading
        }

        // Card should remain in Deck
        assert deckList.get(0).getCardsSet().size() == 1;
    }

    /**
     * If card is added to Deck via CardUUID, should not be able to delete via Tag
     */
    @Test
    public void logic_addByCardUUIDDeleteByTag() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        CardUUID cardUUID = cardList.get(0).getUuid();

        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);

        // Add card via CardUUID
        parseAndExecute("card deck -d testDeck -c " + cardUUID, AddCardToDeckCommand.class);
        assert deckList.get(0).getCardsSet().size() == 1;

        // Remove card via CardUUID
        assertThrows(TagNeverWasInDeck.class,
                () -> parseAndExecute("deck delete -d testDeck -t testTag", RemoveTagFromDeckCommand.class),
                "Tag not added to deck");


        // Card should remain in Deck
        assert deckList.get(0).getCardsSet().size() == 1;
    }
}
