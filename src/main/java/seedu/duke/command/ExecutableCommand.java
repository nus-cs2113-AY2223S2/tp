package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.DukeSession;

/**
 * Represents a fully parsed command which can be executed.
 */
public abstract class ExecutableCommand {
    public abstract void execute(DukeSession dukeSession) throws DukeException;
}
