package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;

import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.task.ToDo;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ModifyCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = storage.loadModuleData();
    Calendar calendar = new Calendar();

    ModifyCommandTest() throws FileNotFoundException {
    }


    @Test
    void newModifyCommand_invalidDeleteCommand_expectException() {
        assertThrows(NumberFormatException.class, () -> new ModifyCommand("delete", "a", taskList.size()));
    }
    @Test
    void newModifyCommand_invalidMarkCommand_expectException() {
        assertThrows(NumberFormatException.class, () -> new ModifyCommand("mark", "a", taskList.size()));
    }
    @Test
    void newModifyCommand_invalidUnmarkCommand_expectException() {
        assertThrows(NumberFormatException.class, () -> new ModifyCommand("unmark", "a", taskList.size()));
    }

    @Test
    void newModifyCommand_deleteInEmptyTaskList_expectException() {
        assertThrows(NumberFormatException.class, () -> new ModifyCommand("delete", "1", taskList.size()));
    }
    @Test
    void newModifyCommand_markInEmptyTaskList_expectException() {
        assertThrows(NumberFormatException.class, () -> new ModifyCommand("mark", "2", taskList.size()));
    }

    @Test
    void newModifyCommand_unmarkInEmptyTaskList_expectException() {
        assertThrows(NumberFormatException.class, () -> new ModifyCommand("unmark", "3", taskList.size()));
    }

    @Test
    void testExecute_deleteInPopulatedTaskList_expectNoException() {
        taskList.add(new ToDo("lunch with friends"));
        ModifyCommand newModifyCommand = new ModifyCommand("delete", "1", taskList.size());
        assertDoesNotThrow(() -> newModifyCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testExecute_unmarkInPopulatedTaskList_expectNoException() {
        taskList.add(new ToDo("lunch with friends"));
        ModifyCommand newModifyCommand = new ModifyCommand("unmark", "1", taskList.size());
        assertDoesNotThrow(() -> newModifyCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testExecute_markInPopulatedTaskList_expectNoException() {
        taskList.add(new ToDo("lunch with friends"));
        ModifyCommand newModifyCommand = new ModifyCommand("mark", "1", taskList.size());
        assertDoesNotThrow(() -> newModifyCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }
}
