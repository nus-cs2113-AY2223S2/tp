package model;

import java.util.ArrayList;
import java.util.UUID;

public class Tag {
    private TagUUID uuid;
    private String tagName;
    private ArrayList<CardUUID> cards = new ArrayList<>();
    private ArrayList<DeckUUID> decks = new ArrayList<>();

    public Tag(String tagName) {
        this.tagName = tagName;
        this.uuid = new TagUUID(UUID.randomUUID());
    }

    public Tag(String tagName, CardUUID cardUUID) {
        this.tagName = tagName;
        this.uuid = new TagUUID(UUID.randomUUID());
        cards.add(cardUUID);
    }

    public Tag(String tagName, String uuidStr) {
        this.tagName = tagName;
        this.uuid = new TagUUID(UUID.fromString(uuidStr));
    }

    public ArrayList<DeckUUID> getDecks() {
        return decks;
    }

    public boolean cardEmpty() {
        return cards.isEmpty();
    }

    public TagUUID getUUID() {
        return this.uuid;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void editTagName(String newTagName) {
        this.tagName = newTagName;
    }

    public boolean cardIsInTag(CardUUID cardUUID) {
        for (CardUUID cardUUIDList : cards) {
            if (cardUUIDList.equals(cardUUID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDeckEmpty() {
        return decks.isEmpty();
    }

    public ArrayList<CardUUID> getCardsUUID() {
        return this.cards;
    }

    public void addCard(CardUUID cardUUID) {
        cards.add(cardUUID);
    }

    public void addCardIntoDeckHashSet(DeckList deckList, CardUUID cardUUID) {
        for (DeckUUID deckUUID : this.decks) {
            Deck deck = deckList.findDeckFromUUID(deckUUID);
            if (!deck.cardIsInList(cardUUID) && !deck.cardIsInMap(cardUUID)) {
                deck.addCardToMap(cardUUID);
            }
            deck.addCardToSet(cardUUID);
        }
    }

    public void removeCard(CardUUID cardUUID) {
        cards.remove(cardUUID);
    }

    public void removeDecks(DeckUUID deckUUID) {
        decks.remove(deckUUID);
    }

    public void addDeck(DeckUUID deckUUID) {
        decks.add(deckUUID);
    }

    @Override
    public String toString() {
        return "Tag name : " + tagName;
    }
}
