package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.task.InvalidDateTime;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "test1.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = new ModuleList();
    Calendar calendar = new Calendar();

    @Test
    void testDateCommandExecute_emptyList_expectsNoException() throws InvalidDateTime {
        DateCommand newDateCommand = new DateCommand("2020-10-10");
        assertDoesNotThrow(() -> newDateCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testDateCommand_invalidDateString_expectsInvalidDateTimeException() {
        assertThrows(InvalidDateTime.class, () -> new DateCommand("2020-10-10-11"));
    }
}
