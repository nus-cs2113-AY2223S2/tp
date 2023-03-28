package seedu.todolist.logic.command;

import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

//@@author clement559
public class CheckRepeatingTaskCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.checkRepeatingTasks();
        ui.printCheckRepeatingTaskMessage();
    }
}
