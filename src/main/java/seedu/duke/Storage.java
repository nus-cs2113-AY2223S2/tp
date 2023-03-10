package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String SAVED_MODULES_FILE_PATH = "./data/saved_modules.txt";
    public ArrayList<Module> modules;

    public Storage() {
        this.modules = new ArrayList<>();
        try {
            initialiseMods();
        } catch (IOException e) {
            System.out.println("Initialise Saved Modules Failure");
        }
    }

    private void initialiseMods() throws IOException {
        File savedModulesFile = new File(SAVED_MODULES_FILE_PATH);
        if (!savedModulesFile.exists()) {
            savedModulesFile.createNewFile();
            return;
        }
        DataReader.readModData(SAVED_MODULES_FILE_PATH, modules);
    }

    public void addModuleToModuleList(Module moduleToAdd) {
        modules.add(moduleToAdd);
        try {
            saveModuleToStorage(moduleToAdd.toString());
        } catch (IOException e) {
            System.out.println("Save Module Failed");
        }
    }

    private void saveModuleToStorage(String saveModuleString) throws IOException {
        FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH, true);
        fw.write(writeTaskPreparation(saveModuleString));
        fw.close();
    }

    private static String writeTaskPreparation(String saveString) {
        return saveString + System.lineSeparator();
    }
}
