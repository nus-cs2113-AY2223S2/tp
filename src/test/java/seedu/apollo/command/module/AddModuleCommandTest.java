package seedu.apollo.command.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.module.InvalidModule;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddModuleCommandTest {

    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();
    ModuleList moduleList = new ModuleList();
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Calendar calendar = new Calendar();

    AddModuleCommandTest() throws FileNotFoundException {
    }

    @Test
    void testAddModuleCommand_invalidParams_expectsIllegalCommandException() {
        assertThrows(IllegalCommandException.class, () -> new AddModuleCommand("addmod modules", allModules));
    }

    @Test
    void testAddModuleCommand_validParams_expectsNoException() {
        assertDoesNotThrow(() -> new AddModuleCommand("CS2113", allModules));
    }

    @Test
    void testAddModuleCommand_existingLessonType_expectsNoException() throws IllegalCommandException, InvalidModule {
        AddModuleCommand newCommand = new AddModuleCommand("cs2113 -tut 05", allModules);
        newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertDoesNotThrow(() -> new AddModuleCommand("cs2113 -tut 06", allModules)
                .execute(taskList, ui, storage,moduleList, allModules, calendar));
    }

    @Test
    void testAddModuleCommand_duplicateModule_expectsNoException() throws IllegalCommandException, InvalidModule {
        AddModuleCommand newCommand = new AddModuleCommand("cs2113", allModules);
        newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertDoesNotThrow(() -> new AddModuleCommand("cs2113", allModules)
                .execute(taskList, ui, storage,moduleList, allModules, calendar));
    }

}
