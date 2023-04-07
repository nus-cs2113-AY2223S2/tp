package model;

import java.util.Optional;

public class TagSelector {
    private Optional<String> tagName = Optional.empty();
    private Optional<Integer> index = Optional.empty();

    public TagSelector(String tagName) throws IllegalArgumentException {
        this.tagName = Optional.of(tagName);
    }

    public TagSelector(int index) {
        this.index = Optional.of(index);
    }

    public Optional<String> getTagName() {
        return tagName;
    }

    public Optional<Integer> getIndex() {
        return index;
    }
}
