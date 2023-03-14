package utils.storage;

import model.CardList;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;

public interface IStorage {

    CardList load(String fileName) throws StorageLoadFailure;
    void save(String fileName, CardList cardList) throws StorageSaveFailure;

}
