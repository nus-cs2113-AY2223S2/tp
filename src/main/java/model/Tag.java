package model;

import java.util.ArrayList;
import java.util.UUID;

public class Tag {
    private UUID uuid;
    private String tagName;
    private ArrayList<UUID> cards = new ArrayList<>();

    public Tag (String tagName, UUID cardUUID) {
        this.tagName = tagName;
        cards.add(cardUUID);
    }
}
