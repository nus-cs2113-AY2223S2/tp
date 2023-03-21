package utils.storage;

import model.CardList;
import utils.exceptions.InkaException;

public interface IDataStorage {

    CardList load() throws InkaException;

    void save(CardList cardList) throws InkaException;
}
