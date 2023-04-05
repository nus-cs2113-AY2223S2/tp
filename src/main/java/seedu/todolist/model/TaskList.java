package seedu.todolist.model;

import seedu.todolist.constants.Formats;
import seedu.todolist.exception.InvalidIdException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

/**
 * A list of Task objects representing the current list of tasks.
 */

public class TaskList {
    private int count = 0;
    private HashMap<Integer, Task> tasks = new HashMap<>();

    /**
     * Returns the task at the given id of the task list.
     *
     * @param id The id of the task to return.
     * @return The task at the given id of the task list.
     */
    private Task getTask(int id) throws InvalidIdException {
        if (!tasks.containsKey(id)) {
            throw new InvalidIdException(id);
        }
        return tasks.get(id);
    }

    private void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public String addTask(String description, LocalDateTime deadline, String email, TreeSet<String> tags,
                          int repeatDuration, int priority) {
        Task task = new Task(++count, description, deadline, email, tags, repeatDuration, priority);
        tasks.put(count, task);
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

    public int size(Predicate<Task> p) {
        return (int) tasks.values().stream().filter(p).count();
    }

    //@@author clement559
    private String tasksStreamToString(Stream<Task> stream) {
        AtomicInteger count = new AtomicInteger(1);
        return stream.map(task -> String.format(Formats.TASK_STRING_INDEXED, count.getAndIncrement(), task.toString()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Converts the task list into its string representation.
     *
     * @return String representation of the task list.
     */
    public String toString() {
        return tasksStreamToString(tasks.values().stream());
    }

    /**
     * Filters the task list using a predicate before converting it into its sorted string representation.
     *
     * @param p The predicate to sort the task list with.
     * @return Filtered string representation of the task list.
     */
    public String toString(Predicate<Task> p) {
        return tasksStreamToString(tasks.values().stream().filter(p));
    }

    //@@author KedrianLoh
    /**
     * Sorts the task list using a comparator before converting it into its sorted string representation.
     *
     * @param c The comparator to sort the task list with.
     * @return Sorted string representation of the task list.
     */
    public String toString(Comparator<Task> c) {
        return tasksStreamToString(tasks.values().stream().sorted(c));
    }

    /**
     * Filters the task list using a predicate and comparator before converting it
     * into its sorted string representation.
     *
     * @param p The predicate to sort the task list with.
     * @param c The comparator to sort the task list with.
     * @return Filtered string representation of the task list.
     */
    public String toString(Predicate<Task> p, Comparator<Task> c) {
        return tasksStreamToString(tasks.values().stream().filter(p).sorted(c));
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
        return  getTask(id).toString();
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
        return "ID: " + id + System.lineSeparator() + getTask(id).getFullInfo();
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
    public void checkRepeatingTasks(Config config) {
        ArrayList<Task> tasksToBeAdded = new ArrayList<>();
        for (Task task : tasks.values()) {
            int repeatDuration = task.getRepeatDuration();
            LocalDateTime deadline = task.getDeadline();
            // Check if this is a recurring task that is past its deadline
            if (repeatDuration == 0 || !task.isDue()) {
                continue;
            }

            // Remove recur duration from this task to avoid duplicates on next check
            task.setRepeatDuration(0);
            while (repeatDuration > 0 && !deadline.isAfter(LocalDateTime.now())) {
                // Calculate new deadline and recur duration
                deadline = deadline.plusDays(config.getRepeatFrequency());
                repeatDuration--;
                // Hold new task in temp array to avoid concurrent modification exception
                tasksToBeAdded.add(new Task(++count, task.getDescription(), deadline,
                        task.getEmail(), task.getTags(), 0, task.getPriority()));
            }
            tasksToBeAdded.get(tasksToBeAdded.size() - 1).setRepeatDuration(repeatDuration);
        }

        for (Task task : tasksToBeAdded) {
            addTask(task);
        }
        config.setLastChecked(LocalDateTime.now());
    }
}
