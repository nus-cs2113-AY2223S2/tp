package utils.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.TagList;
import model.TagUUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.AddCardCommand;
import utils.command.AddCardToDeckCommand;
import utils.command.AddCardToTagCommand;
import utils.command.Command;
import utils.command.DeleteCardCommand;
import utils.command.ListCardCommand;
import utils.command.PrintHelpCommand;
import utils.command.ViewCardCommand;
import utils.exceptions.CardNotFoundException;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.storage.FakeStorage;
import utils.storage.Storage;

public class CardParserTest {
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
    public void parse_cardSelector() {
        String[] testCases = {"card delete -i test", "card delete -c", "card delete"};
        for (String testCase : testCases) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testCase), "Invalid syntax");
        }
    }

    //region `card add` tests
    @Test
    public void parse_card_add() throws InkaException {
        Command cmd = parser.parseCommand("card add -q QUESTION -a ANSWER");
        assert cmd instanceof AddCardCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);

        Card card = cardList.get(0);
        assert card.getQuestion().equals("QUESTION") : "Unexpected question parsed";
        assert card.getAnswer().equals("ANSWER") : "Unexpected answer parsed";
    }

    @Test
    public void parse_card_addLongFlag() throws InkaException {
        Command cmd = parser.parseCommand("card add --question QUESTION --answer ANSWER");
        assert cmd instanceof AddCardCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);

        Card card = cardList.get(0);
        assert card.getQuestion().equals("QUESTION") : "Unexpected question parsed";
        assert card.getAnswer().equals("ANSWER") : "Unexpected answer parsed";
    }

    @Test
    public void parse_card_addWithWhitespaces() throws InkaException {
        Command cmd = parser.parseCommand("card add -q MULTI WORD QUESTION -a MULTI WORD ANSWER");
        assert cmd instanceof AddCardCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);

        Card card = cardList.get(0);
        assert card.getQuestion().equals("MULTI WORD QUESTION") : "Unexpected question parsed";
        assert card.getAnswer().equals("MULTI WORD ANSWER") : "Unexpected answer parsed";
    }

    @Test
    public void parse_card_addMissingOptions() {
        String[] testInputs = {"card add", "card add -q", "card add -a"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid syntax");
        }
    }

    @Test
    public void parse_card_addMissingArguments() {
        String[] testInputs = {"card add -a ANSWER -q", "card add -q QUESTION -a"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid syntax");
        }
    }
    //endregion

    //region `card deck` tests

    @Test
    public void parse_card_deck() throws InkaException {

        String[] testCases = {"card deck -c 00000000-0000-0000-0000-000000000000 -d testDeck",
            "card deck -i 1 -d testDeck"};
        for (String testCase : testCases) {
            init();
            Card testCard = Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000");
            Deck testDeck = new Deck("testDeck");
            cardList.addCard(testCard);
            deckList.addDeck(testDeck);

            assert testDeck.getCardsSet().size() == 0;

            Command cmd = parser.parseCommand(testCase);
            assert cmd instanceof AddCardToDeckCommand;
            cmd.execute(cardList, tagList, deckList, ui, storage);

            assert testDeck.cardIsInDeck(new CardUUID(UUID.fromString("00000000-0000-0000-0000-000000000000")));
        }
    }

    @Test
    public void parse_card_deckInvalidSyntax() {
        String[] testCases = {"card deck -i 1", "card deck -c 00000000-0000-0000-0000-000000000000",
            "card deck -d testDeck"};
        for (String testCase : testCases) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testCase), "Invalid syntax");
        }
    }

    //endregion

    //region `card delete` tests

    @Test
    public void parse_card_delete() throws InkaException {
        String[] testInputs = {"card delete -i 1", "card delete -c 00000000-0000-0000-0000-000000000000"};
        for (String testInput : testInputs) {
            cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));

            Command cmd = parser.parseCommand(testInput);
            assert cmd instanceof DeleteCardCommand;
            cmd.execute(cardList, tagList, deckList, ui, storage);

            assert cardList.isEmpty() : "Should have deleted Card";
        }
    }

    @Test
    public void parse_card_deleteLongFlag() throws InkaException {
        String[] testInputs = {"card delete --index 1", "card delete --card 00000000-0000-0000-0000-000000000000"};
        for (String testInput : testInputs) {
            cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));

            Command cmd = parser.parseCommand(testInput);
            assert cmd instanceof DeleteCardCommand;
            cmd.execute(cardList, tagList, deckList, ui, storage);

            assert cardList.isEmpty() : "Should have deleted Card";
        }
    }

    @Test
    public void parse_card_deleteEmpty() throws InkaException {
        Command cmd = parser.parseCommand("card delete -i 1");
        assertThrows(CardNotFoundException.class, () -> cmd.execute(cardList, tagList, deckList, ui, storage),
                "Should fail to delete nothing");
    }

    @Test
    public void parse_card_deleteInvalidIndex() {
        String[] testInputs = {"card delete -i -1", "card delete -i 0", "card delete -i 0.1", "card delete -i -0.1",
            "card delete -i STRING"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid index");
        }
    }

    @Test
    public void parse_card_deleteMissingOptions() {
        String[] testInputs = {"card delete"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid syntax");
        }
    }

    @Test
    public void parse_card_deleteMissingArguments() {
        String[] testInputs = {"card delete -i"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid syntax");
        }
    }

    //endregion

    //region `card tag` tests

    @Test
    public void parse_card_tag() throws InkaException {
        cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));
        Command cmd = parser.parseCommand("card tag -c 00000000-0000-0000-0000-000000000000 -t tagName");
        assert cmd instanceof AddCardToTagCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);

        assert tagList.findTagFromName("tagName") != null;
        TagUUID tagUUID = cardList.getCards().get(0).getTagsUUID().get(0);
        assert tagList.get(0).getUUID().equals(tagUUID);
    }

    @Test
    public void parse_card_tagLongFlag() throws InkaException {
        cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));
        Command cmd = parser.parseCommand("card tag -c 00000000-0000-0000-0000-000000000000 --tag tagName");
        assert cmd instanceof AddCardToTagCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);

        assert tagList.findTagFromName("tagName") != null;
        TagUUID tagUUID = cardList.getCards().get(0).getTagsUUID().get(0);
        assert tagList.get(0).getUUID().equals(tagUUID);
    }

    @Test
    public void parse_card_tagWhitespaceName() {
        InkaException ex = assertThrows(InvalidSyntaxException.class,
                () -> parser.parseCommand("card tag -c 00000000-0000-0000-0000-000000000000 -t tag name"));
        assertEquals(ex.getUiMessage(), InvalidSyntaxException.buildTooManyTokensMessage().getUiMessage());
    }

    //endregion

    @Test
    public void parse_card_list() throws InkaException {
        Command cmd = parser.parseCommand("card list");
        assert cmd instanceof ListCardCommand;
    }

    @Test
    public void parse_card_listExtraTokens() {
        assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand("card list test"),
                "Does not expect trailing tokens");
    }

    @Test
    public void parse_card_help() throws InkaException {
        Command cmd = parser.parseCommand("card help");
        assert cmd instanceof PrintHelpCommand;
    }

    @Test
    public void parse_card_helpExtraTokens() {
        assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand("card help test"),
                "Does not expect trailing tokens");
    }

    //region `card view` tests

    @Test
    public void parse_card_view() throws InkaException {
        String[] testInputs = {"card view -i 1", "card view -c 00000000-0000-0000-0000-000000000000"};
        for (String testInput : testInputs) {
            cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));

            Command cmd = parser.parseCommand(testInput);
            assert cmd instanceof ViewCardCommand;
            cmd.execute(cardList, tagList, deckList, ui, storage);
        }
    }

    @Test
    public void parse_card_viewLongFlag() throws InkaException {
        String[] testInputs = {"card view --index 1", "card view --card 00000000-0000-0000-0000-000000000000"};
        for (String testInput : testInputs) {
            cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));

            Command cmd = parser.parseCommand(testInput);
            assert cmd instanceof ViewCardCommand;
            cmd.execute(cardList, tagList, deckList, ui, storage);
        }
    }

    @Test
    public void parse_card_viewMissingOptions() {
        String[] testInputs = {"card view"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid syntax");
        }
    }

    @Test
    public void parse_card_viewMissingArguments() {
        String[] testInputs = {"card view -i"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid syntax");
        }
    }

    //endregion
}
