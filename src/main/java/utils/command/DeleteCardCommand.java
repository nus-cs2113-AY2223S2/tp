package utils.command;

import model.Card;
import model.CardList;
import model.CardSelector;
import model.Deck;
import model.DeckList;
import model.DeckUUID;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.CardNotFoundException;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class DeleteCardCommand extends Command {

    private CardSelector cardSelector;

    public DeleteCardCommand(CardSelector cardSelector) {
        this.cardSelector = cardSelector;
    }

    //Assume this is silent removal, no UI message is displayed
    public void cardRemovalFromDecks(Card cardToDelete, DeckList deckList) {
        for (DeckUUID deckUUID : cardToDelete.getDecksUUID()) {
            Deck deckToDeleteCardFrom = deckList.findDeckFromUUID(deckUUID);
            deckToDeleteCardFrom.getCardsUUID().remove(cardToDelete.getUuid());
        }
    }

    public void cardRemovalFromTag(Card cardToDelete, TagList tagList) {
        for (TagUUID tagUUID : cardToDelete.getTagsUUID()) {
            Tag tagToDeleteCardFrom = tagList.findTagFromUUID(tagUUID);
            tagToDeleteCardFrom.getCardsUUID().remove(cardToDelete.getUuid());
        }
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Card cardToDelete = cardList.findCard(cardSelector);
        if (cardToDelete == null) {
            throw new CardNotFoundException();
        }

        // Delete references to card
        cardRemovalFromDecks(cardToDelete, deckList);
        cardRemovalFromTag(cardToDelete, tagList);

        // Delete actual Card object
        cardList.delete(cardSelector);

        ui.printDeleteSuccess();
        ui.printNumOfQuestions(cardList);
    }
}
