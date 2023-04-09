package utils.command;

import java.util.Optional;
import model.Card;
import model.CardList;
import model.CardSelector;
import model.CardUUID;
import model.DeckList;
import model.Tag;
import model.TagList;
import model.TagSelector;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.CardInTagException;
import utils.exceptions.InkaException;
import utils.exceptions.LongTagNameException;
import utils.exceptions.TagNotFoundException;
import utils.exceptions.UUIDWrongFormatException;
import utils.storage.IDataStorage;

public class AddCardToTagCommand extends Command {
    private TagSelector tagSelector;
    private CardSelector cardSelector;

    public AddCardToTagCommand(TagSelector tagSelector, CardSelector cardSelector) throws InkaException {
        try {
            this.tagSelector = tagSelector;
            this.cardSelector = cardSelector;
        } catch (IllegalArgumentException e) {
            throw new UUIDWrongFormatException();
        }
    }

    /**
     * Adds the tagName tag to the corresponding card, creates the tag beforehand if it currently does not exist.
     *
     * @param tagList   The tagList into which to add the tag that has been created
     * @param cardToAdd The card to add to tag
     * @param ui        The userInterface to print the success of the tag creation
     */
    private void addCardToTag(TagList tagList, Card cardToAdd, UserInterface ui)
            throws CardInTagException, TagNotFoundException, LongTagNameException {
        //find the corresponding Tag and Card based on its tagName and card uuid
        Tag tagToAdd = tagList.findTag(tagSelector);
        Optional<String> tagName = tagSelector.getTagName();

        if (tagName.isPresent() && tagName.get().length() > 50) {
            throw new LongTagNameException();
        } else if (tagToAdd == null) {
            ui.printTagCreationSuccess(tagName.get());
            tagToAdd = new Tag(tagName.get());
            tagList.addTag(tagToAdd);
        } else if (tagToAdd.cardIsInTag(cardToAdd.getUuid())) {
            throw new CardInTagException();
        }

        CardUUID cardUUID = cardToAdd.getUuid();
        tagToAdd.addCard(cardUUID);

        //add the tag uuid to the card
        TagUUID tagUUID = tagToAdd.getUUID();

        cardToAdd.addTag(tagUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Card cardToAdd = cardList.findCard(cardSelector);
        assert cardToAdd != null;

        addCardToTag(tagList, cardToAdd, ui);

        Tag tagToBeAdded = tagList.findTag(tagSelector);
        ui.printAddTagToCardSuccess(cardToAdd.getUuid(), tagToBeAdded.getUUID());
    }
}
