package seedu.rainyDay.command;


import seedu.rainyDay.RainyDay;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author KN-CY
public class ShortcutAddCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShortcutAddCommand.class.getName());
    private static String key;
    private static String value;
    private static HashMap<String, String> shortcutCommands;
    private static final String SHORTCUT_SUCCESSFULLY_ADDED = "Shortcut successfully added";
    private static final String SHORTCUT_ALREADY_EXISTS = "Shortcut already exists";


    public ShortcutAddCommand(String key, String value) {
        this.key = key;
        this.value = value;
        shortcutCommands = RainyDay.userData.getShortcutCommands();
    }

    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ShortcutAddCommand.execute()");
        if (!shortcutCommands.containsKey(key)) {
            shortcutCommands.put(key, value);
            logger.log(Level.INFO, "Successful ShortcutAddCommand.execute()");
            return new CommandResult(SHORTCUT_SUCCESSFULLY_ADDED);
        }
        logger.log(Level.INFO, "ShortcutAddCommand.execute() did not add any shortcuts as given shortcut already "
                + "exists.");
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
            FileHandler fileHandler = new FileHandler("ShortcutAddCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ShortcutAddCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
