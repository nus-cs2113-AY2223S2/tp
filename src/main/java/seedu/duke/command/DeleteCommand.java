package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

public class DeleteCommand extends Command{
    public static final String KEYWORD = "delete";
    private int index;

    public DeleteCommand(String[] command, TaskList taskList){
        if (command[1].isEmpty()){
            throw new NullPointerException();
        }
        index = Integer.parseInt(command[1]) - 1;
        if (index + 1  < 1 || index + 1 > taskList.size()){
            throw new IndexOutOfBoundsException();

        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printDeleteTaskNotification(taskList, index);
        taskList.deleteTask(index);
    }
}
