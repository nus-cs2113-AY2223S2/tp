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

class ListCommandTest {

    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Calendar calendar = new Calendar();

    ModuleList moduleList = new ModuleList();

    ListCommandTest() throws FileNotFoundException {
    }

    @Test
    void testListCommandExecute_emptyTaskList_expectsNoException() {
        ListCommand newCommand = new ListCommand();
        assertDoesNotThrow(() -> newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void testListCommandExecute_normalList_expectsNoException() {

        ListCommand newCommand = new ListCommand();
        taskList.add(new ToDo("Test Junit"));

        assertDoesNotThrow(() -> newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }
}
