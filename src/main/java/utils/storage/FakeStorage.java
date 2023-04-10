package utils.storage;

import model.CardList;
import model.DeckList;
import model.Memory;
import model.TagList;
import utils.UserInterface;

/**
 * For testing without writing to file system
 */
public class FakeStorage extends Storage {

    public FakeStorage(UserInterface ui) {
        super("", ui);


    }

    @Override
    public Memory load() {
        return new Memory();
    }

    @Override
    public void save(CardList cardList, TagList tagList, DeckList deckList) {

    }
}
