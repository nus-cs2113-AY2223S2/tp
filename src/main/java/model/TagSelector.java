package model;

import java.util.Optional;
import java.util.UUID;

public class TagSelector {
    private Optional<TagUUID> uuid = Optional.empty();
    private Optional<String> tagName = Optional.empty();
    private Optional<Integer> index = Optional.empty();

    public TagSelector(String uuidOrTagName) throws IllegalArgumentException {
        if (uuidOrTagName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be empty or whitespace-only.");
        }
        try {
            this.uuid = Optional.of(new TagUUID(UUID.fromString(uuidOrTagName)));
        } catch (IllegalArgumentException e) {
            this.tagName = Optional.of(uuidOrTagName);
        }
    }

    public TagSelector(int index) {
        this.index = Optional.of(index);
    }

    public Optional<TagUUID> getUuid() {
        return uuid;
    }

    public Optional<String> getTagName() {
        return tagName;
    }
    public Optional<Integer> getIndex() {
        return index;
    }
}

