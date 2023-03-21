package seedu.pettracker.data;

import java.util.ArrayList;
import java.util.logging.Logger;

public class TaskList {
    private static final Logger logger = Logger.getLogger("TaskListLogger");
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int numberOfTasks;

    public TaskList() {
        numberOfTasks = 0;
    }

    /**
     * Adds a new task to the todo list
     *
     * @param todoDescription Description of task to be added
     */
    public static void addTask(String todoDescription) {
        Task newTask = new Task(todoDescription);
        taskList.add(newTask);
        numberOfTasks += 1;
    }
}
