package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private static final String SAVED_MODULES_FILE_PATH = "./data/saved_modules.txt";
    private ArrayList<Module> modules;
    
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

    /**
     * Adds and overwrites ArrayList of user's saved modules list in database.
     *
     * @param modules ArrayList of modules to be written into database.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public static void writeListToFile(ArrayList<Module> modules) throws IOException {
        FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH);
        String stringToAdd = "";
        for (Module module : modules) {
            stringToAdd += writeTaskPreparation(module.toString());
        }
        fw.write(stringToAdd);
        fw.close();
    }

    /**
     * Returns list of modules in ArrayList type.
     *
     * @return ArrayList of modules.
     */
    public ArrayList<Module> getModule() {
        return modules;
    }

    private static String writeTaskPreparation(String saveString) {
        return saveString + System.lineSeparator();
    }

}
