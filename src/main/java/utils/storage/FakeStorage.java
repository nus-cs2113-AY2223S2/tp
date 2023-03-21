package utils.storage;

import model.CardList;

/**
 * For testing without writing to file system
 */
public class FakeStorage extends Storage {

    public FakeStorage() {
        super("");
    }

    @Override
    public CardList load() {
        return new CardList();
    }

    @Override
    public void save(CardList cardList) {

    }
}
