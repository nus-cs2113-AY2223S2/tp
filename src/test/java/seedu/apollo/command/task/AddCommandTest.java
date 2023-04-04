package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.task.InvalidDeadline;
import seedu.apollo.exception.task.InvalidEvent;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.Deadline;
import seedu.apollo.task.Event;
import seedu.apollo.task.Task;
import seedu.apollo.task.TaskList;
import seedu.apollo.task.ToDo;
import seedu.apollo.ui.Ui;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.apollo.task.Task.printPattern;

class AddCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "test1.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = new ModuleList();
    Calendar calendar = new Calendar();

    @Test
    void execute_normalToDo_expectAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("todo", "add junit tests");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        Task result = taskList.get(0);
        assertTrue(result instanceof ToDo);
        assertEquals("add junit tests", result.getDescription());
    }

    @Test
    void execute_normalDeadline_expectAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("deadline", "submit homework -by 2024-01-01T23:59");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        Task result = taskList.get(0);
        assertTrue(result instanceof Deadline);
        assertEquals("submit homework", result.getDescription());
        assertEquals("Jan 01 2024, 11:59PM", ((Deadline) result).getBy(printPattern));
    }

    @Test
    void newAddCommand_invalidDeadline_expectException() {
        assertThrows(InvalidDeadline.class, () -> new AddCommand("deadline", "submit homework"));
    }

    @Test
    void execute_invalidDateTimeDeadline_expectNoAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("deadline", "submit homework -by tomorrow");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertEquals(0, taskList.size());
    }

    @Test
    void execute_overDateTimeDeadline_expectNoAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("deadline", "submit homework -by 2022-01-01T00:00");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertEquals(0, taskList.size());
    }

    @Test
    void execute_normalEvent_expectAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("event",
                "concert -from 2024-01-01T10:00 -to 2024-01-01T13:00");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        Task result = taskList.get(0);
        assertTrue(result instanceof Event);
        assertEquals("concert", result.getDescription());
        assertEquals("Jan 01 2024, 10:00AM", ((Event) result).getFrom(printPattern));
        assertEquals("Jan 01 2024, 01:00PM", ((Event) result).getTo(printPattern));
    }

    @Test
    void newAddCommand_invalidEvent_expectException() {
        assertThrows(InvalidEvent.class, () -> new AddCommand("event", "concert"));
    }

    @Test
    void execute_invalidDateTimeEvent_expectNoAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("event", "concert -from now -to later");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertEquals(0, taskList.size());
    }

    @Test
    void execute_overDateTimeEvent_expectNoAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("event", "concert -from 2022-01-01T23:59 -to 2022-02-01T00:00");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertEquals(0, taskList.size());
    }

    @Test
    void execute_wrongOrderDateTimeEvent_expectNoAdd() throws InvalidDeadline, InvalidEvent, UnexpectedException {
        AddCommand addCommand = new AddCommand("event", "concert -from 2024-12-12T23:59 -to 2024-01-01T00:00");
        addCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertEquals(0, taskList.size());
    }

}
