package model;

import java.util.UUID;

public class InkaUUID {

    UUID uuid;

    public InkaUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean equals(UUID uuid) {
        return this.uuid.equals(uuid);
    }

    @Override
    public String toString() {
        return this.uuid.toString();
    }
}
