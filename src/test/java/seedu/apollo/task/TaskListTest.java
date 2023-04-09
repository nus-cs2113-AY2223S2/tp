package seedu.apollo.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.task.DateOrderException;
import seedu.apollo.exception.task.DateOverException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    Deadline deadline = new Deadline("deadline", "05-06-2023-23:59");
    Event event = new Event("event", "04-06-2023-00:00", "06-06-2023-00:00");

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

}
