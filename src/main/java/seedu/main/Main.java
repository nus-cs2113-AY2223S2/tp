package seedu.main;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.main.Print.printFarewellMessage;

/**
 * Represents a ChatBot that acts as a Module Planner
 */
public class Main {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Parser parser;
    private ModuleList moduleList;

    /**
     * Loads users saved modules if any.
     * Instantiates any classes needed for execution.
     */
    public Main() {
        initialiseLogger();
        parser = new Parser();
        try {
            moduleList = new ModuleList(Storage.getSavedModules());
        } catch (FileNotFoundException e) {
            Print.printMissingModulesFileError();
            moduleList = new ModuleList();
        } catch (MainException | ArrayIndexOutOfBoundsException e) {
            Print.printLoadingSaveFileError();
            moduleList = new ModuleList();
        }
    }

    /**
     * Checks if user key in name is bye as it indicates that user wants exit program
     * @param name The string that the user inputs
     * @return a boolean that is set to true if the string given is "bye", else set to false.
     */
    public static boolean isNameBye(String name) {
        return name.equalsIgnoreCase("bye");
    }

    /**
     * Starts the Chatbot and continuously reads in user input until the application exits
     */
    public void run() {
        LOGGER.log(Level.INFO, "Modganiser is starting up!");
        Print.printLogo();

        Scanner in = new Scanner(System.in);
        String name;
        try {
            name = getUserName(in);
        } catch (FileNotFoundException e) {
            Print.printNameFileLoadingError();
            return;
        }

        if (isNameBye(name.trim())) {
            printFarewellMessage();
            Storage.saveModules(moduleList.getModuleList());
            LOGGER.log(Level.INFO, "Name was given as 'bye', exiting Modganiser.");
            return;
        }
        assert !(name.equalsIgnoreCase("bye")) : "name is bye";
        Print.printHelloMessage(name);

        while (in.hasNextLine()) {
            String line = in.nextLine().toUpperCase();
            try {
                parser.checkUserInput(line, moduleList);
                Storage.saveModules(moduleList.getModuleList());
            } catch (MainException e){
                Print.printErrorMessage(e);
            }
        }
    }

    /**
     * If the user has previously entered his/her name, extract the name from the save file.
     * Otherwise, prompt the user for his/her name
     * @param in the Scanner object to get input from stdin
     * @return the user's name
     */
    public static String getUserName(Scanner in) throws FileNotFoundException{
        String savedName = Storage.getSavedName();
        String name;

        if (isNameSaved(savedName)) {
            name = savedName;
        } else {
            name = promptUserForName(in);
        }
        return name;
    }

    /**
     * Continuously prompt the user for his/her name and save it to the save file.
     * @param in the Scanner object to get input from stdin
     * @return the user's name
     */
    public static String promptUserForName(Scanner in) {
        Print.promptForName();
        String name = "";
        boolean nameIsValid = true;
        do {
            if (!nameIsValid) {
                Print.printInvalidNameMessage();
            }
            name = in.nextLine().trim();
            if (name.trim().length() == 0) {
                nameIsValid = false;
            } else {
                nameIsValid = true;
            }
        } while (!nameIsValid);
        assert !(name.equalsIgnoreCase("bye")) : "name is bye";
        if (!name.equalsIgnoreCase("bye")) {
            Storage.saveName(name);
        }
        return name;
    }

    /**
     * Determine if the user's name has been saved to the save file previously.
     * @param name the string which represents the user's name if present, otherwise it will
     *             be an empty string
     * @return true if the user's name has been previously saved, returns false otherwise.
     */
    public static boolean isNameSaved(String name) {
        if (name.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * Main entry-point of the whole Modganiser application.
     *
     * @param args The additional system arguments when Modganiser starts up.
     */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Initialises the logger to log information about processes throughout the runtime of the program.
     * Logging information is written into the file "modganiserLog.txt".
     */
    private static void initialiseLogger() {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);
        try {
            FileHandler fh = new FileHandler("modganiserLog.txt");
            fh.setLevel(Level.FINE);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
        } catch (java.io.IOException e) {
            LOGGER.log(Level.SEVERE, "Error in file logger!", e);
        }
    }

}
