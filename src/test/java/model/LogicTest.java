package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.AddCardCommand;
import utils.command.AddCardToDeckCommand;
import utils.command.AddCardToTagCommand;
import utils.command.AddTagToDeckCommand;
import utils.command.Command;
import utils.command.DeleteDeckCommand;
import utils.command.DeleteTagCommand;
import utils.command.EditDeckNameCommand;
import utils.command.EditTagNameCommand;
import utils.command.ListCardsInTagCommand;
import utils.command.RemoveCardFromDeckCommand;
import utils.command.RemoveTagFromCardCommand;
import utils.command.RemoveTagFromDeckCommand;
import utils.exceptions.CardInTagException;
import utils.exceptions.CardNotFoundException;
import utils.exceptions.CardInSetNotInList;
import utils.exceptions.InkaException;
import utils.exceptions.LongDeckNameException;
import utils.exceptions.LongTagNameException;
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
        storage = new FakeStorage(ui);
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
        assert deckList.get(0).getCardsSet().size() == 0;
    }

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
        } catch (CardInSetNotInList ex) {
            //Should throw out an exception
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



    /**
     * If two tags sharing the same card are added to the deck, deleting one deck must not delete the card from the
     * deck's HashSet
     *
     * @throws InkaException
     */
    @Test
    public void logic_deleteSharedCard() throws InkaException{
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        CardUUID cardUUID = cardList.get(0).getUuid();

        // Create tag
        parseAndExecute("card tag -t testTag1 -i 1", AddCardToTagCommand.class);
        parseAndExecute("card tag -t testTag2 -i 1", AddCardToTagCommand.class);


        // Add Tags to the testDeck
        parseAndExecute("tag deck -t testTag1 -d testDeck", AddTagToDeckCommand.class);
        parseAndExecute("tag deck -t testTag2 -d testDeck", AddTagToDeckCommand.class);
        // Check if only one instance is stored in the HashSet
        assert deckList.get(0).getCardsSet().size() == 1;


        // remove testTag1
        parseAndExecute("deck delete -d testDeck -t testTag1", RemoveTagFromDeckCommand.class);

        // Card should remain in Deck
        assert deckList.get(0).getCardsSet().size() == 1;
    }



    @Test
    public void logic_unTagCardWhileTagInDeck() throws InkaException{
        // create a card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        CardUUID cardUUID = cardList.get(0).getUuid();

        // Add card to the tag
        parseAndExecute("card tag -t testTag1 -i 1", AddCardToTagCommand.class);

        // Add tag to the deck
        parseAndExecute("tag deck -t testTag1 -d testDeck", AddTagToDeckCommand.class);
        assert deckList.get(0).getCardsSet().size()==1;

        // Remove the card from the tag
        parseAndExecute("card untag -t testTag1 -i 1", RemoveTagFromCardCommand.class);
        assert tagList.get(0).getCardsUUID().size()==0;
        assert deckList.get(0).getCardsSet().size()==0;

    }

    @Test
    public void logic_tagCardWhileTagInDeck() throws InkaException {
        // create a card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        parseAndExecute("card add -q test2 -a test2", AddCardCommand.class);
        CardUUID cardUUID = cardList.get(0).getUuid();
        CardUUID cardUUID1 = cardList.get(1).getUuid();

        // Add card to the tag
        parseAndExecute("card tag -t testTag1 -i 1", AddCardToTagCommand.class);

        // Add Tag to the deck
        parseAndExecute("tag deck -t testTag1 -d testDeck", AddTagToDeckCommand.class);
        assert deckList.get(0).getCardsSet().size()==1;

        // Add another card to the tag
        parseAndExecute("card tag -t testTag1 -i 2", AddCardToTagCommand.class);

        // Check if the HashSet has 2 cards now
        assert deckList.get(0).getCardsSet().size()==2;
    }

    @Test
    public void logic_deleteMultipleTags() throws InkaException {
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        parseAndExecute("card add -q test2 -a test2", AddCardCommand.class);
        parseAndExecute("card add -q test3 -a test3", AddCardCommand.class);
        CardUUID cardUUID1 = cardList.get(0).getUuid();
        CardUUID cardUUID2 = cardList.get(1).getUuid();
        CardUUID cardUUID3 = cardList.get(2).getUuid();
        assert cardList.size()==3;

        parseAndExecute("card tag -t testTag1 -i 1", AddCardToTagCommand.class);
        parseAndExecute("card tag -t testTag1 -i 2", AddCardToTagCommand.class);
        parseAndExecute("card tag -t testTag2 -i 2", AddCardToTagCommand.class);
        parseAndExecute("card tag -t testTag2 -i 3", AddCardToTagCommand.class);
        parseAndExecute("card tag -t testTag3 -i 3", AddCardToTagCommand.class);
        parseAndExecute("card tag -t testTag3 -i 1", AddCardToTagCommand.class);
        assert tagList.get(0).getCardsUUID().size()==2;
        assert tagList.get(1).getCardsUUID().size()==2;
        assert tagList.get(2).getCardsUUID().size()==2;
        assert tagList.size()==3;

        parseAndExecute("tag deck -t testTag1 -d testDeck1", AddTagToDeckCommand.class);
        parseAndExecute("tag deck -t testTag2 -d testDeck1", AddTagToDeckCommand.class);
        parseAndExecute("tag deck -t testTag2 -d testDeck2", AddTagToDeckCommand.class);
        parseAndExecute("tag deck -t testTag3 -d testDeck2", AddTagToDeckCommand.class);
        parseAndExecute("tag deck -t testTag1 -d testDeck3", AddTagToDeckCommand.class);
        parseAndExecute("tag deck -t testTag3 -d testDeck3", AddTagToDeckCommand.class);
        assert deckList.get(0).getTagsUUID().size()==2;
        assert deckList.get(1).getTagsUUID().size()==2;
        assert deckList.get(2).getTagsUUID().size()==2;
        assert deckList.get(0).getCardsSet().size()==3;
        assert deckList.get(1).getCardsSet().size()==3;
        assert deckList.get(2).getCardsSet().size()==3;
        assert deckList.size()==3;

        //parseAndExecute("deck delete -d testDeck1 -c " + cardUUID1, RemoveCardFromDeckCommand.class);
    }

    @Test
    public void logic_tagExistingTag() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);
        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);

        String tagInCardStr = "Card is already a part of the Tag";

        // Create tag with long name
        try {
            parseAndExecute(
                    "card tag -t testTag -i 1",
                    AddCardToTagCommand.class);
        } catch (CardInTagException e) {
            assertEquals(e.getUiMessage(), tagInCardStr);
        }
    }

    @Test
    public void logic_tagMissingCard() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        String missingCardStr = "Card cannot be found";

        //tag non-existent card
        try {
            parseAndExecute(
                    "card tag -t testTag -i 100",
                    AddCardToTagCommand.class);
        } catch (CardNotFoundException e) {
            assertEquals(e.getUiMessage(), missingCardStr);
        }
    }

    @Test
    public void logic_untagMissingCard() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        String missingCardStr = "Card cannot be found";

        //Untag a non-existent card
        try {
            parseAndExecute(
                    "card untag -t testTag -i 100",
                    RemoveTagFromCardCommand.class);
        } catch (CardNotFoundException e) {
            assertEquals(e.getUiMessage(), missingCardStr);
        }
    }

    @Test
    public void logic_untagLongName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        String longTagNameStr = "Tag name specified is too long.";

        // Create tag with long name
        try {
            parseAndExecute(
                    "card untag -t "
                            +
                            "jghsjhgshdkhsdjkghsdjksghsjghsjzkghjsdghjdszjsdzhgjskdghghjsdhgsdjhgjsddsjkghsdjs -i 1",
                    RemoveTagFromCardCommand.class);
        } catch (LongTagNameException e) {
            assertEquals(e.getUiMessage(), longTagNameStr);
        }
    }

    @Test
    public void logic_tagLongName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        String longTagNameStr = "Tag name specified is too long.";

        // Create tag with long name
        try {
            parseAndExecute(
                    "card tag -t jghsjhgshdkhsdjkghsdjksghsjghsjzkghjsdghjdszjsdzhgjskdghghjsdhgsdjhgjsd -i 1",
                    AddCardToTagCommand.class);
        } catch (LongTagNameException e) {
            assertEquals(e.getUiMessage(), longTagNameStr);
        }
    }

    @Test
    public void logic_editTagLongName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);

        String longTagNameStr = "Tag name specified is too long.";

        // Create tag with long name
        try {
            parseAndExecute(
                    "tag edit -o ahfahfgafgasehyfgeduhsgfuyhghfusdghfhgfsdhsjhgfhsgfhjsgfsjhfghsfsgfsfs -n "
                            + "fgeshjfgsydfgsduyysfgydfgsyufgsyufgsyufgsdyufgsyufgsyuhfgsyufsgfsgyfsgfys",
                    EditTagNameCommand.class);
        } catch (LongTagNameException e) {
            assertEquals(e.getUiMessage(), longTagNameStr);
        }
    }

    @Test
    public void logic_deleteTagLongName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);

        String longTagNameStr = "Tag name specified is too long.";

        // Create tag with long name
        try {
            parseAndExecute(
                    "tag delete -t "
                            +
                            "gsjhgskghsjkiughkghsighszghzsghzghsgiuhohzsdiughzoughidogjhzogizsjghzioghjzsdioggjsiokgj",
                    DeleteTagCommand.class);
        } catch (LongTagNameException e) {
            assertEquals(e.getUiMessage(), longTagNameStr);
        }
    }

    @Test
    public void logic_listCardsUnderTagLongName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);

        String longTagNameStr = "Tag name specified is too long.";

        // Create tag with long name
        try {
            parseAndExecute(
                    "tag list -t "
                            +
                            "gsjhgskghsjkiughkghsighszghzsghzghsgiuhohzsdiughzoughidogjhzogizsjghzioghjzsdioggjsiokgj",
                    ListCardsInTagCommand.class);
        } catch (LongTagNameException e) {
            assertEquals(e.getUiMessage(), longTagNameStr);
        }
    }

    @Test
    public void logic_addDeckLongDeckName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        String longDeckNameStr = "Deck name specified is too long.";

        // Create tag with long name
        try {
            // Create tag
            parseAndExecute("card deck -d testDecksetsetsettsetststsststtstststststststststtstststshrhhs -i 1",
                    AddCardToDeckCommand.class);
        } catch (LongDeckNameException e) {
            assertEquals(e.getUiMessage(), longDeckNameStr);
        }
    }

    @Test
    public void logic_deleteDeckLongDeckName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        String longDeckNameStr = "Deck name specified is too long.";

        try {
            //delete deck with long name
            parseAndExecute("deck delete -d gjshguhsuigshuighzsduighzuighyzuighzughzughzughzughzughzugzhgugzug",
                    DeleteDeckCommand.class);
        } catch (LongDeckNameException e) {
            assertEquals(e.getUiMessage(), longDeckNameStr);
        }
    }

    @Test
    public void logic_editDeckLongName() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        // Create tag
        parseAndExecute("card deck -d deckTest -i 1", AddCardToDeckCommand.class);

        String longDeckNameStr = "Deck name specified is too long.";

        // Edit deck with long name
        try {
            parseAndExecute(
                    "deck edit -o ahfahfgafgasehyfgeduhsgfuyhghfusdghfhgfsdhsjhgfhsgfhjsgfsjhfghsfsgfsfs -n "
                            + "fgeshjfgsydfgsduyysfgydfgsyufgsyufgsyufgsdyufgsyufgsyuhfgsyufsgfsgyfsgfys",
                    EditDeckNameCommand.class);
        } catch (LongDeckNameException e) {
            assertEquals(e.getUiMessage(), longDeckNameStr);
        }
    }

    @Test
    public void logic_tagAndUntag() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);
        assert cardList.get(0).getTagsUUID().size() == 1;
        assert tagList.get(0).getCardsUUID().size() == 1;

        // Untag
        parseAndExecute("card untag -t testTag -i 1", RemoveTagFromCardCommand.class);
        assert cardList.get(0).getTagsUUID().size() == 0;
        assert tagList.get(0).getCardsUUID().size() == 0;
    }

    @Test
    public void logic_renameAndDeleteTag() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        // Create tag
        parseAndExecute("card tag -t testTag -i 1", AddCardToTagCommand.class);
        assert tagList.get(0).getTagName().equals("testTag");

        // Rename tag
        parseAndExecute("tag edit -o testTag -n newName", EditTagNameCommand.class);
        assert tagList.get(0).getTagName().equals("newName");

        // Delete tag
        parseAndExecute("tag delete -t newName", DeleteTagCommand.class);
        assert tagList.isEmpty();
    }

    @Test
    public void logic_renameAndDeleteDeck() throws InkaException {
        // Create card
        parseAndExecute("card add -q test1 -a test1", AddCardCommand.class);

        // Create deck
        parseAndExecute("card deck -d testDeck -i 1", AddCardToDeckCommand.class);
        assert deckList.get(0).getDeckName().equals("testDeck");

        // Rename deck
        parseAndExecute("deck edit -o testDeck -n newName", EditDeckNameCommand.class);
        assert deckList.get(0).getDeckName().equals("newName");

        // Delete deck
        parseAndExecute("deck delete -d newName", DeleteDeckCommand.class);
        assert deckList.isEmpty();
    }
}
