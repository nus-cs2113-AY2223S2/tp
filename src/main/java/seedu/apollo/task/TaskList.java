package seedu.apollo.task;

import seedu.apollo.module.Module;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TaskList class is a modified ArrayList of Tasks.
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
    public ArrayList<Task> findTasks(String keyword) {

        String lowerKeyword = keyword.toLowerCase();
        Stream<Task> filteredTasks = this.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerKeyword));

        ArrayList<Task> filteredTasksList = (ArrayList<Task>) filteredTasks.collect(Collectors.toList());
        return filteredTasksList;
    }

    public void clusterByType() {
        this.sort(Comparator.comparing(Task::getType));
    }

}
