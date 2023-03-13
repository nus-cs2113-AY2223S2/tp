package seedu.brokeMan.command;

import seedu.brokeMan.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public void execute() {
        Ui.showToUserWithLineBreak("Exiting program...", "");
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
