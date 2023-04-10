package utils.storage;

import java.io.File;
import utils.UserInterface;

public abstract class Storage implements IDataStorage {

    protected File saveFile;

    public static UserInterface ui;

    public Storage(String filePath,  UserInterface ui) {
        saveFile = new File(filePath);
        this.ui = ui;
    }

    public boolean saveFileExists() {
        return saveFile.exists() && saveFile.isFile();
    }

}
