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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ShowModuleCommandTest {

    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Calendar calendar = new Calendar();

    ModuleList moduleList = new ModuleList();

    ShowModuleCommandTest() throws FileNotFoundException {
    }

    @Test
    void testConstructor_validModule_expectNotNull() throws FileNotFoundException,
            IllegalCommandException, InvalidModule {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        moduleList.add(allModules.findModule("CS2113"));
        ShowModuleCommand newShowMod = new ShowModuleCommand("CS2113", moduleList);
        assertNotNull(newShowMod);
    }

    @Test
    void testConstructor_invalidModule_expectsInvalidModuleException() {
        assertThrows(InvalidModule.class, () -> new ShowModuleCommand("CS21134", allModules));
    }

    @Test
    void testExecute_illegalCommand_expectsIllegalCommandException() {
        assertThrows(IllegalCommandException.class, () -> new ShowModuleCommand("CS2113 -mod 1", allModules)
                .execute(taskList, ui, storage, moduleList, allModules, calendar));
    }
}
