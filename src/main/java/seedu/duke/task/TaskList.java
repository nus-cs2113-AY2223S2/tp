package seedu.duke.task;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * A list of Task objects representing the current list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return The number of tasks in this task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index of the task list.
     *
     * @param index The index of the task to return.
     *              Must be between 1 and the size of the task list.
     * @return The task at the given index of the task list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Appends the given task to the task list.
     *
     * @param task The task to be added to the task list.
     * @return String representation of the task that was added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    /**
     * Sets the completion status of the task at the given index of the task list.
     *
     * @param index The index of the task whose completion status should be set.
     *              Must be between 1 and the size of the task list.
     * @param isDone Whether the task should be marked as completed.
     * @return String representation of the task whose completion status was set.
     */
    public String setDone(int index, boolean isDone) {
        tasks.get(index).setDone(isDone);
        return tasks.get(index).toString();
    }

    /**
     * Deletes the task at the given index of the task list.
     *
     * @param index The index of the task to be deleted.
     *              Must be between 1 and the size of the task list.
     * @return String representation of the task that was deleted.
     */
    public String deleteTask(int index) {
        String taskString = tasks.get(index).toString();
        tasks.remove(index);
        return taskString;
    }

    /**
     * Converts the task list into its string representation.
     *
     * @return String representation of the task list.
     */
    public String toString() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < size(); i++) {
            taskListString.add((i + 1) + "." + getTask(i).toString());
        }
        return taskListString.toString();
    }
}
