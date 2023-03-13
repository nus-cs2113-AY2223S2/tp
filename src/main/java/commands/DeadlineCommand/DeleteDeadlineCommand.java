package commands.DeadlineCommand;

import dinerDeadline.DeadlineList;
import ui.TextUi;

public class DeleteDeadlineCommand extends commands.Command {
    public static final String COMMAND_WORD = "delete_deadline";
    private int index_to_delete;

    /**
     * Creates a delete deadline command that deletes the deadline item of the index being passed through.
     * @param index the index of the deadline item to delete.
     */
    public DeleteDeadlineCommand(int index) {
        this.index_to_delete = index;
    }

    /**
     * Executes the delete deadline command.
     * @param deadlineList managed the list of deadlines.
     * @param ui manages user input.
     */
    @Override
    public void execute(DeadlineList deadlineList, TextUi ui) {
        deadlineList.deleteDeadline(this.index_to_delete, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
