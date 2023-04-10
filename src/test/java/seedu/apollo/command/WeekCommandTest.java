package seedu.apollo.command;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.utils.WeekCommand;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WeekCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = storage.loadModuleData();
    Calendar calendar = new Calendar();

    WeekCommandTest() throws FileNotFoundException {
    }

    @Test
    void testExecute_emptySchedule_expectsNoException() {
        WeekCommand newWeekCommand = new WeekCommand();
        assertDoesNotThrow(() -> newWeekCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }
}
