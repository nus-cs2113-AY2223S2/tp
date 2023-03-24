package model;

import java.util.UUID;

public class CardUUID {
    UUID uuid;

    public CardUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean equals(CardUUID cardUUID) {
        return this.uuid.equals(cardUUID.uuid);
    }

    @Override
    public String toString() {
        return this.uuid.toString();
    }
}
