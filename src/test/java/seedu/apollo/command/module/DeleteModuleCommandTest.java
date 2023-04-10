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

class DeleteModuleCommandTest {

    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();
    ModuleList moduleList = new ModuleList();
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Calendar calendar = new Calendar();


    DeleteModuleCommandTest() throws FileNotFoundException {

    }

    @Test
    void testDeleteModuleCommand_invalidParams_expectsIllegalCommandException() {
        assertThrows(IllegalCommandException.class, () -> new DeleteModuleCommand("delete module"));
    }

    @Test
    void testDeleteModuleCommand_validParams_expectsNoException() throws
            IllegalCommandException, InvalidModule {
        AddModuleCommand newCommand  = new AddModuleCommand("CS2113", allModules);
        newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertDoesNotThrow(() -> new DeleteModuleCommand("CS2113"));
    }

    @Test
    void testDeleteModuleCommand_invalidLessonType_expectsNoException()
            throws IllegalCommandException, InvalidModule {

        new AddModuleCommand("CS2113 -lec 1", allModules).
                execute(taskList, ui, storage, moduleList, allModules, calendar);
        DeleteModuleCommand newCommand = new DeleteModuleCommand("CS2113 -lc 1");
        assertDoesNotThrow(() -> newCommand.execute(new TaskList(), new Ui(),
                new Storage("test.txt", "testModuleData.txt"), new ModuleList(),
                new ModuleList(), new Calendar()));
    }

    @Test
    void testDeleteModuleCommand_classNotInList_expectsNoException() throws IllegalCommandException, InvalidModule {
        new AddModuleCommand("CS2113 -tut 05", allModules).
                execute(taskList, ui, storage, moduleList, allModules, calendar);
        DeleteModuleCommand newCommand = new DeleteModuleCommand("CS2113 -tut 04");
        assertDoesNotThrow(() -> newCommand.execute(new TaskList(), new Ui(),
                new Storage("test.txt", "testModuleData.txt"), moduleList,
                allModules, calendar));
    }

    @Test
    void testDeleteModuleCommand_invalidModuleCode_expectsNoException() throws IllegalCommandException, InvalidModule {
        new AddModuleCommand("CS2113 -tut 05", allModules).
                execute(taskList, ui, storage, moduleList, allModules, calendar);
        DeleteModuleCommand newCommand = new DeleteModuleCommand("CS2114 -tut 04");
        assertDoesNotThrow(() -> newCommand.execute(new TaskList(), new Ui(),
                new Storage("test.txt", "testModuleData.txt"), moduleList,
                allModules, calendar));
    }

}
