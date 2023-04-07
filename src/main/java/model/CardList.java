package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import utils.exceptions.CardNotFoundException;

public class CardList {

    private List<Card> cards;

    // Initialize with no cards
    public CardList() {
        this.cards = new ArrayList<>();
    }

    // Initialize from existing container of cards
    public CardList(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void delete(int id) throws IndexOutOfBoundsException {
        this.cards.remove(id);
    }

    public void delete(CardSelector cardSelector) throws CardNotFoundException {
        Optional<Integer> index = cardSelector.getIndex();
        Optional<CardUUID> uuid = cardSelector.getUuid();

        if (index.isPresent()) {
            // Index from user input is 1-indexed
            try {
                delete(index.get() - 1);
                return;
            } catch (IndexOutOfBoundsException e) {
                throw new CardNotFoundException();
            }
        } else if (uuid.isPresent()) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getUuid().equals(uuid.get().uuid)) {
                    delete(i);
                    return;
                }
            }
        }

        throw new CardNotFoundException();
    }

    public List<Card> getCards() {
        return this.cards;
    }

    /**
     * Find the card with cardUUID from the cardList.
     *
     * @param cardUUID Card with UUID to find
     * @return The card with the cardUUID specified that exists in the cardList
     * @throws CardNotFoundException No card with the specified cardUUID
     */
    public Card findCardFromUUID(CardUUID cardUUID) throws CardNotFoundException {
        for (Card card : cards) {
            if (card.getUuid().equals(cardUUID.uuid)) {
                return card;
            }
        }
        throw new CardNotFoundException();
    }

    public Card findCard(CardSelector cardSelector) throws CardNotFoundException {
        if (cardSelector.getIndex().isPresent()) {
            // Index from user input is 1-indexed
            try {
                return cards.get(cardSelector.getIndex().get() - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new CardNotFoundException();
            }
        } else if (cardSelector.getUuid().isPresent()) {
            return findCardFromUUID(cardSelector.getUuid().get());
        }

        return null;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return this.cards.size();
    }

    public Card get(int id) {
        return this.cards.get(id);
    }
}
