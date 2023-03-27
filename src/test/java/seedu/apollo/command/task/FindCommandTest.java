package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FindCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "test1.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = new ModuleList();
    Calendar calendar = new Calendar();

    @Test
    void testFindExecute_emptyTaskList_expectsNoException() {
        FindCommand newFindCommand = new FindCommand("test");
        assertDoesNotThrow(() -> newFindCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }
}
