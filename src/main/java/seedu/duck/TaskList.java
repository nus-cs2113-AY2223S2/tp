package seedu.duck;

import seedu.duck.exception.IllegalDeadlineException;
import seedu.duck.exception.IllegalEventException;
import seedu.duck.exception.IllegalTodoException;
import seedu.duck.task.Deadline;
import seedu.duck.task.Event;
import seedu.duck.task.Task;
import seedu.duck.task.Todo;

import java.util.ArrayList;

/**
 * Contains operations to manipulate the task list
 */
public class TaskList {
    static void addTask(String line, ArrayList<Task> tasks) {
        if (line.contains("/by")) {
            // Adding a Deadline
            try {
                addDeadline(line, tasks);
            } catch (IllegalDeadlineException e) {
                Ui.deadlineErrorMessage();
            }
        } else if (line.contains("/from") || line.contains("/to")) {
            // Adding an Event
            try {
                addEvent(line, tasks);
            } catch (IllegalEventException e) {
                Ui.eventErrorMessage();
            } catch (IndexOutOfBoundsException e) {
                Ui.eventErrorMessage();
            }
        } else {
            // Adding a _Todo_
            try {
                addTodo(line, tasks);
            } catch (IllegalTodoException e) {
                Ui.todoErrorMessage();
            }
        }
    }

    /**
     * Adds a _Todo_ to the list
     *
     * @param line The line of input from the user
     * @param tasks The array list of tasks
     */
    static void addTodo(String line, ArrayList<Task> tasks) throws IllegalTodoException {
        if (line.isBlank()) {
            throw new IllegalTodoException();
        } else {
            Todo currTodo = new Todo(line);
            tasks.add(currTodo);
            Ui.addedTaskMessage(currTodo);
        }
    }

    /**
     * Adds an event to the list
     *
     * @param line The line of input from the user
     * @param tasks The array list of tasks
     */
    static void addEvent(String line, ArrayList<Task> tasks) throws IllegalEventException {
        String description = line.substring(0, line.indexOf("/from")).trim();
        String start = line.substring(line.indexOf("/from") + 5, line.indexOf("/to")).trim();
        String end = line.substring(line.indexOf("/to") + 3).trim();
        if (description.isBlank() || start.isBlank() || end.isBlank()) {
            throw new IllegalEventException();
        } else {
            Event currEvent = new Event(description, start, end);
            tasks.add(currEvent);
            Ui.addedTaskMessage(currEvent);
        }
    }

    /**
     * Adds a deadline to the list
     *
     * @param line The line of input from the user
     * @param tasks The array list of tasks
     */
    static void addDeadline(String line, ArrayList<Task> tasks) throws IllegalDeadlineException {
        String description = line.substring(0, line.indexOf("/by")).trim();
        String deadline = line.substring(line.indexOf("/by") + 3).trim();
        //System.out.println(description.isBlank());
        if (description.isBlank() || deadline.isBlank()) {
            throw new IllegalDeadlineException();
        } else {
            Deadline currDeadline = new Deadline(description, deadline);
            tasks.add(currDeadline);
            Ui.addedTaskMessage(currDeadline);
        }
    }

    /**
     * Marks a task as done
     *
     * @param tasks The array list of tasks
     * @param words The array of words generated from the user input
     */
    static void markTask(ArrayList<Task> tasks, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        int taskCount = Task.getTaskCount();
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            Ui.exceedTaskNumberMessage(taskNumber);
        } else {
            tasks.get(taskNumber - 1).markAsDone();
            // Printing out marked as done message
            Ui.borderLine();
            System.out.println("\t Understood. I've marked this task as done:");
            System.out.println("\t " + tasks.get(taskNumber - 1));
            Ui.borderLine();
        }
    }

    /**
     * Marks a task as not done
     *
     * @param tasks The array list of tasks
     * @param words The array of words generated from the user input
     */
    static void unmarkTask(ArrayList<Task> tasks, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        int taskCount = Task.getTaskCount();
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            Ui.exceedTaskNumberMessage(taskNumber);
        } else {
            tasks.get(taskNumber - 1).markAsNotDone();
            // Printing out marked as not done message
            Ui.borderLine();
            System.out.println("\t Understood. I've marked this task as not done yet:");
            System.out.println("\t " + tasks.get(taskNumber - 1));
            Ui.borderLine();
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param tasks The array list of tasks
     * @param words The array of words generated from the user input
     */
    static void deleteTask(ArrayList<Task> tasks, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        int taskCount = Task.getTaskCount();
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            Ui.exceedTaskNumberMessage(taskNumber);
        } else {
            Task taskToDelete = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            Task.decrementCount();
            Ui.deleteTaskMessage(taskToDelete);
        }
    }
}