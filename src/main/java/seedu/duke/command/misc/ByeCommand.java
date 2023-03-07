package seedu.duke.command.misc;

import seedu.duke.DukeControlFlow;
import seedu.duke.command.ExecutableCommand;

/**
 * Represents the "bye" command.
 */
public class ByeCommand extends ExecutableCommand {
    private final DukeControlFlow controlFlow;

    public ByeCommand(DukeControlFlow controlFlow) {
        this.controlFlow = controlFlow;
    }

    public void execute() {
        this.controlFlow.setQuit(true);
    }
}
