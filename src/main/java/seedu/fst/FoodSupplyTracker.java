package seedu.fst;

import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.logging.Level;

import seedu.fst.commands.Command;
import seedu.fst.commands.CommandResult;
import seedu.fst.exceptions.FSTException;
import seedu.fst.exceptions.InvalidStorageFilePathException;
import seedu.fst.exceptions.StorageOperationException;
import seedu.fst.food.FoodList;
import seedu.fst.general.Parser;
import seedu.fst.general.Ui;
import seedu.fst.storage.StorageFile;


/**
 * Entry point of the Food Supply Tracker application
 * Initializes the application and starts the interaction with the user.
 */
public class FoodSupplyTracker {
    private static final Logger logger = Logger.getLogger("Run FoodSupplyTracker Log");
    private final Ui ui;

    private FoodList foodList;

    private StorageFile storageFile;

    public FoodSupplyTracker() {
        ui = new Ui();
        try {
            storageFile = new StorageFile();
        } catch (InvalidStorageFilePathException e) {
            Ui.showError(e.getMessage());
        }

        try {
            foodList = storageFile.load();
        } catch (StorageOperationException e) {
            Ui.showError(e.getMessage());
        }
    }

    /**
     * Process the user input and execute actions according to the input.
     * Returns a boolean value to determine whether the command allows the exit of the program.
     *
     * @param fullCommand
     * @return boolean to determine the termination or continuation of the program.
     * @throws FSTException
     */
    public boolean processCommand(String fullCommand) throws FSTException {
        Command c = Parser.parse(fullCommand);
        CommandResult result = c.execute(foodList);
        foodList.sortFoodList();
        result.printResult();
        storageFile.save(foodList);
        return c.isExit();
    }

    /**
     * The code is taken from the module website:
     * https://nus-cs2113-ay2223s2.github.io/website/schedule/week7/project.html
     *
     * Run the food supply tracker.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                isExit = processCommand(fullCommand);
            } catch (FSTException e) {
                logger.log(Level.WARNING, "ERROR");
                Ui.showError(e.getMessage());
            } catch (NoSuchElementException e) {
                Ui.showError("Invalid input");
                return;
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry-point for the java.fst.FoodSupplyTracker application.
     */
    public static void main(String[] args) {
        logger.log(Level.INFO, "Start running FoodSupplyTracker");
        new FoodSupplyTracker().run();
        logger.log(Level.INFO, "End of running FoodSupplyTracker");
    }
}
