package seedu.duke.command.misc;

import seedu.duke.command.ExecutableCommand;

/**
 * Represents the easter egg "hello world" command.
 */
public class HelloWorldCommand extends ExecutableCommand {
    String name;

    public HelloWorldCommand(String name) {
        this.name = name;
    }

    public void execute() {
        if (this.name == null) {
            System.out.println("Hello, world!");
            return;
        }
        System.out.println(String.format("Hello, %s!", this.name));
    }
}
