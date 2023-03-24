package utils.storage;

import java.io.File;
import java.io.IOException;

public abstract class Storage implements IDataStorage {

    protected File saveFile;

    public Storage(String filePath) {
        saveFile = new File(filePath);
    }

    public boolean saveFileExists() {
        return saveFile.exists() && saveFile.isFile();
    }

    public void createSaveFile() throws IOException {
        saveFile.createNewFile();
    }
}
