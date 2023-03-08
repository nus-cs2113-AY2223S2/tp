package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.HelloWorldCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the easter egg "hello world" command.
 */
public class HelloWorldCommandFactory extends ExecutableCommandFactory {
    public HelloWorldCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new HelloWorldCommand(arguments.getPositionalArgument());
    }
}
