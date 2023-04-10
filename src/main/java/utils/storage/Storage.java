package utils.storage;

import java.io.File;
import utils.UserInterface;

public abstract class Storage implements IDataStorage {

    public static UserInterface ui;
    protected File saveFile;



    public Storage(String filePath,  UserInterface ui) {
        saveFile = new File(filePath);
        this.ui = ui;
    }

    public boolean saveFileExists() {
        return saveFile.exists() && saveFile.isFile();
    }

}
