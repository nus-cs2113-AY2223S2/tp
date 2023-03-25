package seedu.todolist.task;

import seedu.todolist.exception.InvalidIndexException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringJoiner;

/**
 * A list of Task objects representing the current list of tasks.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks = new ArrayList<>();
    private HashSet<String> tags = new HashSet<>();

    /**
     * Checks if the provided index is valid, which is when it is from 0 to task list size - 1.
     *
     * @param index The index being checked.
     * @throws InvalidIndexException If the provided index is invalid.
     */
    private void validateIndex(int index) throws InvalidIndexException {
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidIndexException(index + 1);
        }
    }

    /**
     * Converts the task list into its string representation.
     *
     * @return String representation of the task list.
     */
    public String toString() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < size(); i++) {
            taskListString.add((i + 1) + ". " + tasks.get(i).toString());
        }
        return taskListString.toString();
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
     * @return The task at the given index of the task list.
     */
    public Task getTask(int index) throws InvalidIndexException {
        validateIndex(index);
        return tasks.get(index);
    }

    public HashSet<String> getTags() {
        return tags;
    }

    /**
     * Appends the given task to the task list.
     *
     * @param task The task to be added to the task list.
     * @return String representation of the task that was added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        if (task.getTags() != null) {
            tags.addAll(task.getTags());
        }
        return task.toString();
    }

    /**
     * Deletes the task at the given index of the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidIndexException If the given index is not within the task list's size.
     */
    public String deleteTask(int index) throws InvalidIndexException {
        validateIndex(index);
        String taskString = tasks.get(index).toString();
        tasks.remove(index);
        return taskString;
    }

    //@@author KedrianLoh
    /**
     * Sorts the ArrayList tasks, by the corresponding Task deadlines
     */
    public void sortByDeadline() {
        tasks.sort(Task.taskDeadlineComparator);
    }

    //@@author clement559
    public void checkRepeatingTasks() {
        int originalListSize = tasks.size();
        for (int i = 0; i < originalListSize; i++) {
            Task task = tasks.get(i);
            int repeatDuration = task.getRepeatDuration();
            LocalDateTime originalDeadline = task.getDeadline();
            if (repeatDuration > 0 && (LocalDateTime.now().isAfter(originalDeadline))) {
                Task repeatTask = new Task(task);
                repeatTask.setDeadline(originalDeadline.plusWeeks(1));
                repeatTask.setRepeatDuration(repeatDuration - 1);
                addTask(repeatTask);
                task.setRepeatDuration(0);
            }
        }
    }

    //@@author ERJUNZE
    /**
     * Adds the given tags to the task at the given index of the task list.
     * No effect if the task already has the given tags.
     *
     * @param index The index of the task that tags are being added to.
     * @param tags The tags being added.
     * @throws InvalidIndexException If the given index is not within the task list's size.
     */
    public void addTags(int index, HashSet<String> tags) throws InvalidIndexException {
        validateIndex(index);
        this.tags.addAll(tags);
        tasks.get(index).addTags(tags);
    }

    /**
     * Deletes the given tags from the task at the given index of the task list.
     * No effect if the task does not have the given tags.
     *
     * @param index The index of the task that tags are being added to.
     * @param tags The tags being added.
     * @throws InvalidIndexException If the given index is not within the task list's size.
     */
    public void deleteTags(int index, HashSet<String> tags) throws InvalidIndexException {
        validateIndex(index);
        this.tags.removeAll(tags);
        tasks.get(index).deleteTags(tags);
    }
}
