package seedu.apollo.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.task.DateOrderException;
import seedu.apollo.exception.task.DateOverException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    Deadline deadline = new Deadline("deadline", "05-06-2023-23:59");
    Deadline deadline2 = new Deadline("deadline2", "05-06-2023-23:59");
    Deadline deadlineA = new Deadline("deadlineA", "06-06-2023-23:59");
    Deadline deadlineB = new Deadline("deadlineB", "07-06-2023-23:59");
    Event event = new Event("event", "04-06-2023-00:00", "06-06-2023-00:00");
    Event event1 = new Event("event1", "04-06-2023-00:00", "06-06-2023-00:00");
    Event eventS = new Event("eventS", "03-06-2023-00:00", "06-06-2023-00:00");
    Event eventA = new Event("eventA", "05-06-2023-00:00", "06-06-2023-00:00");
    Event eventB = new Event("eventB", "05-06-2023-00:00", "06-06-2023-00:01");
    Event eventC = new Event("eventC", "05-06-2023-00:00", "06-06-2023-00:02");
    ToDo toDo = new ToDo("todo");
    ToDo toDoA = new ToDo("todoA");
    ToDo toDoB = new ToDo("todoB");

    TaskListTest() throws DateOverException, DateOrderException {
    }

    @Test
    void getTasksOnDate_noTasks_expectNone() {
        TaskList emptyList = new TaskList();
        LocalDate date = LocalDate.parse("2023-06-05");
        assertEquals(0, emptyList.getTasksOnDate(date).size());
    }

    @Test
    void getTasksOnDate_tasksOnDate_expectTasks() {
        TaskList taskList = new TaskList();
        taskList.add(deadline);
        taskList.add(event);
        LocalDate noneDate = LocalDate.parse("2023-09-05");
        assertEquals(0, taskList.getTasksOnDate(noneDate).size());
        LocalDate haveDate = LocalDate.parse("2023-06-05");
        assertEquals(2, taskList.getTasksOnDate(haveDate).size());
    }

    @Test
    void findTasks_noTasks_expectNone() {
        TaskList emptyList = new TaskList();
        assertEquals(0, emptyList.findTasks("e").size());
    }

    @Test
    void findTasks_tasksWithKeyword_expectTasks() {
        TaskList taskList = new TaskList();
        taskList.add(deadline);
        taskList.add(event);
        assertEquals(0, taskList.findTasks("hello").size());
        assertEquals(1, taskList.findTasks("dead").size());
        assertEquals(2, taskList.findTasks("e").size());
    }

    @Test
    void sortTaskByDay_unorderedDeadlines_expectSort() {
        TaskList taskList = new TaskList();
        taskList.add(deadlineB);
        taskList.add(deadlineA);
        taskList.add(deadline);
        taskList.add(deadline2);
        taskList.sortTaskByDay();
        assertEquals(deadline, taskList.get(0));
        assertEquals(deadlineB, taskList.get(3));
    }

    @Test
    void sortTaskByDay_unorderedEvents_expectSort() {
        TaskList taskList = new TaskList();
        taskList.add(eventC);
        taskList.add(eventA);
        taskList.add(eventB);
        taskList.add(eventS);
        taskList.add(event);
        taskList.add(event1);
        taskList.sortTaskByDay();
        assertEquals(eventS, taskList.get(0));
        assertEquals(event1, taskList.get(2));
        assertEquals(eventC, taskList.get(5));
    }

    @Test
    void sortTaskByDay_unorderedTasks_expectSort() {
        TaskList taskList = new TaskList();
        taskList.add(deadlineA);
        taskList.add(eventA);
        taskList.add(toDo);
        taskList.add(eventB);
        taskList.add(toDoB);
        taskList.add(deadlineB);
        taskList.add(deadline);
        taskList.add(toDoA);
        taskList.add(event);
        taskList.sortTaskByDay();
        assertEquals(deadline, taskList.get(0));
        assertEquals(event, taskList.get(3));
        assertEquals(toDoB, taskList.get(7));
    }

}
