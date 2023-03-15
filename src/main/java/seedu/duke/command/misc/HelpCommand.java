package seedu.duke.command.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;

/**
 * Represents the "help" command.
 */
public class HelpCommand extends ExecutableCommand {
    public void execute(DukeSession dukeSession) {
        dukeSession.getUi().printHelpMessages();
    }
}
