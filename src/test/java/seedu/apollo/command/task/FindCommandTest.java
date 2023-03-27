package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.utils.EmptyKeywordException;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.task.ToDo;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FindCommandTest {

    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = storage.loadModuleData();
    Calendar calendar = new Calendar();

    FindCommandTest() throws FileNotFoundException {
    }

    @Test
    void testExecute_emptyTaskList_expectNoExceptions() {
        FindCommand newFindCommand = new FindCommand("test");
        assertDoesNotThrow(() -> newFindCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }
    @Test
    void testExecute_populatedTaskList_expectNoExceptions() {
        FindCommand newFindCommand = new FindCommand("lunch");
        taskList.add(new ToDo ("lunch with friends"));
        assertDoesNotThrow(() -> newFindCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

}