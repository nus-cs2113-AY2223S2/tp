package seedu.rainyDay.command;


import seedu.rainyDay.RainyDay;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import seedu.rainyDay.exceptions.ErrorMessage;


//@@author KN-CY
public class ShortcutAddCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShortcutAddCommand.class.getName());
    private static String key;
    private static String value;
    private static HashMap<String, String> shortcutCommands;
    private static final String SHORTCUT_SUCCESSFULLY_ADDED = "Shortcut successfully added";


    public ShortcutAddCommand(String key, String value) {
        this.key = key;
        this.value = value;
        shortcutCommands = RainyDay.userData.getShortcutCommands();
    }

    private void checkShortcutValidity() throws RainyDayException {
        if (shortcutCommands.containsKey(key)) {
            logger.log(Level.INFO, "ShortcutAddCommand.execute() did not add any shortcuts as given shortcut already "
                    + "exists.");
            throw new RainyDayException(ErrorMessage.SHORTCUT_ALREADY_EXISTS.toString());
        }
        if (key.equals(value)) {
            logger.log(Level.INFO, "ShortcutAddCommand.execute() did not add any shortcuts as a shortcut cannot be " +
                    "mapped to the same string.");
            throw new RainyDayException(ErrorMessage.SHORTCUT_MAPS_ITSELF.toString());
        }
        String keyFirstWord = key.split(" ", 2)[0];
        if (Command.isValidCommand(keyFirstWord)) {
            logger.log(Level.INFO, "ShortcutAddCommand.execute() did not add any shortcuts as a shortcut cannot be " +
                    "named as an actual command");
            throw new RainyDayException(ErrorMessage.SHORTCUT_NAME_VALID_COMMAND.toString());

        }

    }

    @Override
    public CommandResult execute() throws RainyDayException {
        setupLogger();
        logger.log(Level.INFO, "starting ShortcutAddCommand.execute()");
        checkShortcutValidity();

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
