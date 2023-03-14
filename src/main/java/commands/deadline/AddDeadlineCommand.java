package commands.deadline;

import entity.Deadline;
import manager.DeadlineManager;
import ui.TextUi;

public class AddDeadlineCommand extends commands.Command {
    public static final String COMMAND_WORD = "add_deadline";
    private Deadline deadline;

    /**
     * Creates an add deadline command that adds the deadline item being passed through.
     *
     * @param deadline the deadline to add to the list.
     */
    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the add deadline command.
     *
     * @param ui manages user input.
     */
    @Override
    public void execute(TextUi ui) {
        DeadlineManager.addDeadline(this.deadline, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
