package seedu.duck;

import seedu.duck.exception.expiredDateException;
import seedu.duck.exception.IllegalSchoolClassException;
import seedu.duck.exception.IllegalEventException;
import seedu.duck.exception.IllegalDeadlineException;
import seedu.duck.exception.IllegalTodoException;
import seedu.duck.exception.startAfterEndException;
import seedu.duck.task.Deadline;
import seedu.duck.task.Event;
import seedu.duck.task.SchoolClass;
import seedu.duck.task.Task;
import seedu.duck.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Contains operations to manipulate the task list
 */
public class TaskList {
    static void addTask(String line, ArrayList<Task> tasks) {
        if (line.contains("/by")) {
            // Adding a Deadline
            try {
                addDeadline(line, tasks);
                Task.incrementCount();
            } catch (IllegalDeadlineException e) {
                Ui.deadlineErrorMessage();
            } catch (expiredDateException e) {
                Ui.expiredErrorMessage();
            } catch (DateTimeException e) {
                Ui.invalidDateTimeMessage();
            }
        } else if (line.contains("/class")) {
            // Adding a SchoolClass
            try {
                addSchoolClass(line, tasks);
                Task.incrementCount();
            } catch (IllegalSchoolClassException | IndexOutOfBoundsException e) {
                Ui.eventErrorMessage();
            } catch (expiredDateException e) {
                Ui.expiredErrorMessage();
            } catch (startAfterEndException e) {
                Ui.startAfterEndErrorMessage();
            } catch (DateTimeException e) {
                Ui.invalidDateTimeMessage();
            }
        } else if (line.contains("/from") || line.contains("/to")) {
            // Adding an Event
            try {
                addEvent(line, tasks);
                Task.incrementCount();
            } catch (IllegalEventException | IndexOutOfBoundsException e) {
                Ui.eventErrorMessage();
            } catch (expiredDateException e) {
                Ui.expiredErrorMessage();
            } catch (startAfterEndException e) {
                Ui.startAfterEndErrorMessage();
            } catch (DateTimeException e) {
                Ui.invalidDateTimeMessage();
            }
        } else {
            // Adding a _Todo_
            try {
                addTodo(line, tasks);
                Task.incrementCount();
            } catch (IllegalTodoException e) {
                Ui.todoErrorMessage();
            }
        }
    }

    /**
     * Adds a _Todo_ to the list
     *
     * @param line  The line of input from the user
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

    static void setPriority(ArrayList<Task> tasks, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        int taskCount = Task.getTaskCount();
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            Ui.exceedTaskNumberMessage(taskNumber);
        } else {
            tasks.get(taskNumber - 1).setPriority(words[2]);
            // Printing out marked as done message
            Ui.borderLine();
            System.out.println("\t Understood. The task's new priority is:");
            System.out.println("\t " + tasks.get(taskNumber - 1).getPriority());
            Ui.borderLine();
        }
    }

    /**
     * Adds an event to the list
     *
     * @param line  The line of input from the user
     * @param tasks The array list of tasks
     */
    static void addEvent(String line, ArrayList<Task> tasks) throws IllegalEventException, startAfterEndException,
            expiredDateException {
        String description = line.substring(0, line.indexOf("/from")).trim();
        String startString = line.substring(line.indexOf("/from") + 5, line.indexOf("/to")).trim();
        String endString = line.substring(line.indexOf("/to") + 3).trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime start = LocalDateTime.parse(startString, dateFormat);
        LocalDateTime end = LocalDateTime.parse(endString, dateFormat);
        if (start.isAfter(end)) {
            throw new startAfterEndException();
        } else if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
            throw new expiredDateException();
        } else if (description.isBlank() || startString.isBlank() || endString.isBlank()) {
            throw new IllegalEventException();
        } else {
            Event currEvent = new Event(description, startString, endString);
            tasks.add(currEvent);
            Ui.addedTaskMessage(currEvent);
        }
    }

    /**
     * Adds a schoolClass to the list
     *
     * @param line  The line of input from the user
     * @param tasks The array list of tasks
     */
    static void addSchoolClass(String line, ArrayList<Task> tasks) throws IllegalSchoolClassException,
            startAfterEndException, expiredDateException {
        String description = line.substring(0, line.indexOf("/class")).trim();
        String className = line.substring(line.indexOf("/class") + 6, line.indexOf("/from")).trim();
        String startString = line.substring(line.indexOf("/from") + 5, line.indexOf("/to")).trim();
        String endString = line.substring(line.indexOf("/to") + 3).trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime start = LocalDateTime.parse(startString, dateFormat);
        LocalDateTime end = LocalDateTime.parse(endString, dateFormat);
        if (start.isAfter(end)) {
            throw new startAfterEndException();
        } else if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
            throw new expiredDateException();
        } else if (className.isBlank() || startString.isBlank() || endString.isBlank()) {
            throw new IllegalSchoolClassException();
        } else {
            SchoolClass currSchoolClass = new SchoolClass(className, description, startString, endString);
            tasks.add(currSchoolClass);
            Ui.addedTaskMessage(currSchoolClass);
        }
    }

    /**
     * Adds a deadline to the list
     *
     * @param line  The line of input from the user
     * @param tasks The array list of tasks
     */
    static void addDeadline(String line, ArrayList<Task> tasks) throws IllegalDeadlineException, expiredDateException {
        String description = line.substring(0, line.indexOf("/by")).trim();
        String deadlineString = line.substring(line.indexOf("/by") + 3).trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadline = LocalDateTime.parse(deadlineString, dateFormat);
        //System.out.println(description.isBlank());
        if (description.isBlank() || deadlineString.isBlank()) {
            throw new IllegalDeadlineException();
        } else if (deadline.isBefore(LocalDateTime.now())) {
            throw new expiredDateException();
        } else {
            Deadline currDeadline = new Deadline(description, deadlineString);
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

    static void purge(ArrayList<Task> tasks) {
        Ui.borderLine();
        System.out.println("\t Displaying all expired tasks below...");
        System.out.println();
        if (tasks.size() > 0) {
            int expiredCount = 0;
            ArrayList<Task> expiredTasks = new ArrayList<Task>();
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmm");
                    String deadline = ((Deadline) task).getDeadline();
                    Date d = null;
                    Date n = new Date();
                    try {
                        d = format.parse(deadline);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    assert d != null;
                    long diff = d.getTime() - n.getTime();
                    if (diff < 0) {
                        expiredCount++;
                        System.out.println(task);
                        expiredTasks.add(task);
                    }
                } else if (task instanceof Event) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmm");
                    String end = ((Event) task).getEnd();
                    Date d = null;
                    Date n = new Date();
                    try {
                        d = format.parse(end);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    assert d != null;
                    long diff = d.getTime() - n.getTime();
                    if (diff < 0) {
                        expiredCount++;
                        System.out.println(task);
                        expiredTasks.add(task);
                    }
                }

            }
            if (expiredCount > 0) {
                Ui.borderLine();
                System.out.println("\t Quack! A total of " + expiredCount + " tasks have expired!");
                System.out.println("\t Should I remove these tasks from the pending list human?");
                Ui.borderLine();
                if (Ui.doubleCheck()) {
                    for (Task expiredTask : expiredTasks) {
                        tasks.removeIf(task -> task == expiredTask);
                        Task.decrementCount();
                    }
                    Storage.trySave(tasks);
                    Ui.borderLine();
                    System.out.println("\t Expired Tasks have been purged from the list!");
                    System.out.println("\t I love purging things human...");
                    Ui.borderLine();
                } else {
                    Ui.borderLine();
                    System.out.println("\t Quack! Expired tasks have not been purged.");
                    Ui.borderLine();
                }
            } else {
                System.out.println("\t Quack! No tasks have expired!");
                Ui.borderLine();
            }

        }
    }
}
