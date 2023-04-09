package utils.command;

import java.util.ArrayList;
import java.util.Optional;
import model.CardList;
import model.Deck;
import model.DeckList;
import model.Tag;
import model.TagList;
import model.TagSelector;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.DeckNotFoundException;
import utils.exceptions.InkaException;
import utils.exceptions.LongDeckNameException;
import utils.exceptions.LongTagNameException;
import utils.exceptions.TagNeverWasInDeck;
import utils.exceptions.TagNotFoundException;
import utils.exceptions.UUIDWrongFormatException;
import utils.storage.IDataStorage;

public class RemoveTagFromDeckCommand extends Command {
    private TagSelector tagSelector;
    private String deckName;

    public RemoveTagFromDeckCommand(TagSelector tagSelector, String deckName) throws InkaException {
        this.deckName = deckName;
        try {
            this.tagSelector = tagSelector;
        } catch (IllegalArgumentException e) {
            throw new UUIDWrongFormatException();
        }
    }

    public void removeTagFromDeck(DeckList deckList, TagList tagList, String deckName, TagSelector tagSelector)
            throws InkaException {

        Tag tagToBeDeleted = tagList.findTag(tagSelector);
        Optional<String> tagName = tagSelector.getTagName();

        if (tagName.isPresent() && tagName.get().length() > 50) {
            throw new LongTagNameException();
        } else if (tagToBeDeleted == null) {
            throw new TagNotFoundException();
        }

        Deck deck = deckList.findDeckFromName(deckName);

        if (deckName.length() > 50) {
            throw new LongDeckNameException();
        } else if (deck == null) {
            throw new DeckNotFoundException();
        }

        ArrayList<TagUUID> deckTagList = deck.getTagsUUID();
        boolean wasTagInDeck = deckTagList.removeIf(tag -> tag.equals(tagToBeDeleted.getUUID()));
        if (!wasTagInDeck) {
            throw new TagNeverWasInDeck();
        }
        deck.removeTaggedCardsMap(tagToBeDeleted.getUUID(), tagList);
        deck.setTags(deckTagList);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Tag tagToBeAdded = tagList.findTag(tagSelector);
        assert tagToBeAdded != null;

        removeTagFromDeck(deckList, tagList, deckName, tagSelector);
        ui.printRemoveTagFromDeckSuccess(tagToBeAdded.getTagName(), deckName);
    }
}
