package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class AddTaskCommand extends Command{
    private String description;
    public static final String KEYWORD = "add";

    public AddTaskCommand(String[] command){
        description = command[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui){
        Task task = new Task(description);
        taskList.addTask(task);
        ui.printAddTaskNotification(task);
    }
}
