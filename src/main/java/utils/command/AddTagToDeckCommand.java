package utils.command;

import java.util.Optional;
import model.CardList;
import model.Deck;
import model.DeckList;
import model.DeckUUID;
import model.Tag;
import model.TagList;
import model.TagSelector;
import utils.UserInterface;
import utils.exceptions.CreateTagBeforeAddingToDeck;
import utils.exceptions.InkaException;
import utils.exceptions.LongDeckNameException;
import utils.exceptions.LongTagNameException;
import utils.exceptions.TagInDeckException;
import utils.exceptions.UUIDWrongFormatException;
import utils.storage.IDataStorage;

public class AddTagToDeckCommand extends Command {
    private String deckName;
    private DeckUUID deckUUID;
    private TagSelector tagSelector;

    public AddTagToDeckCommand(String deckName, TagSelector tagSelector) throws InkaException {
        this.deckName = deckName;
        try {
            this.tagSelector = tagSelector;
        } catch (IllegalArgumentException e) {
            throw new UUIDWrongFormatException();
        }
    }

    private void addTagToDeck(DeckList deckList, TagList tagList, UserInterface ui) throws InkaException {

        Tag tagToAdd = tagList.findTag(tagSelector);
        Optional<String> tagName = tagSelector.getTagName();

        if (tagName.isPresent() && tagName.get().length() > 50) {
            throw new LongTagNameException();
        } else if (tagToAdd == null) {
            // exception here
            throw new CreateTagBeforeAddingToDeck();
        }

        Deck deckToAdd = deckList.findDeckFromName(deckName);
        if (deckName.length() > 50) {
            throw new LongDeckNameException();
        } else if (deckToAdd == null) {
            ui.printDeckCreationSuccess();
            deckToAdd = new Deck(deckName, tagToAdd.getUUID());
            deckList.addDeck(deckToAdd);
        } else if (deckToAdd.tagIsInDeck(tagToAdd.getUUID())) {
            throw new TagInDeckException();
        } else {
            deckToAdd.addTag(tagToAdd.getUUID());
        }
        deckToAdd.addCardstoMap(tagToAdd.getUUID(), tagList); // add tagged cards to set
        deckToAdd.addCardsToSet(tagToAdd.getUUID(), tagList); // add tagged cards to map
        deckUUID = deckToAdd.getDeckUUID();
        tagToAdd.addDeck(deckUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        addTagToDeck(deckList, tagList, ui);

        Tag tagToBeAdded = tagList.findTag(tagSelector);
        ui.printAddTagToDeckSuccess(tagToBeAdded.getTagName(), deckUUID);
    }
}
