package commands;

import common.Messages;
import ui.TextUi;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

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

    @Override
    public boolean isExit() {
        return false;
    }
}
