package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.ByeCommand;

/**
 * Represents a factory for the "bye" command.
 */
public class ByeCommandFactory extends ExecutableCommandFactory {
    public ByeCommand buildCommand(DukeSession dukeSession) {
        return new ByeCommand(dukeSession.getControlFlow());
    }
}
