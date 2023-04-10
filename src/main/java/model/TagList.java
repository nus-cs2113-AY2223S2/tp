package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import utils.exceptions.TagNotFoundException;

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

    public Tag findTag(TagSelector tagSelector) throws TagNotFoundException {
        if (tagSelector.getIndex().isPresent()) {
            // Index from user input is 1-indexed
            try {
                return tags.get(tagSelector.getIndex().get() - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new TagNotFoundException();
            }
        } else if (tagSelector.getTagName().isPresent()) {
            return findTagFromName(tagSelector.getTagName().get());
        }

        return null;
    }

    /**
     * Find the tag with tagName specified from the tagList.
     *
     * @param tagName
     * @return The tag with the tagName specified if the tag is found, returns null otherwise.
     * @throws TagNotFoundException
     */
    public Tag findTagFromName(String tagName) {
        for (Tag tag : tags) {
            if (tag.getTagName().equals(tagName)) {
                return tag;
            }
        }
        return null;
    }

    public Tag findTagFromUUID(TagUUID tagUUID) {
        for (Tag tag : tags) {
            if (tag.getUUID().equals(tagUUID.uuid)) {
                return tag;
            }
        }
        return null;
    }

    public void delete(int id) throws IndexOutOfBoundsException {
        this.tags.remove(id);
    }

    public void delete(TagSelector tagSelector) throws TagNotFoundException {
        Optional<Integer> index = tagSelector.getIndex();
        Optional<String> tagName = tagSelector.getTagName();

        if (index.isPresent()) {
            // Index from user input is 1-indexed
            try {
                delete(index.get() - 1);
                return;
            } catch (IndexOutOfBoundsException e) {
                throw new TagNotFoundException();
            }
        } else if (tagName.isPresent()) {
            for (int i = 0; i < tags.size(); i++) {
                if (tags.get(i).getTagName().equals(tagName.get())) {
                    delete(i);
                    return;
                }
            }
        }
        throw new TagNotFoundException();
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
