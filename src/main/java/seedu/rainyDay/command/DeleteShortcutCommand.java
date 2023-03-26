package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author KN-CY
public class DeleteShortcutCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShortcutCommand.class.getName());

    private static String keyToDelete;
    private static HashMap<String, String> shortcutCommands;
    private static final String SHORTCUT_SUCCESSFULLY_DELETED = "Shortcut successfully deleted.";
    private static final String SHORTCUT_DOES_NOT_EXIST = "The shortcut does not exist.";


    public DeleteShortcutCommand(String key) {
        this.keyToDelete = key;
        shortcutCommands = RainyDay.userData.getShortcutCommands();
    }

    @Override
    public CommandResult execute() {
        if (shortcutCommands.containsKey(keyToDelete)) {
            shortcutCommands.remove(keyToDelete);
            Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
            return new CommandResult(SHORTCUT_SUCCESSFULLY_DELETED);
        }
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
            FileHandler fileHandler = new FileHandler("DeleteShortcutCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log DeleteShortcutCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
