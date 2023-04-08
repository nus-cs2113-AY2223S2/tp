//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.constants.HelpMessages;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class HelpCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_HELP};

    private String helpMessage;

    public HelpCommand(HashMap<Flags, String> args) {
        switch(args.get(Flags.COMMAND_HELP)) {
        case "filter":
            helpMessage = HelpMessages.HELP_FILTERS;
            return;
        case "sort":
            helpMessage = HelpMessages.HELP_SORT;
            return;
        default:
            helpMessage = HelpMessages.HELP_COMMAND;
        }
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        ui.printHelpList(helpMessage);
    }
}
