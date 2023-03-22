package seedu.apollo.task;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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

    /**
     * Sorts the TaskList by type.
     */
    public void clusterByType() {
        this.sort(Comparator.comparing(Task::getType));
    }

    //first sort by type, then sort by date
    //takes in a parameter of taskList


    private int deterministicSortForEvent(Date startDay1, Date endDay1, Date startDay2, Date endDay2) {
        //both events have same start time
        if (startDay1.equals(startDay2)) {
            //if event2 ends first
            if (endDay2.after(endDay1)){
                return 1;
                //event1 ends first
            } else if (endDay1.before(endDay2)) {
                return -1;
                //both events end at the same time
            } else {
                return 0;
            }

        } else if (startDay1.after(startDay2)){
            return 1;
        } else {
            return -1;
        }
    }
    private void sortTaskByDay(TaskList allTasks) {
        allTasks.clusterByType();

        for (Task task : allTasks){
            if (task.getType().equals("deadline")){
                allTasks.sort((Deadline deadline1, Deadline deadline2)-> {
                    SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy, hh:mma");
                    try{
                        Date deadline1Date = format.parse(deadline1.getBy(format));
                        Date deadline2Date = format.parse(deadline2.getBy(format));
                        return deterministicSortForDeadline(deadline1Date, deadline2Date);
                    } catch (ParseException e){
                        return 0;
                    }
                });
            }
            else if (task.getType().equals("event")) {
                // Insert code here for sorting events
                allTasks.sort((Event event1, Event event2)-> {
                    SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy, hh:mma");
                    try{
                        Date startDay1 = format.parse(event1.getFromDate(format));
                        Date endDay1 = format.parse(event1.getTo(format));
                        Date startDay2 = format.parse(event2.getFrom(format));
                        Date endDay2 = format.parse(event2.getTo(format));
                        return deterministicSortForEvent(startDay1, endDay1, startDay2, endDay2);
                    } catch (ParseException e){
                        return 0;
                    }
                });

            }
        }
    }

    private int deterministicSortForDeadline(Date deadline1, Date deadline2) {
        if (deadline1.after(deadline2)) {
            return 1;
        } else if (deadline1.before(deadline2)) {
            return -1;
        } else {
            return 0;
        }
    }

}
