package seedu.duck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duck.exception.IllegalDeadlineException;
import seedu.duck.exception.IllegalEventException;
import seedu.duck.exception.IllegalTodoException;
import seedu.duck.exception.expiredDateException;
import seedu.duck.exception.startAfterEndException;
import seedu.duck.task.Deadline;
import seedu.duck.task.Event;
import seedu.duck.task.RecurringDeadline;
import seedu.duck.task.RecurringEvent;
import seedu.duck.task.Task;
import seedu.duck.task.Todo;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void addTodo_correctCommand_correctTypeAdded() throws IllegalTodoException {
        ArrayList<Task> testTasks = new ArrayList<>();
        String line = "/todo todo";
        TaskList.addTodo(line, testTasks);
        assertTrue(testTasks.get(0) instanceof Todo);
    }

    @Test
    public void addTodo_correctCommand_correctDescriptionAdded() throws IllegalTodoException {
        ArrayList<Task> testTasks = new ArrayList<>();
        String line = "/todo todo";
        TaskList.addTodo(line, testTasks);
        assertEquals("todo", testTasks.get(0).getDescription());
    }
    @Test
    public void addDeadline_correctCommand() throws expiredDateException, IllegalDeadlineException {
        ArrayList<Task> testTasks = new ArrayList<>();
        String line = "deadline /by 2023-04-30 1200";
        TaskList.addDeadline(line, testTasks);
        assertTrue(testTasks.get(0) instanceof Deadline && !(testTasks.get(0) instanceof RecurringDeadline));
        assertEquals("deadline", testTasks.get(0).getDescription());
    }

    @Test
    public void addRecurringDeadline_correctCommand() throws IllegalDeadlineException {
        ArrayList<Task> testTasks = new ArrayList<>();
        String line = "/re deadline /by 1200 /day MONDAY";
        TaskList.addRecurringDeadline(line, testTasks);
        assertTrue(testTasks.get(0) instanceof RecurringDeadline);
        assertEquals("deadline", testTasks.get(0).getDescription());
    }

    @Test
    public void addEvent_correctCommand() throws expiredDateException, startAfterEndException,
            IllegalEventException {
        ArrayList<Task> testTasks = new ArrayList<>();
        String line = "event /from 2023-04-30 1200 /to 2023-04-30 1900";
        TaskList.addEvent(line, testTasks);
        assertTrue(testTasks.get(0) instanceof Event && !(testTasks.get(0) instanceof RecurringEvent));
        assertEquals("event", testTasks.get(0).getDescription());
    }

    @Test
    public void addRecurringEvent_correctCommand() throws IllegalEventException {
        ArrayList<Task> testTasks = new ArrayList<>();
        String line = "/re event /from 1200 /to 1900 /day MONDAY";
        TaskList.addRecurringEvent(line, testTasks);
        assertTrue(testTasks.get(0) instanceof RecurringEvent);
        assertEquals("event", testTasks.get(0).getDescription());
    }

    @Test
    public void setPriority_correctCommand() {
        ArrayList<Task> testTasks = new ArrayList<>();
        Todo todo = new Todo("todo");
        testTasks.add(todo);
        Task.incrementCount();
        int firstPri = 2;
        int secondPri = 3;
        String firstTest = "priority 1 2";
        String secondTest = "priority 1 3";
        String[] wordsOne = firstTest.split(" ");
        String[] wordsTwo = secondTest.split(" ");
        TaskList.setPriority(testTasks, wordsOne);
        assertEquals(firstPri, testTasks.get(0).getPriorityIndex());
        TaskList.setPriority(testTasks, wordsTwo);
        assertEquals(secondPri, testTasks.get(0).getPriorityIndex());
    }
}
