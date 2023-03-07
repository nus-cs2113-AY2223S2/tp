package seedu.duke.command.misc;

import seedu.duke.command.ExecutableCommand;

/**
 * Represents the easter egg "hello world" command.
 */
public class HelloWorldCommand extends ExecutableCommand {
    public void execute() {
        System.out.println("Hello, world!");
    }
}
