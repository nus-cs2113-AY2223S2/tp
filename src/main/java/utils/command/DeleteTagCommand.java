package utils.command;

import model.Card;
import model.CardList;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.DeckUUID;
import model.Tag;
import model.TagList;
import model.TagSelector;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.TagNotFoundException;
import utils.storage.IDataStorage;

public class DeleteTagCommand extends Command {
    private TagSelector tagSelector;

    public DeleteTagCommand(TagSelector tagSelector) {
        this.tagSelector = tagSelector;
    }

    /**
     * Remove the tagName tag from the tagList and all the cards that currently have the tagName tag.
     *
     * @param cardList The cardList from which to look for the cards with tagName tag.
     * @param tagList  The tagList from which to delete the tagName tag.
     * @param ui       The userInterface to print the success of removal of the tag from the cards.
     */
    private void removeTagFromCards(CardList cardList, TagList tagList, UserInterface ui) throws InkaException {
        Tag tagToDelete = tagList.findTag(tagSelector);
        if (tagToDelete == null) {
            throw new TagNotFoundException();
        }

        //for each card whose uuid is listed under the tag, remove the tag uuid from that card
        if (!tagToDelete.cardEmpty()) {
            for (CardUUID cardUUID : tagToDelete.getCardsUUID()) {
                Card affectedCard = cardList.findCardFromUUID(cardUUID);
                affectedCard.removeTag(tagToDelete.getUUID());
                ui.printRemoveTagFromCard(affectedCard.getUuid(), tagToDelete.getUUID());
            }
        }
    }

    private void removeTagsFromDecks(DeckList deckList, TagList tagList) throws TagNotFoundException {
        Tag tagToDelete = tagList.findTag(tagSelector);

        for (DeckUUID deckUUID : tagToDelete.getDecks()) {
            Deck deckToDeleteTagFrom = deckList.findDeckFromUUID(deckUUID);
            deckToDeleteTagFrom.getTagsUUID().remove(tagToDelete.getUUID());
        }
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Tag tagToDelete = tagList.findTag(tagSelector);
        if (tagToDelete == null) {
            throw new TagNotFoundException();
        }

        removeTagFromCards(cardList, tagList, ui);
        removeTagsFromDecks(deckList, tagList);

        tagList.delete(this.tagSelector);
        ui.printRemoveTagFromTagList(tagToDelete.getUUID());
    }
}
