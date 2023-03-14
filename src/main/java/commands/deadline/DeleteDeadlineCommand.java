package commands.deadline;

import manager.DeadlineManager;
import ui.TextUi;

public class DeleteDeadlineCommand extends commands.Command {
    public static final String COMMAND_WORD = "delete_deadline";
    private int indexToDelete;

    /**
     * Creates a delete deadline command that deletes the deadline item of the index being passed through.
     *
     * @param index the index of the deadline item to delete.
     */
    public DeleteDeadlineCommand(int index) {
        this.indexToDelete = index;
    }

    /**
     * Executes the delete deadline command.
     *
     * @param ui manages user input.
     */
    @Override
    public void execute(TextUi ui) {
        DeadlineManager.deleteDeadline(this.indexToDelete, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
