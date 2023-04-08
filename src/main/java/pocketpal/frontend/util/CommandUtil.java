package pocketpal.frontend.util;

import pocketpal.data.entry.HelpInput;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidHelpCommandException;

public class CommandUtil {
    public static HelpInput convertStringToCommand(String commandString) throws InvalidHelpCommandException {
        switch (commandString) {
        case "Add":
            return HelpInput.ADD;
        case "Delete":
            return HelpInput.DELETE;
        case "Edit":
            return HelpInput.EDIT;
        case "View":
            return HelpInput.VIEW;
        case "Bye":
            return HelpInput.BYE;
        case "Help":
            return HelpInput.HELP;
        default:
            throw new InvalidHelpCommandException(MessageConstants.MESSAGE_INVALID_HELP_COMMAND);
        }
    }
}
