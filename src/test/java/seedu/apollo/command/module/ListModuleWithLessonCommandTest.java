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

public class ListModuleWithLessonCommandTest {
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Calendar calendar = new Calendar();

    ModuleList moduleList = new ModuleList();

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
    void testExecute_ModuleNotAdded_expectsNoException() {
        assertDoesNotThrow(() -> new ListModuleWithLessonCommand("CG2023", allModules)
                .execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

}
