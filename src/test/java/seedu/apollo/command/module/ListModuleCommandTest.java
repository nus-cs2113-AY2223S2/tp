package seedu.apollo.command.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ListModuleCommandTest {

    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Calendar calendar = new Calendar();

    ModuleList moduleList = new ModuleList();

    ListModuleCommandTest() throws FileNotFoundException {
    }

    @Test
    void testExecute_emptyModuleList_expectsNoException() {
        assertDoesNotThrow(() -> new ListModuleCommand()
                .execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

}
