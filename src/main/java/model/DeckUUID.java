package model;

import java.util.UUID;
public class DeckUUID {
    UUID uuid;

    public DeckUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean equals(DeckUUID deckUUID) {
        return this.uuid.equals(deckUUID.uuid);
    }

    @Override
    public String toString() {
        return this.uuid.toString();
    }
}
