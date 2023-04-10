package utils.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.DeckList;
import model.Tag;
import model.TagList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.Command;
import utils.command.DeleteTagCommand;
import utils.command.EditTagNameCommand;
import utils.command.ListCardsInTagCommand;
import utils.command.ListTagsCommand;
import utils.command.PrintHelpCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.TagNotFoundException;
import utils.storage.FakeStorage;
import utils.storage.Storage;

public class TagParserTest {
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

    @Test
    public void parse_tagSelector() {
        String[] testCases = {"tag delete -x", "tag delete -x test", "tag delete -t"};
        for (String testCase : testCases) {
            assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand(testCase), "Invalid syntax");
        }
    }

    @Test
    public void parse_tag_list() throws InkaException {
        Command cmd = parser.parseCommand("tag list");
        assert cmd instanceof ListTagsCommand;
    }

    @Test
    public void parse_tag_listWithTag() throws InkaException {
        Command cmd = parser.parseCommand("tag list -t tagName");
        assert cmd instanceof ListCardsInTagCommand;
    }

    @Test
    public void parse_tag_listWithTagLongFlag() throws InkaException {
        Command cmd = parser.parseCommand("tag list --tag tagName");
        assert cmd instanceof ListCardsInTagCommand;
    }

    @Test
    public void parse_tag_help() throws InkaException {
        Command cmd = parser.parseCommand("tag help");
        assert cmd instanceof PrintHelpCommand;
    }

    @Test
    public void parse_tag_helpInvalidSyntax() {
        assertThrows(InvalidSyntaxException.class, () -> parser.parseCommand("tag help test"), "Extra tokens");
    }

    @Test
    public void parse_tag_delete() throws InkaException {
        Card card = Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000");
        cardList.addCard(card);
        tagList.addTag(new Tag("tagName", card.getUuid()));

        Command cmd = parser.parseCommand("tag delete -t tagName");
        assert cmd instanceof DeleteTagCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);
        assert tagList.isEmpty();
    }

    @Test
    public void parse_tag_deleteWithIndex() throws InkaException {
        Card card = Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000");
        cardList.addCard(card);
        tagList.addTag(new Tag("tagName", card.getUuid()));

        Command cmd = parser.parseCommand("tag delete -x 1");
        assert cmd instanceof DeleteTagCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);
        assert tagList.isEmpty();
    }

    @Test
    public void parse_tag_deleteWithLongIndex() throws InkaException {
        Card card = Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000");
        cardList.addCard(card);
        tagList.addTag(new Tag("tagName", card.getUuid()));

        Command cmd = parser.parseCommand("tag delete --tagindex 1");
        assert cmd instanceof DeleteTagCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);
        assert tagList.isEmpty();
    }

    @Test
    public void parse_tag_deleteUnknownTag() throws InkaException {
        Command cmd = parser.parseCommand("tag delete -t tagName");
        assert cmd instanceof DeleteTagCommand;
        assertThrows(TagNotFoundException.class, () -> cmd.execute(cardList, tagList, deckList, ui, storage),
                "Should not delete non-existent tag");
    }

    @Test
    public void parse_tag_edit() throws InkaException {
        CardUUID cardUUID = new CardUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        tagList.addTag(new Tag("oldTag", cardUUID));

        Command cmd = parser.parseCommand("tag edit -o oldTag -n newTag");
        assert cmd instanceof EditTagNameCommand;
        cmd.execute(cardList, tagList, deckList, ui, storage);
        assert tagList.findTagFromName("oldTag") == null;
        assert tagList.findTagFromName("newTag") != null;
    }

    @Test
    public void parse_tag_editUnknownTag() throws InkaException {
        Command cmd = parser.parseCommand("tag edit -o oldTag -n newTag");
        assert cmd instanceof EditTagNameCommand;
        assertThrows(TagNotFoundException.class, () -> cmd.execute(cardList, tagList, deckList, ui, storage),
                "Should not delete non-existent tag");
    }
}
