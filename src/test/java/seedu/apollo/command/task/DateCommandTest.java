package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.task.DateOverException;
import seedu.apollo.exception.task.InvalidDateTime;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.Deadline;
import seedu.apollo.task.TaskList;

import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class DateCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();

    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = storage.loadModuleData();
    Calendar calendar = new Calendar();

    DateCommandTest() throws FileNotFoundException {
    }

    @Test
    void testExecute_emptyTaskList_expectsNoException() throws InvalidDateTime {
        DateCommand newDateCommand = new DateCommand("10-10-2023");

        assertDoesNotThrow(() -> newDateCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test

    void testExecute_populatedTaskList_expectsNoException() throws InvalidDateTime, DateOverException {
        DateCommand newDateCommand = new DateCommand("10-10-2023");
        taskList.add(new Deadline("submit tutorial", "10-10-2023-23:59"));
        assertDoesNotThrow(() -> newDateCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

}

