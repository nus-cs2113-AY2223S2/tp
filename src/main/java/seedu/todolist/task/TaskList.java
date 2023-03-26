package seedu.todolist.task;

import seedu.todolist.exception.InvalidIdException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A list of Task objects representing the current list of tasks.
 */
public class TaskList implements Serializable {
    private int id = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashSet<String> tags = new HashSet<>();

    /**
     * Checks if the provided id is valid, which is when it is from 0 to task list size - 1.
     *
     * @param id The id being checked.
     * @throws InvalidIdException If the provided id is invalid.
     */
    private void validateIndex(int id) throws InvalidIdException {
        if (!tasks.containsKey(id)) {
            throw new InvalidIdException(id);
        }
    }

    /**
     * Returns the task at the given id of the task list.
     *
     * @param id The id of the task to return.
     * @return The task at the given id of the task list.
     */
    private Task getTask(int id) throws InvalidIdException {
        validateIndex(id);
        return tasks.get(id);
    }

    public String addTask(String description, LocalDateTime deadline, String email, HashSet<String> tags,
                          int repeatDuration) {
        Task task = new Task(id, description, deadline, email, tags, repeatDuration);
        tasks.put(id++, task);
        tags.addAll(task.getTags());
        return task.toString();
    }

    /**
     * Deletes the task at the given id of the task list.
     *
     * @param id The id of the task to be deleted.
     * @throws InvalidIdException If there is no task with the given id.
     */
    public String deleteTask(int id) throws InvalidIdException {
        String taskString = getTask(id).toString();
        tasks.remove(id);
        return taskString;
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return The number of tasks in this task list.
     */
    public int size() {
        return tasks.size();
    }

    public int countTasksWithFilter(Predicate<Task> p) {
        return (int) tasks.values().stream().filter(p).count();
    }

    /**
     * Converts the task list into its string representation.
     *
     * @return String representation of the task list.
     */
    public String toString() {
        return tasks.values().stream().map(Task::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Filters the task list using a predicate before converting it into its sorted string representation.
     *
     * @param p The predicate to sort the task list with.
     * @return Filtered string representation of the task list.
     */
    public String toString(Predicate<Task> p) {
        return tasks.values().stream().filter(p).map(Task::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    //@@author KedrianLoh
    /**
     * Sorts the task list using a comparator before converting it into its sorted string representation.
     *
     * @param c The comparator to sort the task list with.
     * @return Sorted string representation of the task list.
     */
    public String toString(Comparator<Task> c) {
        return tasks.values().stream().sorted(c).map(Task::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    //@@author ERJUNZE
    /**
     * Gets the string representation of the task at the given id of the task list.
     *
     * @param id The id of the task whose tags are being set.
     * @return The string representation of the task.
     * @throws InvalidIdException If there is no task with the given id.
     */
    public String getTaskString(int id) throws InvalidIdException {
        return getTask(id).toString();
    }

    public String getDescription(int id) throws InvalidIdException {
        return getTask(id).getDescription();
    }

    public String getEmail(int id) throws InvalidIdException {
        return getTask(id).getEmail();
    }

    public LocalDateTime getDeadline(int id) throws InvalidIdException {
        return getTask(id).getDeadline();
    }

    public HashSet<String> getTags(int id) throws InvalidIdException {
        return getTask(id).getTags();
    }

    public boolean isDone(int id) throws InvalidIdException {
        return getTask(id).isDone();
    }

    public HashSet<String> getAllTags() {
        return tags;
    }

    public String setDescription(int id, String description) throws InvalidIdException {
        return getTask(id).setDescription(description);
    }

    public String setEmail(int id, String email) throws InvalidIdException {
        return getTask(id).setEmail(email);
    }

    public String setDeadline(int id, LocalDateTime deadline) throws InvalidIdException {
        return getTask(id).setDeadline(deadline);
    }

    /**
     * Sets the tags of the task at the given id of task list to the provided tags.
     *
     * @param id The id of the task whose tags are being set.
     * @param tags The tags being set to.
     * @return The string representation of the task.
     * @throws InvalidIdException If there is no task with the given id.
     */
    public String setTags(int id, HashSet<String> tags) throws InvalidIdException {
        return getTask(id).setTags(tags);
    }

    /**
     * Adds the given tags to the task at the given id of the task list.
     * No effect if the task already has the given tags.
     *
     * @param id The id of the task that tags are being added to.
     * @param tags The tags being added.
     * @return The string representation of the task.
     * @throws InvalidIdException If there is no task with the given id.
     */
    public String addTags(int id, HashSet<String> tags) throws InvalidIdException {
        return getTask(id).addTags(tags);
    }

    /**
     * Deletes the given tags from the task at the given id of the task list.
     * No effect if the task does not have the given tags.
     *
     * @param id The id of the task that tags are being added to.
     * @param tags The tags being added.
     * @return The string representation of the task.
     * @throws InvalidIdException If there is no task with the given id.
     */
    public String deleteTags(int id, HashSet<String> tags) throws InvalidIdException {
        return getTask(id).deleteTags(tags);
    }

    public String setDone(int id, boolean isDone) throws InvalidIdException {
        return getTask(id).setDone(isDone);
    }

    //@@author clement559
    public void checkRepeatingTasks() {
        for (Task task : tasks.values()) {
            int repeatDuration = task.getRepeatDuration();
            LocalDateTime originalDeadline = task.getDeadline();
            if (repeatDuration > 0 && (LocalDateTime.now().isAfter(originalDeadline))) {
                LocalDateTime newDeadline = originalDeadline.plusWeeks(1);
                int newRepeatDuration = repeatDuration - 1;
                addTask(task.getDescription(), newDeadline, task.getEmail(), task.getTags(), newRepeatDuration);
                task.setRepeatDuration(0);
            }
        }
    }
}
