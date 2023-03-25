package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author KN-CY
public class ViewShortcutCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShortcutCommand.class.getName());
    private static HashMap<String, String> shortcutCommands;
    private static final String TABLE_BORDER = "" +
            "+-----------------------------------+-------------------------------------------------------------+\n";

    private static final String ACKNOWLEDGE_VIEW_SHORTCUT_COMMAND = "" +
            "|Here are your shortcuts!                                                                         |\n";
    private static final String HEADERS = "" +
            "+-----------------------------------+-------------------------------------------------------------+\n" +
            "|Shortcut                           |Mapped Command                                               |\n";
    private static final String NO_SHORTCUTS = "You do not have any shortcuts configured.";

    public ViewShortcutCommand() {
        shortcutCommands = RainyDay.userData.getShortcutCommands();
    }


    private static void printBodyRow(String key, String value) {

        String formattedKey = String.format("%s                                   ", key);
        formattedKey = formattedKey.substring(0, 35);
        String formattedValue = String.format("%s                                                             ", value);
        formattedValue = formattedValue.substring(0, 61);
        System.out.println('|' + formattedKey + '|' + formattedValue + '|');
    }
    private static void printCommandsTable() {
        System.out.print(TABLE_BORDER);
        System.out.print(ACKNOWLEDGE_VIEW_SHORTCUT_COMMAND);
        System.out.print(HEADERS);
        for (String key : shortcutCommands.keySet()) {
            String value = shortcutCommands.get(key);
            printBodyRow(key, value);
        }
        System.out.print(TABLE_BORDER);
    }

    @Override
    public CommandResult execute() {
        if (shortcutCommands.size() == 0) {
            return new CommandResult(NO_SHORTCUTS);
        }
        printCommandsTable();
        return null;
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ViewShortcutCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ViewShortcutCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
