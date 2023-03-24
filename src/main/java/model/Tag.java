package model;

import java.util.ArrayList;
import java.util.UUID;

public class Tag {
    private TagUUID uuid;
    private String tagName;
    private ArrayList<CardUUID> cards = new ArrayList<>();

    public Tag(String tagName, CardUUID cardUUID) {
        this.tagName = tagName;
        this.uuid = new TagUUID(UUID.randomUUID());
        cards.add(cardUUID);
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

    public ArrayList<CardUUID> getCardsUUID() {
        return this.cards;
    }

    public void addCard(CardUUID cardUUID) {
        cards.add(cardUUID);
    }

    @Override
    public String toString() {
        return "Tag name : " + tagName + ", tag uuid : " + uuid;
    }
}
