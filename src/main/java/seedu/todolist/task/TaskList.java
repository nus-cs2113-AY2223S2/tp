package seedu.todolist.task;

import seedu.todolist.exception.InvalidIdException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * A list of Task objects representing the current list of tasks.
 */
public class TaskList {
    private int id = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();

    /**
     * Checks if the provided id is valid, which is when it is from 0 to task list size - 1.
     *
     * @param id The id being checked.
     * @throws InvalidIdException If the provided id is invalid.
     */
    private void validateId(int id) throws InvalidIdException {
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
        validateId(id);
        return tasks.get(id);
    }

    public String addTask(String description, LocalDateTime deadline, String email, TreeSet<String> tags,
                          int repeatDuration, int priority) {
        Task task = new Task(id, description, deadline, email, tags, repeatDuration, priority);
        tasks.put(id++, task);
        return task.toString();
    }

    public void addTask(Task task) {
        tasks.put(id++, task);
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

    public int size(Predicate<Task> p) {
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

    //@@author clement559
    /**
     * Filters the task list using a predicate and comparator before converting it
     * into its sorted string representation.
     *
     * @param p The predicate to sort the task list with.
     * @param c The comparator to sort the task list with.
     * @return Filtered string representation of the task list.
     */
    public String toString(Predicate<Task> p, Comparator<Task> c) {
        return tasks.values().stream().filter(p).sorted(c).map(Task::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public ArrayList<Task> getTaskWithTag(String tag) {
        return  (ArrayList<Task>) tasks.values().stream()
                .filter(t -> t.getTags().contains(tag))
                .collect(toList());
    }

    public ArrayList<Task> getTaskWithPriority(Integer priority) {
        return (ArrayList<Task>) tasks.values().stream()
                .filter(t -> t.getPriority() == priority)
                .collect(toList());
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

    public TreeSet<String> getTags(int id) throws InvalidIdException {
        return getTask(id).getTags();
    }

    public boolean isDone(int id) throws InvalidIdException {
        return getTask(id).isDone();
    }

    public String getFullInfo(int id) throws InvalidIdException {
        return getTask(id).getFullInfo();
    }

    public TreeSet<String> getAllTags() {
        TreeSet<String> tags = new TreeSet<>();
        tasks.values().forEach(task -> tags.addAll(task.getTags()));
        return tags;
    }

    public HashSet<Integer> getAllPrioritiesInTaskList() {
        HashSet<Integer> priorities = new HashSet<>();
        tasks.values().forEach(task -> priorities.add(task.getPriority()));
        return priorities;
    }

    public String setDescription(int id, String description) throws InvalidIdException {
        return getTask(id).setDescription(description);
    }

    public String setEmail(int id, String email) throws InvalidIdException {
        return getTask(id).setEmail(email);
    }

    public String setPriority(int id, int priority) throws InvalidIdException {
        return getTask(id).setPriority(priority);
    }

    public String setDeadline(int id, LocalDateTime deadline) throws InvalidIdException {
        return getTask(id).setDeadline(deadline);
    }

    public String setTags(int id, TreeSet<String> tags) throws InvalidIdException {
        return getTask(id).setTags(tags);
    }

    public String setDone(int id, boolean isDone) throws InvalidIdException {
        return getTask(id).setDone(isDone);
    }

    //@@author clement559
    public String setRepeatDuration(int id, int repeatDuration) throws InvalidIdException {
        return getTask(id).setRepeatDuration(repeatDuration);
    }
    //@@author clement559
    public void checkRepeatingTasks() {
        for (Task task : tasks.values()) {
            int repeatDuration = task.getRepeatDuration();
            LocalDateTime originalDeadline = task.getDeadline();
            if (repeatDuration > 0 && (LocalDateTime.now().isAfter(originalDeadline))) {
                LocalDateTime newDeadline = originalDeadline.plusWeeks(1);
                int newRepeatDuration = repeatDuration - 1;
                addTask(task.getDescription(), newDeadline, task.getEmail(), task.getTags(), newRepeatDuration,
                        task.getPriority());
                task.setRepeatDuration(0);
            }
        }
    }
}
