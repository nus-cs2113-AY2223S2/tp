package utils.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import model.CardList;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.CardInSetNotInList;
import utils.exceptions.CardNotInDeck;
import utils.exceptions.DeckNotFoundException;
import utils.exceptions.InkaException;
import utils.exceptions.LongDeckNameException;
import utils.exceptions.UUIDWrongFormatException;
import utils.storage.IDataStorage;

public class RemoveCardFromDeckCommand extends Command {
    private CardUUID cardUUID;

    private String deckName;

    public RemoveCardFromDeckCommand(String cardUUID, String deckName) throws InkaException {
        this.deckName = deckName;
        //this.tagName = tagName;
        try {
            this.cardUUID = new CardUUID(UUID.fromString(cardUUID));
        } catch (IllegalArgumentException e) {
            throw new UUIDWrongFormatException();
        }
    }

    public void removeCardFromDeck(DeckList deckList, String deckName, CardUUID cardUUID)
            throws InkaException {
        Deck deck = deckList.findDeckFromName(deckName);

        if (deckName.length() > 50) {
            throw new LongDeckNameException();
        } else if (deck == null) {
            throw new DeckNotFoundException();
        }
        //This is only for isolated cards, not for the cards that are tagged
        ArrayList<CardUUID> deckCardList = deck.getCardsUUID();
        HashSet<CardUUID> deckCardSet = deck.getCardsSet();
        boolean wasCardInDeck = deckCardList.removeIf(card -> card.equals(cardUUID));
        if (!wasCardInDeck && deck.cardIsInMap(cardUUID)) {
            throw new CardInSetNotInList();
        } else if(!wasCardInDeck) {
            throw new CardNotInDeck();
        } else if (!deck.cardIsInMap(cardUUID)) { // if the card does not exist under any tag
            deckCardSet.remove(cardUUID);
            deck.setCardsSet(deckCardSet);
        }
        deck.setCards(deckCardList);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        removeCardFromDeck(deckList, deckName, cardUUID);
        ui.printRemoveCardFromDeckSuccess(cardUUID, deckName);
    }
}
