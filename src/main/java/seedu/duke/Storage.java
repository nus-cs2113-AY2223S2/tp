package seedu.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Storage implements DatabaseInterface {

    private static Storage instance = null;
    private static final String SAVED_MODULES_FILE_PATH = "data/saved_modules.txt";
    private ArrayList<Module> modules;

    /**
     * Constructor of the Storage class that initialises the Storage database.
     * Prints a failure message when there is an IOException when trying to initialise.
     */
    private Storage() {
        this.modules = new ArrayList<>();
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Saved Modules Failure");
        }
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    /**
     * Initialises the Storage database for module saving.
     * It creates a new directory and file being data/saved_modules.txt when there is no file found.
     * If there is already an existing file, it loads the data into the ArrayList of modules.
     *
     * @throws IOException when the file path does not exist but commands are being given to load from the file path.
     */
    public void initialiseDatabase() throws IOException {
        File savedModulesFile = new File(SAVED_MODULES_FILE_PATH);
        if (!savedModulesFile.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            savedModulesFile.createNewFile();
            return;
        }
        readModData(SAVED_MODULES_FILE_PATH, modules);
        boolean isStorageCorrupted = checkDatabaseCorrupted();
        if (isStorageCorrupted) {
            UI.printStorageCorruptedMessage();
        }
        try {
            writeModListToFile(modules);
        } catch (IOException e) {
            UI.printWriteToDatabaseFailureMessage();
        }
    }

    /**
     * Returns a boolean value, either true or false on whether the saved_modules.txt file is corrupted.
     * Corrupted occurs when there might be tampering of the txt file in an incorrect way.
     *
     * @return true if saved_modules.txt file is corrupted.
     * @return false if saved_modules.txt file is not corrupted.
     */
    @Override
    public boolean checkDatabaseCorrupted() {
        ArrayList<Module> allModules = DataReader.getDataReaderOneInstance().getModules();
        boolean isCorrupted = false;
        ArrayList<Module> copyArrayList = new ArrayList<>();
        for (Module module : modules) {
            if (!checkIsValidModule(module, allModules)) {
                isCorrupted = true;
            } else {
                copyArrayList.add(module);
            }
        }
        modules.clear();
        for (Module toCopyModule : copyArrayList) {
            modules.add(toCopyModule);
        }
        return isCorrupted;
    }

    /**
     * Returns a boolean value, either true or false on whether the module is a valid one cross compared to the list of
     * valid modules.
     *
     * @param moduleToCheck is the module to be checked whether valid or not.
     * @param allModules    is the arraylist of modules that is being cross-referenced from.
     * @return true if the moduleToCheck is a valid one, meaning it exists in allModules.
     * @return false if the moduleToCheck is an invalid one, meaning it does not exist in allModules.
     */
    private boolean checkIsValidModule(Module moduleToCheck, ArrayList<Module> allModules) {
        for (Module module : allModules) {
            if (module.toString().equals(moduleToCheck.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads module information from the txt file line by line and adding it into the module list
     *
     * @param modulesFilePath is the moduleFilePath to read data from.
     * @param modules         is the ArrayList of modules to add the txt file module data into.
     */
    private void readModData(String modulesFilePath, ArrayList<Module> modules) {
        try (BufferedReader br = new BufferedReader(new FileReader(modulesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length != 7) {
                    UI.printModuleCorruptDeleteMessage();
                    continue;
                }
                if (row[3].equals("N/A")) {
                    row[3] = "0";
                }
                try {
                    Module module = new Module(Integer.parseInt(row[0]), row[1], row[2],
                            Integer.parseInt(row[3]), row[4], row[5], Integer.parseInt(row[6]));
                    boolean isDuplicate = doesModuleExist(module);
                    if (isDuplicate) {
                        continue;
                    }
                    modules.add(module);
                } catch (NumberFormatException e) {
                    UI.printModuleCorruptDeleteMessage();
                }
            }
        } catch (IOException e) {
            System.out.println("Read Modules failure");
        }
    }

    /**
     * Returns a boolean value, either true or false depending on whether the module was added successfully to the
     * list of modules.
     * Checks if the module to be added was already previously added.
     *
     * @param moduleToAdd is the target module to be added into the list.
     * @return true if the module was added successfully.
     * @return false if the module was not added successfully, in cases like it was already added before or when the
     * moduleToAdd is of null value.
     */
    public boolean addModuleToModuleList(Module moduleToAdd) {
        assert (moduleToAdd != null) : "error line 111";
        if (moduleToAdd == null) {
            UI.printAddModuleFailureMessage();
            return false;
        }
        if (doesModuleExist(moduleToAdd)) {
            UI.printModAlreadyExistMessage();
            return false;
        }
        modules.add(moduleToAdd);
        sortModulesAccordingToPrintingLength(modules);
        try {
            saveModuleToStorage(moduleToAdd.toString());
        } catch (IOException e) {
            UI.printAddModuleFailureMessage();
        }
        return true;
    }

    /**
     * Returns a boolean value, either true or false depending on whether the module already exists in the module list.
     *
     * @param moduleToAdd is the module to be checked if already exists
     * @return true if module already exists in the module list.
     * @return false if module does not exist in the module list.
     */
    public boolean doesModuleExist(Module moduleToAdd) {
        for (Module module : modules) {
            if (moduleToAdd.toString().equalsIgnoreCase(module.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves Module to the saved_modules.txt file by appending it to the back of the txt file.
     *
     * @param saveModuleString is the String format to be saved for the target module.
     * @throws IOException when the file path does not exist but commands are being given to load from the file path.
     */
    private void saveModuleToStorage(String saveModuleString) throws IOException {
        FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH, true);
        fw.write(writeTaskPreparation(saveModuleString));
        fw.close();
    }

    /**
     * Returns true or false depending on whether the module was deleted successfully.
     * Deletes the module corresponding to the uni specified by user. Module will the removed from user's
     * saved list of modules. Uses Index relative to specific PU list. Sorts ArrayList modules after deleting
     * module to ensure that modules are still sorted according to printing length
     *
     * @param indexToDeletePuSpecificList Index of that module that is given in user input, relative to PU list.
     * @param modules                     The ArrayList of all modules user saved.
     * @param database                    Database of the user's saved list of modules.
     * @param uniID                       Partner University's ID number.
     * @return True if successfully deleted the module, false if unsuccessful.
     */
    public static boolean deleteModule(int indexToDeletePuSpecificList, ArrayList<Module> modules,
                                       Storage database, int uniID) {
        int indexToDeletePuSpecificListToZeroBased = indexToDeletePuSpecificList - 1;
        int counterUpToIndexToDelete = 0;
        int indexToDelete = -1;
        for (int i = 0; i < modules.size(); i++) {
            Module currentModule = modules.get(i);
            if (currentModule.getUnivId() == uniID) {
                if (counterUpToIndexToDelete == indexToDeletePuSpecificListToZeroBased) {
                    indexToDelete = i;
                    break;
                }
                counterUpToIndexToDelete++;
                assert counterUpToIndexToDelete <= indexToDeletePuSpecificListToZeroBased : "counterUpToIndexToDelete " +
                        "should not be greater than indexToDeletePuSpecificListToZeroBased";
            }
        }

        try {
            modules.remove(indexToDelete);
        } catch (IndexOutOfBoundsException e) {
            UI.printDeleteNumError();
            return false;
        }
        sortModulesAccordingToPrintingLength(modules);
        try {
            database.writeModListToFile(modules);
        } catch (IOException e) {
            UI.printWriteToDatabaseFailureMessage();
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
    public void writeModListToFile(ArrayList<Module> modules) throws IOException {
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

    /**
     * Sorts modules according to printing length.
     *
     * @param modules Module to be printed to User Console
     */
    public static void sortModulesAccordingToPrintingLength(ArrayList<Module> modules) {
        modules.sort(Comparator.comparing(Module::getPrintingLength));
    }
}
