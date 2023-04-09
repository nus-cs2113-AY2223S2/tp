package utils.command;

import java.util.ArrayList;
import model.CardList;
import model.Deck;
import model.DeckList;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.DeckNotFoundException;
import utils.exceptions.InkaException;
import utils.exceptions.LongDeckNameException;
import utils.storage.IDataStorage;

public class ListTagsUnderDeckCommand extends Command {
    private String deckName;

    public ListTagsUnderDeckCommand(String deckName) {
        this.deckName = deckName;
    }

    private TagList findTagsUnderDeck(TagList tagList, DeckList deckList) throws InkaException {
        Deck foundDeck = deckList.findDeckFromName(deckName);

        if (foundDeck == null) {
            throw new DeckNotFoundException();
        }
        ArrayList<TagUUID> tagUUIDS = foundDeck.getTagsUUID();
        TagList foundTagList = new TagList();

        for (Tag tag : tagList.getTags()) {
            for (TagUUID tagUUID : tagUUIDS) {
                if (tagUUID.equals(tag.getUUID())) {
                    foundTagList.addTag(tag);
                }
            }
        }
        return foundTagList;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        TagList foundTagList = findTagsUnderDeck(tagList, deckList);
        ui.printTagList(foundTagList);
    }
}
