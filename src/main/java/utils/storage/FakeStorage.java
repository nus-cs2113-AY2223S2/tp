package utils.storage;

import model.CardList;
import model.DeckList;
import model.Memory;
import model.TagList;

/**
 * For testing without writing to file system
 */
public class FakeStorage extends Storage {

    public FakeStorage() {
        super("");
    }

    @Override
    public Memory load() {
        return new Memory();
    }

    @Override
    public void save(CardList cardList, TagList tagList, DeckList deckList) {

    }
}
