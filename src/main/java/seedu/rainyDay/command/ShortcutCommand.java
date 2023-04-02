package seedu.rainyDay.command;

import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.HashMap;

public abstract class ShortcutCommand extends Command {
    protected static HashMap<String, String> shortcutCommands;

    public static void checkShortcutValidity(HashMap<String, String> shortcutCommands, String key, String value)
            throws RainyDayException {
        if (shortcutCommands.containsKey(key)) {
            throw new RainyDayException(ErrorMessage.SHORTCUT_ALREADY_EXISTS.toString());
        }
        if (key.equals(value)) {
            throw new RainyDayException(ErrorMessage.SHORTCUT_MAPS_ITSELF.toString());
        }
        if (shortcutCommands.containsKey(value) || shortcutCommands.containsValue(key)) {
            throw new RainyDayException(ErrorMessage.SHORTCUT_MAPS_SHORTCUT.toString());
        }
        String keyFirstWord = key.split(" ", 2)[0];
        if (Command.isValidCommand(keyFirstWord)) {
            throw new RainyDayException(ErrorMessage.SHORTCUT_NAME_VALID_COMMAND.toString());
        }
    }
}
