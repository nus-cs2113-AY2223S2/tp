package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.AddCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the "add" command.
 */
public class AddCommandFactory extends ExecutableCommandFactory {
    public AddCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new AddCommand(arguments.getPositionalArgument(), arguments.getFlagArgument("qty"));
    }
}
