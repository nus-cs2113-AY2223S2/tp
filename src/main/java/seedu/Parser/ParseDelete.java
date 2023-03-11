package seedu.Parser;

import seedu.commands.Command;
import seedu.commands.DeleteCommand;
import seedu.commands.InvalidCommand;

public class ParseDelete {
    public static Command deleteItem(String line) {
        try {
            int pos = line.indexOf(" ");
            String details = line.substring(pos+1);
            int posToDelete = Integer.parseInt(details);
            return new DeleteCommand(posToDelete);
        } catch (NumberFormatException numberFormatException) {
            // posToDelete is not a proper int
            return new InvalidCommand("Invalid");
        }
    }
}
