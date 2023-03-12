package utils.cardlist;

import utils.Card;
import utils.userinterface.UserInterface;

import java.util.ArrayList;

public class CardList {

    private ArrayList<Card> cards;

    public CardList() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void delete(int id) {
        this.cards.remove(id);
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
