package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class DeleteCommand extends Command{
    private int index;
    public static final String KEYWORD = "delete";

    public DeleteCommand(String[] command){
        index = Integer.parseInt(command[1]) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printDeleteTaskNotification(taskList, index);
        taskList.deleteTask(index);
    }
}
