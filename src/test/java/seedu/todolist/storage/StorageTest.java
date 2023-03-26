package seedu.todolist.storage;

import org.junit.jupiter.api.Test;
import seedu.todolist.logic.Parser;
import seedu.todolist.logic.command.Command;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StorageTest {
    private static final String PROPER_SAVE_FILE = "./src/test/data/properSaveFile.txt";
    private static final String INVALID_SAVE_FILE = "./src/test/data/invalidSaveFile.txt";
    private static final String TEST_DATA_FOLDER = "./src/test/data";
    
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private Storage storage = new Storage(PROPER_SAVE_FILE);

    @Test
    void saveAndLoadData_properFilePath_success() {
        try {
            String sampleInput1 = "add bubu -due 18-02-2032 18:00 -rep 0";
            Command command1 = parser.parseCommand(sampleInput1);
            command1.execute(taskList, ui);
            String sampleInput2 = "add baba -due 18-04-2033 12:00 -rep 0";
            Command command2 = parser.parseCommand(sampleInput2);
            command2.execute(taskList, ui);
            // Save modified task list object
            storage.saveData(taskList);
            // Load the saved task list into another task list object
            TaskList testTaskList = storage.loadData();
            assertEquals(taskList.toString(), testTaskList.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    // If the path exists but is a directory, not a regular file
    void saveData_directoryAsFilePath_exceptionThrown() {
        try {
            Storage storageDirectory = new Storage(TEST_DATA_FOLDER);
            storageDirectory.saveData(taskList);
            // The test should not reach the following line
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void loadData_fileWithInvalidData_exceptionThrown() {
        try {
            Storage storageInvalid = new Storage(INVALID_SAVE_FILE);
            taskList = storageInvalid.loadData();
            // The test should not reach the following line
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
