package seedu.apollo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        return (ArrayList<Task>) filteredTasks.collect(Collectors.toList());
    }

    /**
     * Groups tasks with the same type within the TaskList
     */
    public void clusterByType() {
        this.sort(Comparator.comparing(Task::getType));
    }

    /**
     * Determines the order of the events based on their respective from and to dates.
     * Sorts events in natural time order.
     *
     * @param startDay1 The start day of the first event.
     * @param endDay1   The end day of the first event.
     * @param startDay2 The start day of the second event.
     * @param endDay2   The end day of the second event.
     * @return {@code 1} If event2 first, {@code -1} If event1 first, {@code 0} otherwise.
     */
    private int deterministicSortForEvent(LocalDateTime startDay1, LocalDateTime endDay1, LocalDateTime startDay2,
                                          LocalDateTime endDay2) {
        assert startDay1 != null : "Start day of first event should not be null";
        assert endDay1 != null : "End day of first event should not be null";
        assert startDay2 != null : "Start day of second event should not be null";
        assert endDay2 != null : "End day of second event should not be null";
        if (startDay1.equals(startDay2)) {
            //both events have same start time
            if (endDay1.isBefore(endDay2)) {
                // event1 ends first
                return -1;
            } else if (endDay2.isBefore(endDay1)) {
                // event2 ends first
                return 1;
            } else {
                // both events end at the same time
                return 0;
            }

        } else if (startDay2.isBefore(startDay1)) {
            // event2 starts first
            return 1;
        } else {
            // event1 starts first
            return -1;
        }
    }

    /**
     * Determines the order of the deadlines based on their respective due dates.
     *
     * @param deadline1 The deadline of the first deadline task.
     * @param deadline2 The deadline of the second deadline task.
     * @return {@code 1} If deadline2 first, {@code -1} If deadline1 first, {@code 0} otherwise.
     */
    private int deterministicSortForDeadline(LocalDateTime deadline1, LocalDateTime deadline2) {
        assert deadline1 != null : "Deadline should not be null";
        assert deadline2 != null : "Deadline should not be null";
        if (deadline1.isAfter(deadline2)) {
            return 1;
        } else if (deadline1.isBefore(deadline2)) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Sorts the deadline tasks in the TaskList by their respective due dates.
     * @param allTasks The TaskList to be sorted.
     */
    private void sortDeadlineTasks(TaskList allTasks){
        assert allTasks != null : "TaskList should not be null";
        allTasks.sort((Task task1, Task task2) -> {

            if (task1 instanceof Deadline && task2 instanceof Deadline) {

                LocalDateTime deadline1Date = ((Deadline) task1).getByDate();
                LocalDateTime deadline2Date = ((Deadline) task2).getByDate();
                return deterministicSortForDeadline(deadline1Date, deadline2Date);

            } else {
                return 0;
            }

        });
    }

    /**
     * Sorts the event tasks in the TaskList by their respective from and to dates.
     * @param allTasks The TaskList to be sorted.
     */
    private void sortEventTasks(TaskList allTasks){
        assert allTasks != null : "TaskList should not be null";
        allTasks.sort((Task task1, Task task2) -> {
            if (task1 instanceof Event && task2 instanceof Event) {

                LocalDateTime startDay1 = ((Event) task1).getFromDate();
                LocalDateTime endDay1 = ((Event) task1).getToDate();
                LocalDateTime startDay2 = ((Event) task2).getFromDate();
                LocalDateTime endDay2 = ((Event) task2).getToDate();
                return deterministicSortForEvent(startDay1, endDay1, startDay2, endDay2);

            } else {
                return 0;
            }
        });
    }

    //@@author T-Wan-Lin
    /**
     * Sorts the TaskList by date.
     */
    public void sortTaskByDay() {
        this.clusterByType();
        boolean isSortDeadlines = false;
        boolean isSortEvents = false;
        //do nothing for todo type tasks
        for (int i = 0; i < this.size(); i++) {
            if (!isSortDeadlines & this.get(i).getType().equals("deadline")) {
                sortDeadlineTasks(this);
                isSortDeadlines = true;
            } else if (!isSortEvents & this.get(i).getType().equals("event")) {
                sortEventTasks(this);
                isSortEvents = true;
            }
            if (isSortDeadlines & isSortEvents) {
                break;
            }
        }
    }
}
