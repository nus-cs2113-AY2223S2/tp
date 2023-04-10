package seedu.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import static seedu.main.Main.LOGGER;

/**
 * Represents a storage mainly in charge of saving data to text files in the "data" directory.
 * This includes tasks such as retrieving previously saved modules, modifying saved modules
 * and saving new modules to the modules.txt file. The storage also saves the user's name to
 * the name.txt file.
 */
public class Storage {
    private static final String MODULES_FILE_PATH = "data/modules.txt";
    private static final String NAME_FILE_PATH = "data/name.txt";
    private static final String DIRECTORY = "data";

    /**
     * Retrieves the user's name if it was previously saved
     * @return A string representing the user's name
     * @throws FileNotFoundException If the name.txt file cannot be located and read
     */
    public static String getSavedName() throws FileNotFoundException {
        File dir = new File(DIRECTORY);
        File f = new File(NAME_FILE_PATH);

        // Creates directory for file if it does not exist
        if (!dir.exists()){
            dir.mkdirs();
        }

        // Create file if it does not exist
        try {
            f.createNewFile();
        } catch (IOException e) {
            Print.printCreateNameFileError();
        }

        // Reading from text file to get stored data
        Scanner s = new Scanner(f);
        String name = "";
        if (s.hasNext()) {
            name = s.nextLine();
        }
        return name;
    }

    /**
     * Saves the user's name to the name.txt file.
     * @param name A string representing the user's name
     */
    public static void saveName(String name) {
        LOGGER.log(Level.INFO, "Starting process to save name to text file");
        try {
            writeNameToFile(name);
            LOGGER.log(Level.INFO, "Finished saving name to text file");
        } catch (IOException e) {
            Print.printNameSavingError();
            LOGGER.log(Level.WARNING, "Failed to save name to text file");
        }
    }

    /**
     * Write the user's name as a line to name.txt file
     * @param name A string representing the user's name
     * @throws IOException If there is an error writing the name.txt file
     */
    public static void writeNameToFile(String name) throws IOException {
        FileWriter fw = new FileWriter(NAME_FILE_PATH);
        fw.write(name);
        fw.close();
    }

    /**
     * Retrieves the modules which were previously stored in modules.txt and returns these modules
     * in a string array, with each module represented by a string.
     * @return An array of strings, with each string representing a previously saved module
     * @throws FileNotFoundException If the modules.txt file cannot be located and read
     */
    public static ArrayList<String> getSavedModules() throws FileNotFoundException {
        File dir = new File(DIRECTORY);
        File f = new File(MODULES_FILE_PATH);

        // Creates directory for file if it does not exist
        if (!dir.exists()){
            dir.mkdirs();
        }

        // Create file if it does not exist
        try {
            f.createNewFile();
        } catch (IOException e) {
            Print.printCreateModulesFileError();
        }

        // Reading from text file to get stored data
        Scanner s = new Scanner(f);
        ArrayList<String> savedModules = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            savedModules.add(line);
        }
        return savedModules;
    }

    /**
     * Save all the modules in the tracked array to modules.txt file.
     * @param savedModules A Module array of tracked modules
     */
    public static void saveModules(ArrayList<Module> savedModules) {
        LOGGER.log(Level.INFO, "Starting process to save modules to text file");
        try {
            writeModulesToFile(savedModules);
            LOGGER.log(Level.INFO, "Finished saving modules to text file");
        } catch (IOException e) {
            Print.printModulesSavingError();
            LOGGER.log(Level.WARNING, "Failed to save modules to text file");
        }
    }

    /**
     * For each module in the array of tracked modules, write each module on a separate line
     * to modules.txt file. The data of each module is written in the following format:
     * moduleType|grade|moduleCode|moduleCredits|year|semester
     * @param savedModules An array containing the tracked modules
     * @throws IOException If there is an error writing to the modules.txt file
     */
    private static void writeModulesToFile(ArrayList<Module> savedModules) throws IOException {
        FileWriter fw = new FileWriter(MODULES_FILE_PATH);
        for (int i = 0; i < savedModules.size(); i++) {
            Module currModule = savedModules.get(i);

            // Determine module type and add module type
            String lineToWrite = currModule.getModuleType();
            lineToWrite += "|" + currModule.getGrade(); // Add grade
            lineToWrite += "|" + currModule.getModuleCode(); // Add module code
            lineToWrite += "|" + currModule.getModularCredits(); // Add MC
            lineToWrite += "|" + currModule.getYear(); // Add year
            lineToWrite += "|" + currModule.getSemester(); // Add semester

            lineToWrite += System.lineSeparator();
            fw.write(lineToWrite);
        }
        fw.close();
    }
}
