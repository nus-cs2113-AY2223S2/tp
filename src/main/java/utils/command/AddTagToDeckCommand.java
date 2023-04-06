package utils.command;

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

        if (tagToAdd == null) {
            // exception here
            throw new CreateTagBeforeAddingToDeck();
        }
        //assert tagToAdd != null; // for the time being, can add newer windowed features later

        Deck deckToAdd = deckList.findDeckFromName(deckName);
        if (deckToAdd == null) {
            ui.printDeckCreationSuccess();
            deckToAdd = new Deck(deckName, tagToAdd.getUUID());
            deckList.addDeck(deckToAdd);
        } else if (deckToAdd.tagIsInDeck(tagToAdd.getUUID())) {
            throw new TagInDeckException();
        } else {
            deckToAdd.addTag(tagToAdd.getUUID());
        }
        deckUUID = deckToAdd.getDeckUUID();
        tagToAdd.addDeck(deckUUID);

        //TODO: Set up a relation between a Tag and Deck attached to, if any
        //add the tag uuid to the card
        //leave for the time being, no functionality at this case
        //        deckUUID = deckToAdd.getDeckUUID();
        //        cardToAdd.addDeck(deckUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        addTagToDeck(deckList, tagList, ui);

        Tag tagToBeAdded = tagList.findTag(tagSelector);
        ui.printAddTagToDeckSuccess(tagToBeAdded.getUUID(), deckUUID);
    }
}
