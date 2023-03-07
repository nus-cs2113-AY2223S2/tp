package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.HelloWorldCommand;

/**
 * Represents a factory for the easter egg "hello world" command.
 */
public class HelloWorldCommandFactory extends ExecutableCommandFactory {
    public HelloWorldCommand buildCommand(DukeSession dukeSession) {
        return new HelloWorldCommand();
    }
}
