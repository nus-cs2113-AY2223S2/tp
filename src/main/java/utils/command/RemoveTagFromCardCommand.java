
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
import utils.exceptions.InkaException;
import utils.exceptions.LongTagNameException;
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
    private void removeTagFromCard(Card cardAffected, Tag tagToDelete)
            throws TagNotInCardException, LongTagNameException {

        if (tagToDelete.getTagName().length() > 50) {
            throw new LongTagNameException();
        }

        if (!tagToDelete.cardIsInTag(cardAffected.getUuid())) {
            throw new TagNotInCardException();
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
        removeTagFromCard(cardAffected, tagToDelete);

        ui.printRemoveTagFromCard(cardAffected.getUuid(), tagToDelete.getUUID());
    }
}

