
package utils.command;

import java.util.ArrayList;
import java.util.Optional;
import model.Card;
import model.CardList;
import model.CardSelector;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.DeckUUID;
import model.Tag;
import model.TagList;
import model.TagSelector;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.LongTagNameException;
import utils.exceptions.TagNotFoundException;
import utils.exceptions.TagNotInCardException;
import utils.exceptions.UUIDWrongFormatException;
import utils.storage.IDataStorage;

public class RemoveTagFromCardCommand extends Command {
    private TagSelector tagSelector;
    private CardSelector cardSelector;

    public RemoveTagFromCardCommand(TagSelector tagSelector, CardSelector cardSelector) throws InkaException {
        try {
            this.tagSelector = tagSelector;
            this.cardSelector = cardSelector;
        } catch (IllegalArgumentException e) {
            throw new UUIDWrongFormatException();
        }
    }

    /**
     * If the tag is currently in the card, removes the tag from the card and removes the card from the tag.
     *
     * @param cardAffected The user-specified card that might contain the tag
     * @param tagToDelete  The target tag that is to be deleted from the Card
     */
    private void removeTagFromCard(Card cardAffected, Tag tagToDelete, DeckList deckList)
            throws InkaException {

        Optional<String> tagName = tagSelector.getTagName();

        if (tagName.isPresent() && tagName.get().length() > 50) {
            throw new LongTagNameException();
        } else if (tagToDelete == null) {
            throw new TagNotFoundException();
        } else if (!tagToDelete.cardIsInTag(cardAffected.getUuid())) {
            throw new TagNotInCardException();
        }

        if(!tagToDelete.isDeckEmpty()) {
            ArrayList<DeckUUID> deckUUIDArrayList = tagToDelete.getDecks();
            for(DeckUUID deckUUID: deckUUIDArrayList) {
                Deck deck = deckList.findDeckFromUUID(deckUUID);
                deck.removeCardFromMap(cardAffected.getUuid());
            }
        }
        CardUUID cardUUID = cardAffected.getUuid();
        tagToDelete.removeCard(cardUUID);

        TagUUID tagUUID = tagToDelete.getUUID();
        cardAffected.removeTag(tagUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Card cardAffected = cardList.findCard(cardSelector);
        assert cardAffected != null;

        Tag tagToDelete = tagList.findTag(tagSelector);
        removeTagFromCard(cardAffected, tagToDelete, deckList);

        ui.printRemoveTagFromCard(cardAffected.getUuid(), tagToDelete.getTagName());
    }
}

