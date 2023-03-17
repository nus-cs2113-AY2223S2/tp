package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TagList {
    private List<Tag> tags;

    // Initialize with no cards
    public TagList() {
        this.tags = new ArrayList<>();
    }

    // Initialize from existing container of cards
    public TagList(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public Tag findTag(String tagName) {
        for (Tag tag : tags) {
            if (tag.getTagName().equals(tagName)) {
                return tag;
            }
        }
        return null;
    }

    public void deleteTagByUUID(UUID uuid) {
        tags.removeIf(tag -> (tag.getUUID().equals(uuid)));
    }
    public void delete(int id) {
        this.tags.remove(id);
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public int size() {
        return this.tags.size();
    }

    public Tag get(int id) {
        return this.tags.get(id);
    }
}
