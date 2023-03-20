package utils.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.Card;
import model.CardList;
import model.Tag;
import model.TagList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserInterface;
import utils.command.Command;
import utils.command.DeleteTagCommand;
import utils.command.ListCardsUnderTagCommand;
import utils.command.ListTagsCommand;
import utils.exceptions.InkaException;
import utils.exceptions.TagNotFoundException;
import utils.storage.FakeStorage;
import utils.storage.Storage;

public class TagParserTest {
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

    @Test
    public void parse_tag_list() throws InkaException {
        Command cmd = parser.parseCommand("tag list");
        assert cmd instanceof ListTagsCommand;
    }

    @Test
    public void parse_tag_listWithTag() throws InkaException {
        Command cmd = parser.parseCommand("tag list -t tagName");
        assert cmd instanceof ListCardsUnderTagCommand;
    }

    @Test
    public void parse_tag_listWithTagLongFlag() throws InkaException {
        Command cmd = parser.parseCommand("tag list --tag tagName");
        assert cmd instanceof ListCardsUnderTagCommand;
    }

    @Test
    public void parse_tag_delete() throws InkaException {
        Card card = Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000");
        cardList.addCard(card);
        tagList.addTag(new Tag("tagName", card.getUuid()));

        Command cmd = parser.parseCommand("tag delete -t tagName");
        assert cmd instanceof DeleteTagCommand;
        cmd.execute(cardList, tagList, ui, storage);
        assert tagList.isEmpty();
    }

    @Test
    public void parse_tag_deleteUnknownTag() throws InkaException {
        Command cmd = parser.parseCommand("tag delete -t tagName");
        assert cmd instanceof DeleteTagCommand;
        assertThrows(TagNotFoundException.class, () -> cmd.execute(cardList, tagList, ui, storage),
                "Should not delete non-existent tag");
    }
}
