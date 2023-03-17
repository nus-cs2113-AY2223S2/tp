package model;

import java.util.ArrayList;
import java.util.UUID;

public class Tag {
    private UUID uuid;
    private String tagName;
    private ArrayList<UUID> cards = new ArrayList<>();

    public Tag (String tagName, String cardUUID) {
        this.tagName = tagName;
        this.uuid = UUID.randomUUID();
        cards.add(UUID.fromString(cardUUID));
    }

    public String getUUID() {
        return this.uuid.toString();
    }
    public String getTagName() {
        return this.tagName;
    }

    public ArrayList<UUID> getCards() {
        return this.cards;
    }

    public void addCard(UUID cardUUID) {
        cards.add(cardUUID);
    }

    @Override
    public String toString() {
        return "Tag name : " + tagName + ", tag uuid : " + uuid;
    }
}
