//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.constants.HelpMessages;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

/**
 * Command class that will display different help messages.
 */
public class HelpCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_HELP};

    private String helpMessage;

    /**
     * Constructs a HelpCommand object by parsing the provided arguments.
     *
     * @param args The provided arguments, parsed from the user's input.
     */
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

    /**
     * Display the help message specified in the constructor.
     *
     * @param taskList The task list to edit tasks from.
     * @param ui The Ui object used to display the help messages.
     */
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        ui.printHelpList(helpMessage);
    }
}
