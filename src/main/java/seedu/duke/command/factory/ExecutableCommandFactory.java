package seedu.duke.command.factory;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.parser.CommandTokens;
import seedu.duke.router.Routable;

/**
 * Represents a factory which can build an <code>ExecutableCommand</code>
 */
public abstract class ExecutableCommandFactory implements Routable {
    public ExecutableCommandFactory resolve(CommandTokens commandTokens) {
        return this;
    }

    public abstract ExecutableCommand buildCommand(DukeSession dukeSession);
}
