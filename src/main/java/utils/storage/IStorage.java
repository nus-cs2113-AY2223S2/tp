package utils.storage;

import model.CardList;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;

public interface IStorage {

    CardList load() throws StorageLoadFailure;
    void save(CardList cardList) throws StorageSaveFailure;

}
