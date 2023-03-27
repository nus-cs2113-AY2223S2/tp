package seedu.apollo.command.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddModuleCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = storage.loadModuleData();
    Calendar calendar = new Calendar();

    AddModuleCommandTest() throws FileNotFoundException {
    }

    @Test
    void testAddModuleCommand_invalidParams_expectsIllegalCommandException() throws
            FileNotFoundException {
        assertThrows(IllegalCommandException.class, () -> new AddModuleCommand("addmod modules", allModules));
    }

    @Test
    void testAddModuleCommand_validParams_expectsNoException() throws FileNotFoundException{
        assertDoesNotThrow(() -> new AddModuleCommand("CS2113", allModules));
    }

}
