package commands.deadline;

import manager.DeadlineManager;
import ui.TextUi;

public class ViewDeadlineCommand extends commands.Command {
    public static final String COMMAND_WORD = "view_deadlines";

    /**
     * Executes the view deadline command.
     *
     * @param ui manages user input.
     */
    @Override
    public void execute(TextUi ui) {
        DeadlineManager.viewDeadlines(ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
