package seedu.bigpp.command.commoncommand;

import seedu.bigpp.command.Command;

public class UnrecognizedCommand extends Command {
    @Override
    public String executeCommand() {
        return "Unrecognised command.";
    }
}
