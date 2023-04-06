package utils.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.Card;
import model.CardList;
import model.DeckList;
import model.TagList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.AddCardCommand;
import utils.command.AddCardToTagCommand;
import utils.command.Command;
import utils.command.DeleteCardCommand;
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
    public void parse_card_deleteBadIndex() throws InkaException {
        cardList.addCard(new Card("QUESTION", "ANSWER"));

        Command cmd = parser.parseCommand("card delete -i 0");
        assertThrows(CardNotFoundException.class, () -> cmd.execute(cardList, tagList, deckList, ui, storage),
                "Should fail to delete nothing");
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
    }

    @Test
    public void parse_card_tagLongFlag() throws InkaException {
        cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));
        Command cmd = parser.parseCommand("card tag -c 00000000-0000-0000-0000-000000000000 --tag tagName");
        assert cmd instanceof AddCardToTagCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);
        assert tagList.findTagFromName("tagName") != null;
    }

//    @Test
//    public void parse_card_tagWhitespaceName() throws InkaException {
//        cardList.addCard(Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000"));
//        Command cmd = parser.parseCommand("card tag -c 00000000-0000-0000-0000-000000000000 -t tag name");
//        assert cmd instanceof AddCardToTagCommand;
//        cmd.execute(cardList, tagList, deckList, ui, storage);
//        assert tagList.findTagFromName("tag-name") != null;
//    }

    //endregion

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
