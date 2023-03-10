package seedu.apollo.task;

import seedu.apollo.exception.DateOrderException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList class that contains the task list.
 */
public class TaskList {

    // Task list containing all Tasks
    public ArrayList<Task> allTasks;

    /**
     * Initialise allTasks with the given ArrayList.
     *
     * @param allTasks List of Tasks.
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Get the list of all Tasks.
     *
     * @return ArrayList allTasks.
     */
    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    /**
     * Get a shortlisted list of all Tasks that overlap with the given date.
     *
     * @param date The given date to check for.
     * @return ArrayList of all shortlisted tasks.
     */
    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.isOnDate(date)) {
                tasksOnDate.add(task);
            }
        }
        return tasksOnDate;
    }

    /**
     * Get a shortlisted list of all Tasks that contain the given keyword in their description.
     *
     * @param keyword The given keyword to check for.
     * @return ArrayList of all shortlisted tasks.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.description.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Get the number of Tasks currently in the TaskList.
     *
     * @return Number of existing Tasks.
     */
    public int getSize() {
        return allTasks.size();
    }

    /**
     * Sets the status for the Task at given index.
     *
     * @param idx Given index.
     * @param isDone Status the Task should be updated to.
     */
    public void markStatus(int idx, boolean isDone) {
        allTasks.get(idx).setDone(isDone);
    }

    /**
     * Deletes Task at given index.
     *
     * @param idx Given index.
     */
    public void deleteTask(int idx) {
        allTasks.remove(idx);
    }

    /**
     * Adds a ToDo to the TaskList.
     *
     * @param param String describing the Todo.
     */
    public void addToDo(String param) {
        allTasks.add(new ToDo(param));
    }

    /**
     * Adds a Deadline to the TaskList.
     *
     * @param param String describing the Deadline.
     * @param by String describing due date.
     */
    public void addDeadline(String param, String by) {
        allTasks.add(new Deadline(param, by));
    }

    /**
     * Adds an Event to the TaskList.
     *
     * @param param String describing the Event.
     * @param from String describing start date.
     * @param to String describing end date.
     * @throws DateOrderException If the end date occurs before the start date.
     */
    public void addEvent(String param, String from, String to) throws DateOrderException {
        allTasks.add(new Event(param, from, to));
    }

}
