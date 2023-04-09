package commands;

import common.Messages;
import ui.TextUi;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Executes the command the user provided.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    @Override
    public void execute(TextUi ui) {
        String helpList = String.format(Messages.MESSAGE_VALID_COMMAND_LIST,
                Messages.MESSAGE_GENERIC_COMMANDS,
                Messages.MESSAGE_MEETING_COMMANDS,
                Messages.MESSAGE_DEADLINE_COMMANDS,
                Messages.MESSAGE_RECIPE_COMMANDS,
                Messages.MESSAGE_STAFF_COMMANDS);
        ui.printMessage(helpList);
    }

    /**
     * Returns the exit value.
     * If false, program continues to run.
     *
     * @return the exit value.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
