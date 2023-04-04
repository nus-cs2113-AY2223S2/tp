package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author KN-CY
public class ShortcutDeleteCommand extends ShortcutCommand {
    private static final Logger logger = Logger.getLogger(ShortcutAddCommand.class.getName());

    private static String keyToDelete;
    private static final String SHORTCUT_SUCCESSFULLY_DELETED = "Shortcut successfully deleted.";
    private static final String SHORTCUT_DOES_NOT_EXIST = "The shortcut does not exist.";

    public ShortcutDeleteCommand(String key) {
        this.keyToDelete = key;
        shortcutCommands = userData.getShortcutCommands();
    }

    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ShortcutDeleteCommand.execute()");
        if (shortcutCommands.containsKey(keyToDelete)) {
            shortcutCommands.remove(keyToDelete);
            logger.log(Level.INFO, "Successful ShortcutDeleteCommand.execute()");
            return new CommandResult(SHORTCUT_SUCCESSFULLY_DELETED);
        }
        logger.log(Level.INFO, "ShortcutDeleteCommand.execute() did not delete any shortcuts as given shortcut " +
                "does not exist");

        return new CommandResult(SHORTCUT_DOES_NOT_EXIST);
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ShortcutDeleteCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ShortcutDeleteCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
