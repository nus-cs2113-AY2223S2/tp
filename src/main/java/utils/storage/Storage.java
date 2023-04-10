package utils.storage;

import java.io.File;

public abstract class Storage implements IDataStorage {

    protected File saveFile;

    public Storage(String filePath) {
        saveFile = new File(filePath);
    }

    public boolean saveFileExists() {
        return saveFile.exists() && saveFile.isFile();
    }

}
