package model;


import java.util.UUID;

public abstract class InkaUUID {

    UUID uuid;

    public InkaUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InkaUUID)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        InkaUUID otherUuid = (InkaUUID) obj;
        return this.uuid.equals(otherUuid.uuid);
    }

    /**
     * Overload for easy comparison with {@link UUID}
     */
    public boolean equals(UUID uuid) {
        return this.uuid.equals(uuid);
    }

    /**
     * Hash should depend directly on underlying UUID's hash
     */
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return this.uuid.toString();
    }
}
