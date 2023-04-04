package seedu.rainyDay.command;


import seedu.rainyDay.RainyDay;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author KN-CY
public class ShortcutAddCommand extends ShortcutCommand {
    private static final Logger logger = Logger.getLogger(ShortcutAddCommand.class.getName());
    private static String key;
    private static String value;
    private static final String SHORTCUT_SUCCESSFULLY_ADDED = "Shortcut successfully added";

    public ShortcutAddCommand(String key, String value) {
        this.key = key;
        this.value = value;
        shortcutCommands = userData.getShortcutCommands();
    }

    @Override
    public CommandResult execute() throws RainyDayException {
        setupLogger();
        logger.log(Level.INFO, "starting ShortcutAddCommand.execute()");
        logger.log(Level.INFO, "checking for validity of shortcut");
        checkShortcutValidity(shortcutCommands, key, value);
        logger.log(Level.INFO, "shortcut confirmed as valid of shortcut");

        shortcutCommands.put(key, value);
        logger.log(Level.INFO, "Successful ShortcutAddCommand.execute()");
        return new CommandResult(SHORTCUT_SUCCESSFULLY_ADDED);

    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ShortcutAddCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ShortcutAddCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
