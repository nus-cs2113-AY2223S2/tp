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

    public void edit(int id, Card card) {
        delete(id);
        this.cards.add(id, card);
    }

    public void list() {
        UserInterface.printList(this.cards);
    }
}
