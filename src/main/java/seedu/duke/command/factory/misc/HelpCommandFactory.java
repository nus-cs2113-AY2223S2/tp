package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.HelpCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the "Help" command.
 */
public class HelpCommandFactory extends ExecutableCommandFactory {
    public HelpCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new HelpCommand();
    }
}
