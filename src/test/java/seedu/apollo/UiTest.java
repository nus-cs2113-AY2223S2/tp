package seedu.apollo;

import org.junit.jupiter.api.Test;

import seedu.apollo.module.ModuleList;
import seedu.apollo.module.Module;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UiTest {

    @Test
    void printList_normalInput_noExceptionThrown() throws IOException {
        Storage storage = new Storage("test.txt", "moduleData.txt");
        Ui ui = new Ui();
        TaskList taskList = storage.loadTaskList(ui);
        assertDoesNotThrow(() -> ui.printList(taskList));
    }

    @Test
    void printList_emptyInput_noExceptionThrown() {
        Storage storage = new Storage("test.txt", "moduleData.txt");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> ui.printList(taskList));
    }

    @Test
    void printFoundList_normalInput_noExceptionThrown() throws IOException {
        Storage storage = new Storage("test.txt", "moduleData.txt");
        Ui ui = new Ui();
        TaskList taskList = storage.loadTaskList(ui);
        assertDoesNotThrow(() -> ui.printFoundList(taskList));
    }

    @Test
    void printFoundList_emptyInput_noExceptionThrown() {

        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> ui.printFoundList(taskList));
    }

    @Test
    void printModuleList_emptyInput_noExceptionThrown() {

        Ui ui = new Ui();
        ModuleList moduleList = new ModuleList();
        assertDoesNotThrow(() -> ui.printModuleList(moduleList));
    }

    @Test
    void printModuleList_normalInput_noExceptionThrown() {
        Ui ui = new Ui();
        ModuleList moduleList = new ModuleList();
        moduleList.add(new Module("CS1010", "Programming Methodology", "4"));
        assertDoesNotThrow(() -> ui.printModuleList(moduleList));
    }


}
