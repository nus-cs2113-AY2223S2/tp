package utils.storage;

import model.CardList;
import model.DeckList;
import model.Memory;
import model.TagList;
import utils.exceptions.InkaException;

public interface IDataStorage {

    Memory load() throws InkaException;

    void save(CardList cardList, TagList tagList, DeckList deckList) throws InkaException;
}
