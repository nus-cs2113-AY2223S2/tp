package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardList  {

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

    public void delete(int id) {
        this.cards.remove(id);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Card findCard(UUID cardUUID) {
        for (Card card : cards) {
            if (card.getUuid().equals(cardUUID)) {
                return card;
            }
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
