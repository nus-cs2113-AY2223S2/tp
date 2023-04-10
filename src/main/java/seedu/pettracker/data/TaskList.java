package seedu.pettracker.data;

import seedu.pettracker.exceptions.EmptyTaskNameException;
import seedu.pettracker.exceptions.InvalidTaskNameException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class TaskList {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static int numberOfTasks;

    public TaskList() {
        numberOfTasks = 0;
    }

    /**
     * Adds a new task to the task list
     *
     * @param todoDescription Description of task to be added
     */

    public static void addTask(String todoDescription, LocalDate time) throws InvalidTaskNameException,
            EmptyTaskNameException {
        if (todoDescription.trim().contains("|")) {
            throw new InvalidTaskNameException();
        }

        if (todoDescription.trim().isEmpty()) {
            throw new EmptyTaskNameException();
        }
        assert todoDescription.length() > 0;
        Task newTask = new Task(todoDescription, time);
        taskList.add(newTask);
        numberOfTasks += 1;
    }

    public static void addTask(String todoDescription) throws InvalidTaskNameException, EmptyTaskNameException {
        if (todoDescription.trim().contains("|")) {
            throw new InvalidTaskNameException();
        }

        if (todoDescription.trim().isEmpty()) {
            throw new EmptyTaskNameException();
        }

        Task newTask = new Task(todoDescription, null);
        taskList.add(newTask);
        numberOfTasks += 1;
    }

    public static void editTask(int taskNumber, String newDescription, LocalDate newTime) throws
            InvalidTaskNameException, EmptyTaskNameException {
        if (newDescription.trim().contains("|")) {
            throw new InvalidTaskNameException();
        }

        if (newDescription.trim().isEmpty()) {
            throw new EmptyTaskNameException();
        }

        Task task = taskList.get(taskNumber - 1);
        task.description = newDescription;
        task.deadline = newTime;
    }

    /**
     * Removes a task from the task list
     *
     * @param taskNumber Number of task to be removed
     */
    public static void removeTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
        numberOfTasks -= 1;
    }

    /**
     * Prints the list of tasks
     */
    public static void listTasks() {
        assert taskList != null;
        for (int i = 0; i < numberOfTasks; i++) {
            String number = Integer.toString(i + 1);
            String icon = taskList.get(i).getStatusIcon();
            String description = taskList.get(i).description;
            System.out.print(number + ". " + icon + " " + description);

            if (taskList.get(i).deadline != null) {
                System.out.println(" (Deadline: " + taskList.get(i).deadline + ")");
            } else {
                System.out.println();
            }
        }
    }

    /**
     * Prints out a list of tasks due on the date the app is accessed.
     */
    public static void taskReminder() {
        System.out.println("Here are your tasks due today:");
        boolean isDueSoon = false;
        for (int i = 0; i < numberOfTasks; i++) {
            LocalDate taskDeadline = taskList.get(i).deadline;
            if (taskDeadline != null && taskDeadline.equals(LocalDate.now())) {
                System.out.println(taskList.get(i).getStatusIcon() + " " + taskList.get(i).description);
                isDueSoon = true;
            }
        }
        if (!isDueSoon) {
            System.out.println("You have no tasks due today (yay)!");
        }
    }

    /**
     * Print a schedule sorted by deadline
     */
    public static void printSchedule() {
        Comparator<Task> deadlineComparator = (task1, task2) -> task1.deadline
                .compareTo(task2.deadline);
        ArrayList<Task> schedule = new ArrayList<>();
        for (Task task : taskList) {
            if (task.deadline != null) {
                schedule.add(task);
            }
        }
        Collections.sort(schedule, deadlineComparator);

        for (int i = 0; i < schedule.size(); i++) {
            if (schedule.get(i).deadline != null) {
                System.out.print((i + 1) + ". " + schedule.get(i).getStatusIcon() + " " + schedule.get(i).description);
                System.out.println(" (Deadline: " + schedule.get(i).deadline + ")");
            }
        }

    }

    /**
     * Marks a task as done or not done
     *
     * @param taskNumber Number of task to be marked as done
     * @param isDone     Boolean value to mark task as done or not done
     */
    public static void markTask(int taskNumber, boolean isDone) {
        taskList.get(taskNumber - 1).isDone = isDone;
    }

    /**
     * Saves the current TaskList to the output file
     *
     * @param storage Storage to save file to
     * @param ui      Ui for any prints
     */
    public static void saveTasksToStorage(Storage storage, Ui ui) {
        storage.saveTasks(taskList, ui);
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static String getSpecificTaskDescription(int taskNumber) {
        return taskList.get(taskNumber - 1).description;
    }

    public static boolean getTaskStatus(int taskNumber) {
        return taskList.get(taskNumber - 1).isDone;
    }
}
