package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.RemoveCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the "remove" command.
 */
public class RemoveCommandFactory extends ExecutableCommandFactory {
    public RemoveCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new RemoveCommand(arguments.getPositionalArgument(), arguments.getFlagArgument("qty"));
    }
}
