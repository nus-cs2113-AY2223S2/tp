package seedu.todolist.model;

import seedu.todolist.constants.Formats;
import seedu.todolist.constants.Messages;
import seedu.todolist.exception.InvalidDateException;
import seedu.todolist.exception.InvalidIdException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A list of Task objects representing the current list of tasks.
 */

public class TaskList {
    private int count = 0;
    private TreeMap<Integer, Task> tasks = new TreeMap<>();

    public void reset() {
        count = 0;
        tasks.clear();
    }

    /**
     * Returns a stream of tasks with the given ids.
     *
     * @param ids The ids of the tasks to return.
     * @return A stream of tasks with the given ids.
     * @throws InvalidIdException If there is no task with any of the provided ids.
     */
    private Stream<Task> getTasks(HashSet<Integer> ids) throws InvalidIdException {
        for (int id : ids) {
            if (!tasks.containsKey(id)) {
                throw new InvalidIdException(id);
            }
        }
        return ids.stream().map(tasks::get);
    }

    private void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public String addTask(String description, LocalDateTime deadline, String email, TreeSet<String> tags,
                          int repeatTimes, Priority priority) {
        Task task = new Task(++count, description, deadline, email, tags, repeatTimes, priority);
        tasks.put(count, task);
        return task.toString();
    }

    /**
     * Deletes the task at the given ids of the task list.
     *
     * @param ids The ids of the task to be deleted.
     * @throws InvalidIdException If there is no task with any of the provided ids.
     */
    public String deleteTask(HashSet<Integer> ids) throws InvalidIdException {
        String taskString = joinStringStream(getTasks(ids).map(Task::toString));
        tasks.keySet().removeAll(ids);
        return taskString;
    }

    public String deleteTask(Predicate<Task> p) {
        String taskString = joinStringStream(tasks.values().stream().filter(p).map(Task::toString));
        tasks.values().removeIf(p);
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
    /**
     * Joins a stream of strings together with numbering (#1, #2, etc...).
     *
     * @param stream The stream of strings to join together.
     * @return A single string composed of numbered strings.
     */
    private String joinStringStream(Stream<String> stream) {
        AtomicInteger count = new AtomicInteger(1);
        return stream.map(string -> String.format(Formats.TASK_STRING_INDEXED, count.getAndIncrement(), string))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Converts the task list into its string representation.
     *
     * @return String representation of the task list.
     */
    public String toString() {
        return joinStringStream(tasks.values().stream().map(Task::toString));
    }

    /**
     * Filters the task list using a predicate before converting it into its string representation.
     *
     * @param p The predicate to filter the task list with.
     * @return Filtered string representation of the task list.
     */
    public String toString(Predicate<Task> p) {
        return joinStringStream(tasks.values().stream().filter(p).map(Task::toString));
    }

    //@@author KedrianLoh
    /**
     * Sorts the task list using a comparator before converting it into its sorted string representation.
     *
     * @param c The comparator to sort the task list with.
     * @return Sorted string representation of the task list.
     */
    public String toString(Comparator<Task> c) {
        return joinStringStream(tasks.values().stream().sorted(c).map(Task::toString));
    }

    /**
     * Filters the task list using a predicate and comparator before converting it
     * into its sorted string representation.
     *
     * @param p The predicate to filter the task list with.
     * @param c The comparator to sort the task list with.
     * @return Filtered string representation of the task list.
     */
    public String toString(Predicate<Task> p, Comparator<Task> c) {
        return joinStringStream(tasks.values().stream().filter(p).sorted(c).map(Task::toString));
    }

    /**
     * Filters the task list using the given ids before converting it into its string representation.
     *
     * @param ids The ids to filter the task list with.
     * @return Filtered string representation of the task list.
     * @throws InvalidIdException If there is no task with any of the given ids.
     */
    public String toString(HashSet<Integer> ids) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(Task::toString));
    }

    /**
     * Filters the task list using the given ids and comparator before converting it
     * into its sorted string representation.
     *
     * @param ids The ids to filter the task list with.
     * @param c The comparator to sort the task list with.
     * @return Filtered string representation of the task list.
     * @throws InvalidIdException If there is no task with any of the given ids.
     */
    public String toString(HashSet<Integer> ids, Comparator<Task> c) throws InvalidIdException {
        return joinStringStream(getTasks(ids).sorted(c).map(Task::toString));
    }

    /**
     * Gets the full information of all tasks with the given ids.
     *
     * @param ids The ids of the tasks to get the full information of.
     * @return A combined string of the full information of all tasks with the given ids.
     * @throws InvalidIdException If there is no task with any of the given ids.
     */
    public String getFullInfo(HashSet<Integer> ids) throws InvalidIdException {
        return getTasks(ids).map(Task::getFullInfo)
                .collect(Collectors.joining(System.lineSeparator() + Messages.LINE + System.lineSeparator()));
    }

    /**
     * Gets the full information of all tasks fulfilling the given predicate.
     *
     * @param p The predicate used to filter the task list.
     * @return A combined string of the full information of all tasks fulfilling the given predicate.
     */
    public String getFullInfo(Predicate<Task> p) {
        return tasks.values().stream().filter(p).map(Task::getFullInfo)
                .collect(Collectors.joining(System.lineSeparator() + Messages.LINE + System.lineSeparator()));
    }

    //@@author ERJUNZE
    public TreeSet<String> getAllTags() {
        TreeSet<String> tags = new TreeSet<>();
        tasks.values().forEach(task -> tags.addAll(task.getTags()));
        return tags;
    }

    public String setDescription(HashSet<Integer> ids, String description) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.setDescription(description)));
    }

    public String setDescription(Predicate<Task> p, String description) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.setDescription(description)));
    }

    public String setTags(HashSet<Integer> ids, TreeSet<String> tags) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.setTags(tags)));
    }

    public String setTags(Predicate<Task> p, TreeSet<String> tags) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.setTags(tags)));
    }

    //@@author RuiShengGit
    public String setEmail(HashSet<Integer> ids, String email) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.setEmail(email)));
    }

    public String setEmail(Predicate<Task> p, String email) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.setEmail(email)));
    }

    public String setPriority(HashSet<Integer> ids, Priority priority) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.setPriority(priority)));
    }

    public String setPriority(Predicate<Task> p, Priority priority) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.setPriority(priority)));
    }

    public String setDone(HashSet<Integer> ids, boolean isDone) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.setDone(isDone)));
    }

    public String setDone(Predicate<Task> p, boolean isDone) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.setDone(isDone)));
    }

    //@@author clement559
    public String setDeadline(HashSet<Integer> ids, LocalDateTime deadline) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.setDeadline(deadline)));
    }

    public String setDeadline(Predicate<Task> p, LocalDateTime deadline) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.setDeadline(deadline)));
    }

    public String setRepeatTimes(HashSet<Integer> ids, int repeatTimes)
            throws InvalidDateException, InvalidIdException {
        // Cannot set the repeat duration of a task if it does not have a deadline
        Task noDeadlineTask = getTasks(ids).filter(task -> task.getDeadline() == null).findFirst().orElse(null);
        if (noDeadlineTask != null) {
            throw new InvalidDateException("Task with ID " + noDeadlineTask.id + " has no deadline.");
        }
        return joinStringStream(getTasks(ids).map(task -> task.setRepeatTimes(repeatTimes)));
    }

    public String setRepeatTimes(Predicate<Task> p, int repeatTimes) throws InvalidDateException {
        // Cannot set the repeat duration of a task if it does not have a deadline
        Task noDeadlineTask = tasks.values().stream().filter(p).
                filter(task -> task.getDeadline() == null).findFirst().orElse(null);
        if (noDeadlineTask != null) {
            throw new InvalidDateException("Task with ID " + noDeadlineTask.id + " has no deadline.");
        }
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.setRepeatTimes(repeatTimes)));
    }

    //@@author jeromeongithub
    public String addTags(HashSet<Integer> ids, TreeSet<String> tags) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.addTags(tags)));
    }

    public String addTags(Predicate<Task> p, TreeSet<String> tags) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.addTags(tags)));
    }

    public String removeTags(HashSet<Integer> ids, TreeSet<String> tags) throws InvalidIdException {
        return joinStringStream(getTasks(ids).map(task -> task.removeTags(tags)));
    }

    public String removeTags(Predicate<Task> p, TreeSet<String> tags) {
        return joinStringStream(tasks.values().stream().filter(p).map(task -> task.removeTags(tags)));
    }

    //@@author clement559
    public void checkRepeatingTasks(Config config) {
        ArrayList<Task> tasksToBeAdded = new ArrayList<>();
        for (Task task : tasks.values()) {
            int repeatTimes = task.getRepeatTimes();
            LocalDateTime deadline = task.getDeadline();
            // Check if this is a recurring task that is past its deadline
            if (repeatTimes == 0 || !task.isDue()) {
                continue;
            }

            // Remove recur duration from this task to avoid duplicates on next check
            task.setRepeatTimes(0);
            while (repeatTimes > 0 && !deadline.isAfter(LocalDateTime.now())) {
                // Calculate new deadline and recur duration
                deadline = deadline.plusDays(config.getRepeatFrequency());
                repeatTimes--;
                // Hold new task in temp array to avoid concurrent modification exception
                tasksToBeAdded.add(new Task(++count, task.getDescription(), deadline,
                        task.getEmail(), task.getTags(), 0, task.getPriority()));
            }
            tasksToBeAdded.get(tasksToBeAdded.size() - 1).setRepeatTimes(repeatTimes);
        }

        for (Task task : tasksToBeAdded) {
            addTask(task);
        }
        config.setLastChecked(LocalDateTime.now());
    }
}
