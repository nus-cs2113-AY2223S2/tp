package seedu.todolist.logic.command;

import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

/**
 * Command for terminating the program.
 */
public class ExitCommand extends Command {
    /**
     * Displays the program's shutdown message.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.printGoodbyeMessage();
    }

    /**
     * Returns whether this command should terminate the program.
     *
     * @return <code>True</code>, since this is the exit command.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
