package commands;

import common.Messages;
import ui.TextUi;

public class IncorrectCommand extends Command {
    /**
     * Executes the command the user provided.
     * Since it is not a valid command, an error message is printed instead.
     */
    @Override
    public void execute(TextUi ui) {
        ui.printMessage(Messages.ERROR_COMMAND_INVALID);
    }

    /**
     * Check if the program is exiting.
     *
     * @return the exit value.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
