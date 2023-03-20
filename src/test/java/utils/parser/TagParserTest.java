package utils.parser;

import java.util.UUID;
import model.CardList;
import model.CardUUID;
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
        CardUUID cardUUID = new CardUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        tagList.addTag(new Tag("tagName", cardUUID));

        Command cmd = parser.parseCommand("tag delete -t tagName");
        assert cmd instanceof DeleteTagCommand;
        cmd.execute(cardList, tagList, ui, storage);
        assert tagList.isEmpty();
    }
}
