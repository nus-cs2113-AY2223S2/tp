package seedu.apollo.command.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.module.InvalidModule;
import seedu.apollo.exception.module.LessonTypeNotAddedException;
import seedu.apollo.exception.module.ModuleNotAddedException;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListModuleWithLessonCommandTest {
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Calendar calendar = new Calendar();

    ModuleList moduleList = new ModuleList();

    private Module module;

    public ListModuleWithLessonCommandTest() throws FileNotFoundException {
    }

    @Test
    void testConstructor_invalidModule_expectsInvalidModuleException() {
        assertThrows(InvalidModule.class,
                () -> new ListModuleWithLessonCommand("CS22222", allModules));
    }

    @Test
    void testExecute_illegalCommand_expectsIllegalCommandException() {
        assertThrows(IllegalCommandException.class,
                () -> new ListModuleWithLessonCommand("CS2113 -mod 1", allModules)
                        .execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testExecute_lessonNotAdded_expectsNoException() {
        assertDoesNotThrow(() -> new AddModuleCommand("EG1311", allModules)
                .execute(taskList, ui, storage, moduleList, allModules, calendar));
        assertDoesNotThrow(() -> new ListModuleWithLessonCommand("EG1311", allModules)
                        .execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testExecute_lessonTypeNotAdded_expectsNoException() {
        assertDoesNotThrow(() -> new AddModuleCommand("CS2113 -tut 02", allModules)
                .execute(taskList, ui, storage, moduleList, allModules, calendar));
        assertDoesNotThrow(() -> new ListModuleWithLessonCommand("CS2113 -lec", allModules)
                        .execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testExecute_moduleNotAdded_expectsNoException() {
        assertDoesNotThrow(() -> new ListModuleWithLessonCommand("CG2023", allModules)
                .execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testCopyModuleListData_moduleNotAdded_expectsModuleNotAddedException()
            throws IllegalCommandException, InvalidModule {
        AddModuleCommand newCommand = new AddModuleCommand("CG2023", allModules);
        newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar);
        assertThrows(ModuleNotAddedException.class,
                () -> new ListModuleWithLessonCommand("EE2211", allModules)
                        .copyModuleListData(moduleList));
    }

    @Test
    void testCopyModuleListData_lessonTypeNotAdded_expectsLessonTypeNotAddedException() {
        assertDoesNotThrow(() -> new AddModuleCommand("CS2113", allModules)
                .execute(taskList, ui, storage, moduleList, allModules, calendar));
        assertThrows(LessonTypeNotAddedException.class,
                () -> new ListModuleWithLessonCommand("CS2113 -lec", allModules)
                        .copyModuleListData(moduleList));
    }

}
