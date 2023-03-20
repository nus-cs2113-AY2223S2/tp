package utils.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.Card;
import model.CardList;
import model.TagList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.Command;
import utils.exceptions.DeleteUnknown;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.storage.FakeStorage;
import utils.storage.Storage;

public class ParserTest {

    private CardList cardList;
    private TagList tagList;
    private UserInterface ui;
    private Storage storage;

    private Parser parser;

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
    }

    //region `card add` tests
    @Test
    public void parse_card_add() throws InkaException {
        Command cmd = parser.parseCommand("card add -q QUESTION -a ANSWER");
        cmd.execute(cardList, tagList, ui, storage);

        Card card = cardList.get(0);
        assert card.getQuestion().equals("QUESTION") : "Unexpected question parsed";
        assert card.getAnswer().equals("ANSWER") : "Unexpected answer parsed";
    }

    @Test
    public void parse_card_addLongFlag() throws InkaException {
        Command cmd = parser.parseCommand("card add --question QUESTION --answer ANSWER");
        cmd.execute(cardList, tagList, ui, storage);

        Card card = cardList.get(0);
        assert card.getQuestion().equals("QUESTION") : "Unexpected question parsed";
        assert card.getAnswer().equals("ANSWER") : "Unexpected answer parsed";
    }

    @Test
    public void parse_card_addWithWhitespaces() throws InkaException {
        Command cmd = parser.parseCommand("card add -q MULTI WORD QUESTION -a MULTI WORD ANSWER");
        cmd.execute(cardList, tagList, ui, storage);

        Card card = cardList.get(0);
        assert card.getQuestion().equals("MULTI WORD QUESTION") : "Unexpected question parsed";
        assert card.getAnswer().equals("MULTI WORD ANSWER") : "Unexpected answer parsed";
    }

    @Test
    public void parse_card_addMissingFlags() {
        String[] testInputs = {"card add", "card add -q", "card add -a"};
        for (String testInput : testInputs) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testInput),
                    "Should be invalid syntax");
        }
    }

    @Test
    public void parse_card_addMissingQuestion() {
        assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand("card add -a ANSWER -q"),
                "Should be invalid syntax");
    }

    @Test
    public void parse_card_addMissingAnswer() {
        assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand("card add -q QUESTION -a"),
                "Should be invalid syntax");
    }
    //endregion

    //region `card delete` tests

    @Test
    public void parse_card_delete() throws InkaException {
        cardList.addCard(new Card("QUESTION", "ANSWER"));

        Command cmd = parser.parseCommand("card delete -i 1");
        cmd.execute(cardList, tagList, ui, storage);

        assert cardList.isEmpty() : "Should have deleted Card";
    }

    @Test
    public void parse_card_deleteLongFlag() throws InkaException {
        cardList.addCard(new Card("QUESTION", "ANSWER"));

        Command cmd = parser.parseCommand("card delete --index 1");
        cmd.execute(cardList, tagList, ui, storage);

        assert cardList.isEmpty() : "Should have deleted Card";
    }

    @Test
    public void parse_card_deleteEmpty() throws InkaException {
        Command cmd = parser.parseCommand("card delete -i 1");
        assertThrows(DeleteUnknown.class, () -> cmd.execute(cardList, tagList, ui, storage),
                "Should fail to delete nothing");
    }

    @Test
    public void parse_card_deleteBadIndex() throws InkaException {
        cardList.addCard(new Card("QUESTION", "ANSWER"));

        Command cmd = parser.parseCommand("card delete -i 0");
        assertThrows(DeleteUnknown.class, () -> cmd.execute(cardList, tagList, ui, storage),
                "Should fail to delete nothing");
    }

    //endregion
}
