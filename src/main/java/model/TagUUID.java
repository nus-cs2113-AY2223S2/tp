package model;

import java.util.UUID;

public class TagUUID {
    UUID uuid;

    public TagUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean equals(TagUUID tagUUID) {
        return this.uuid.equals(tagUUID.uuid);
    }

    @Override
    public String toString() {
        return this.uuid.toString();
    }
}
