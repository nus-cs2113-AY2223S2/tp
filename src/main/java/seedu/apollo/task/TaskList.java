package seedu.apollo.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList is an ArrayList of Tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Get a shortlisted list of all Tasks that overlap with the given date.
     *
     * @param date The given date to check for.
     * @return ArrayList of all shortlisted tasks.
     */
    public TaskList getTasksOnDate(LocalDate date) {
        TaskList tasksOnDate = new TaskList();
        for (Task task : this) {
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
     * @return TaskList of all shortlisted tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this) {
            if (task.description.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

//    /**
//     * Sets the status for the Task at given index.
//     *
//     * @param idx Given index.
//     * @param isDone Status the Task should be updated to.
//     */
//    public void markStatus(int idx, boolean isDone) {
//        this.get(idx).setDone(isDone);
//    }

//    /**
//     * Deletes Task at given index.
//     *
//     * @param idx Given index.
//     */
//    public void deleteTask(int idx) {
//        this.remove(idx);
//    }

//    /**
//     * Adds a ToDo to the TaskList.
//     *
//     * @param param String describing the Todo.
//     */
//    public void addToDo(String param) {
//        this.add(new ToDo(param));
//    }
//
//    /**
//     * Adds a Deadline to the TaskList.
//     *
//     * @param param String describing the Deadline.
//     * @param by String describing due date.
//     */
//    public void addDeadline(String param, String by) {
//        this.add(new Deadline(param, by));
//    }
//
//    /**
//     * Adds an Event to the TaskList.
//     *
//     * @param param String describing the Event.
//     * @param from String describing start date.
//     * @param to String describing end date.
//     * @throws DateOrderException If the end date occurs before the start date.
//     */
//    public void addEvent(String param, String from, String to) throws DateOrderException {
//        this.add(new Event(param, from, to));
//    }

}
