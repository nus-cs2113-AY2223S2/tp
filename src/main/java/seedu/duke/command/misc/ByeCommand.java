package seedu.duke.command.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;

/**
 * Represents the "bye" command.
 */
public class ByeCommand extends ExecutableCommand {
    public void execute(DukeSession dukeSession) {
        dukeSession.getControlFlow().setQuit(true);
    }
}
