package commands.DeadlineCommand;

import dinerDeadline.DeadlineList;
import ui.TextUi;

public class ViewDeadlineCommand extends commands.Command {
    public static final String COMMAND_WORD = "view_deadline";

    /**
     * Executes the view deadline command.
     * @param deadlineList managed the list of deadlines.
     * @param ui manages user input.
     */
    @Override
    public void execute(DeadlineList deadlineList, TextUi ui) {
        deadlineList.printDeadlines(ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
