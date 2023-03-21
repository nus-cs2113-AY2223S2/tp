package seedu.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage implements DatabaseInterface {

    private static final String SAVED_MODULES_FILE_PATH = "data/saved_modules.txt";
    private ArrayList<Module> modules;

    public Storage() {
        this.modules = new ArrayList<>();
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Saved Modules Failure");
        }
    }

    public void initialiseDatabase() throws IOException {
        File savedModulesFile = new File(SAVED_MODULES_FILE_PATH);
        if (!savedModulesFile.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            savedModulesFile.createNewFile();
            return;
        }
        readModData(SAVED_MODULES_FILE_PATH, modules);
    }


    private void readModData(String modulesFilePath, ArrayList<Module> modules) {
        try (BufferedReader br = new BufferedReader(new FileReader(modulesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row[3].equals("N/A")) {
                    row[3] = "0";
                }
                Module module = new Module(Integer.parseInt(row[0]), row[1], row[2],
                        Integer.parseInt(row[3]), row[4], row[5], Integer.parseInt(row[6]));
                modules.add(module);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addModuleToModuleList(Module moduleToAdd) {
        assert (moduleToAdd != null) : "error line 53";
        if (moduleToAdd == null) {
            UI.printAddModuleFailureMessage();
            return;
        }
        modules.add(moduleToAdd);
        try {
            saveModuleToStorage(moduleToAdd.toString());
        } catch (IOException e) {
            UI.printAddModuleFailureMessage();
        }
    }

    private void saveModuleToStorage(String saveModuleString) throws IOException {
        FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH, true);
        fw.write(writeTaskPreparation(saveModuleString));
        fw.close();
    }

    /**
     * Deletes the module corresponding to the uni specified by user. Module will the removed from user's
     * saved list of modules.
     * @param indexToDelete Index of that module that is given in user input.
     * @param uniModuleList The corresponding ArrayList of that specified uni.
     * @param database      Database of the user's saved list of modules.
     * @return True if successfully deleted the module, false if unsuccessful.
     */
    public static boolean deleteModule(int indexToDelete, ArrayList<Module> uniModuleList,
                                       Storage database) {
        if (indexToDelete == -1) {
            return false;
        }
        int indexToZeroBased = indexToDelete - 1;
        try {
            uniModuleList.remove(indexToZeroBased);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
            return false;
        }
        try {
            database.writeListToFile(uniModuleList);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
            return false;
        }
        return true;
    }

    /**
     * Adds and overwrites ArrayList of user's saved modules list in database.
     *
     * @param modules ArrayList of modules to be written into database.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public void writeListToFile(ArrayList<Module> modules) throws IOException {
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
    public ArrayList<Module> getModules() {
        return modules;
    }

//    private static String writeTaskPreparation(String saveString) {
//        return saveString + System.lineSeparator();
//    }
}
