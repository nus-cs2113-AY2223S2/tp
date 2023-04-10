package utils.command;

import model.Card;
import model.CardList;
import model.CardSelector;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.DeckUUID;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.CardInDeckException;
import utils.exceptions.CardInDeckUnderTagException;
import utils.exceptions.InkaException;
import utils.exceptions.LongDeckNameException;
import utils.storage.IDataStorage;

public class AddCardToDeckCommand extends Command {

    private String deckName;
    private DeckUUID deckUUID;
    private CardSelector cardSelector;

    public AddCardToDeckCommand(String deckName, CardSelector cardSelector) {
        this.deckName = deckName;
        this.cardSelector = cardSelector;
    }

    private void addCardToDeck(DeckList deckList, Card cardToAdd, UserInterface ui) throws InkaException {
        Deck deckToAdd = deckList.findDeckFromName(deckName);
        CardUUID cardToAddUUID = cardToAdd.getUuid();
        if (deckName.length() > 50) {
            throw new LongDeckNameException();
        } else if (deckToAdd == null) {
            ui.printDeckCreationSuccess();
            deckToAdd = new Deck(deckName, cardToAddUUID);
            deckToAdd.addCardToSet(cardToAddUUID);
            deckList.addDeck(deckToAdd);
        } else if (deckToAdd.cardIsInSet(cardToAddUUID)) {
            throw new CardInDeckException();
        } else if (deckToAdd.cardIsInMap(cardToAddUUID)) {
            throw new CardInDeckUnderTagException();
        } else {
            deckToAdd.addCard(cardToAddUUID); // add card to the array list
            deckToAdd.addCardToSet(cardToAddUUID); // add card to the set
        }

        //add the tag uuid to the card
        deckUUID = deckToAdd.getDeckUUID();
        cardToAdd.addDeck(deckUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Card cardToAdd = cardList.findCard(cardSelector);
        assert cardToAdd != null;

        addCardToDeck(deckList, cardToAdd, ui);
        CardUUID cardUUID = cardToAdd.getUuid();
        ui.printAddCardToDeckSuccess(cardUUID, deckName);
    }
}
