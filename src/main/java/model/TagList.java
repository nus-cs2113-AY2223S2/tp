package model;

import java.util.ArrayList;
import java.util.List;

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

    public void addTag(Tag tag) {
        this.tags.add(tag);
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
