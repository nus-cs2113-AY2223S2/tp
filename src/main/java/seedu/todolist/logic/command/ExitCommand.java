package seedu.todolist.logic.command;

import seedu.todolist.model.Config;
import seedu.todolist.ui.Ui;
import seedu.todolist.model.TaskList;

/**
 * Command for terminating the program.
 */
public class ExitCommand extends Command {
    /**
     * Displays the program's shutdown message.
     */
    public void execute(TaskList taskList, Config config, Ui ui) {
        ui.printGoodbyeMessage();
    }

    /**
     * Returns whether this command should terminate the program.
     *
     * @return True, since this is the exit command.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
