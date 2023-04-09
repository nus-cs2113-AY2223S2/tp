package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Deck {
    private String deckName;
    private DeckUUID deckUUID;

    // Contains CardUUIDs for cards that are added via CardUUID and/or Tag
    private HashSet<CardUUID> cardsSet = new HashSet<>();

    // Contains only CardUUIDs that are added through Tag
    private HashMap<CardUUID, Integer>  cardUUIDIntegerHashMap= new HashMap<>();
    private ArrayList<CardUUID> cards = new ArrayList<>();
    private ArrayList<TagUUID> tags = new ArrayList<>();

    public Deck(String deckName) {
        this.deckName = deckName;
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }

    public Deck(String deckName, CardUUID cardUUID) {
        this.deckName = deckName;
        this.cards.add(cardUUID);
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }

    public Deck(String deckName, TagUUID tagUUID) {
        this.deckName = deckName;
        this.tags.add(tagUUID);
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }
    public Deck(String deckName, TagUUID tagUUID, CardUUID cardUUID) {
        this.deckName = deckName;
        this.tags.add(tagUUID);
        this.cards.add(cardUUID);
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }

    public String getDeckName() {
        return deckName;
    }
    public boolean cardIsInSet(CardUUID cardUUID) {
        return cardsSet.contains(cardUUID);
    }

    public boolean cardIsInList(CardUUID cardUUID) {
        return cards.contains(cardUUID);
    }

    public boolean tagIsInDeck(TagUUID tagUUID) {
        for(TagUUID tagUUIDHolder : tags) {
            if(tagUUIDHolder.equals(tagUUID)) {
                return true;
            }
        }
        return false;
    }

    public boolean cardIsInMap(CardUUID cardUUID) {
        return cardUUIDIntegerHashMap.containsKey(cardUUID);
    }


    public void setTags(ArrayList<TagUUID> tags) {
        this.tags = tags;
    }

    public DeckUUID getDeckUUID() {
        return deckUUID;
    }

    public void setDeckUUID(String uuidStr){
        this.deckUUID = new DeckUUID(UUID.fromString(uuidStr));
    }


    public ArrayList<CardUUID> getCardsUUID() {
        return cards;
    }

    public ArrayList<TagUUID> getTagsUUID() {
        return tags;
    }

    public void editDeckName(String newDeckName) {
        this.deckName = newDeckName;
    }

    /**
     * Adds a single card through its CardUUID
     * @param cardUUID
     */
    public void addCard(CardUUID cardUUID) {
        cards.add(cardUUID);
    }

    public void setCardsSet(HashSet<CardUUID> cardsSet) {
        this.cardsSet = cardsSet;
    }



    public HashSet<CardUUID> getCardsSet() {
        return this.cardsSet;
    }

    public void addCardToSet(CardUUID cardUUID) {

        cardsSet.add(cardUUID);
    }

    public void removeCardFromSet(CardUUID cardUUID) {
        cardsSet.remove(cardUUID);
    }
    public void addTag(TagUUID tagUUID) {
        this.tags.add(tagUUID);
    }

    public void addCardsToSet(TagUUID tagUUID, TagList tagList) {
        Tag tagToCheck = tagList.findTagFromUUID(tagUUID);
        ArrayList<CardUUID> cardUUIDArrayList = tagToCheck.getCardsUUID();
        for(CardUUID cardUUID: cardUUIDArrayList) {
            cardsSet.add(cardUUID);
        }
    }

    public void addCardstoMap(TagUUID tagUUID, TagList tagList) {
        Tag tagToCheck = tagList.findTagFromUUID(tagUUID);
        ArrayList<CardUUID> cardUUIDArrayList = tagToCheck.getCardsUUID();
        for(CardUUID cardUUID: cardUUIDArrayList) {
            if(!this.cardIsInMap(cardUUID)) {
                this.cardUUIDIntegerHashMap.put(cardUUID, 1);
            } else {
                this.cardUUIDIntegerHashMap.put(cardUUID, cardUUIDIntegerHashMap.get(cardUUID)+1);
            }
        }
    }

    public void removeTaggedCardsMap(TagUUID tagUUID, TagList tagList) {
        Tag tagToCheck = tagList.findTagFromUUID(tagUUID);
        ArrayList<CardUUID> cardUUIDArrayList = tagToCheck.getCardsUUID();
        for(CardUUID cardUUID: cardUUIDArrayList) {
            if(cardUUIDIntegerHashMap.get(cardUUID)==1) {
                cardUUIDIntegerHashMap.remove(cardUUID);
                if(!this.cardIsInList(cardUUID)) {
                    cardsSet.remove(cardUUID);
                }
            } else {
                cardUUIDIntegerHashMap.put(cardUUID, cardUUIDIntegerHashMap.get(cardUUID)-1);
            }
        }
    }
    public void setCards(ArrayList<CardUUID> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Deck name : " + deckName + ", deck uuid : " + deckUUID;
    }
}
