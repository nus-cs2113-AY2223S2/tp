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

import static org.junit.jupiter.api.Assertions.*;

class AddModuleCommandTest {


    @Test
    void testAddModuleCommand_invalidParams_expectsIllegalCommandException() throws
            FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt");
        ModuleList allModules = storage.loadModuleData();
        assertThrows(IllegalCommandException.class, () -> new AddModuleCommand("addmod modules", allModules));
    }
    @Test
    void testAddModuleCommand_validParams_expectsNoException() throws
            FileNotFoundException, IllegalCommandException, InvalidModule {
        Storage storage = new Storage("test.txt", "testModuleData.txt");
        ModuleList allModules = storage.loadModuleData();
        ModuleList moduleList = new ModuleList();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Calendar calendar = new Calendar();
        AddModuleCommand newCommand  = new AddModuleCommand("CS2113", allModules);
        newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertDoesNotThrow(() -> new AddModuleCommand("CS2113", allModules));
    }

}