package seedu.rainyDay.command;


import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author KN-CY
public class ShortcutCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShortcutCommand.class.getName());
    private static String key; // for now lets limit key to be a single word with no space.
    private static String value;
    private static HashMap<String, String> shortcutCommands;
    private static final String SHORTCUT_SUCCESSFULLY_ADDED = "Shortcut successfully added";
    private static final String SHORTCUT_ALREADY_EXISTS = "Shortcut already exists";


    public ShortcutCommand(String key, String value) {
        this.key = key;
        this.value = value;
        shortcutCommands = RainyDay.userData.getShortcutCommands();
    }

    @Override
    public CommandResult execute() {
        if (!shortcutCommands.containsKey(key)) {
            shortcutCommands.put(key, value);
            Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
            return new CommandResult(SHORTCUT_SUCCESSFULLY_ADDED);
        }
        return new CommandResult(SHORTCUT_ALREADY_EXISTS);
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ShortcutCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ShortcutCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
