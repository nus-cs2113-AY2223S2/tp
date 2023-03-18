package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class StorageTest {
    private static final String TEST_DATA_FOLDER = "./src/test/data"; // this is also a directory
    private static final String ACTUAL_SAVE_FILE = "./src/test/data/actualSaveFile.txt";
    private static final String EXPECTED_SAVE_FILE = "./src/test/data/expectedSaveFile.txt";
    private static final String VALID_DATA_FILE = "./src/test/data/validData.txt";
    private static final String INVALID_DATA_FILE = "./src/test/data/invalidData.txt";
    private static final String NON_EXISTENT_FILE = "./src/test/data/doesNotExist.txt";
    private static final String NEW_SAVE_FILE_FOR_COMPARISON = "./src/test/data/newSaveFileAfterLoading.txt";

    private CommandParser parser = new CommandParser();
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();

    @Test
    void saveData_properFilePath_success() {
        try {
            Command addTaskCommand1 = parser.parseCommand("add bubu -d 18-02-2022 18:00");
            Command addTaskCommand2 = parser.parseCommand("add baba -d 12-05-2023 17:00");
            addTaskCommand1.execute(taskList, ui);
            addTaskCommand2.execute(taskList, ui);
            Storage.saveData(ACTUAL_SAVE_FILE, taskList, ui);
            File f1 = new File(ACTUAL_SAVE_FILE);
            File f2 = new File(EXPECTED_SAVE_FILE);
            Scanner s1 = new Scanner(f1);
            Scanner s2 = new Scanner(f2);
            boolean isActualSaveEqualExpectedSave = true;
            while (s1.hasNext() && s2.hasNext()) {
                String line1 = s1.nextLine();
                String line2 = s2.nextLine();
                if (!line1.equals(line2)) {
                    isActualSaveEqualExpectedSave = false;
                }
            }
            assertTrue(isActualSaveEqualExpectedSave);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void saveData_directoryAsFilePath_exceptionThrown() { // if the path exists but is a directory, not a regular file
        try {
            Storage.saveData(TEST_DATA_FOLDER, taskList, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            System.out.println("Directory cannot be opened");
        }
    }

    @Test
    void loadData_fileWithInvalidData_exceptionThrown() {
        try {
            taskList = Storage.loadData(INVALID_DATA_FILE, ui);
            fail(); // should not reach this line
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void loadData_nonExistentFile_throwsException() {
        try {
            taskList = Storage.loadData(NON_EXISTENT_FILE, ui);
            fail(); // should not reach this line
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                ui.printFileNotFoundMessage();
            }
        }
    }

    @Test
    void loadData_fileWithValidData_taskListCreated() {
        try {
            taskList = Storage.loadData(VALID_DATA_FILE, ui);
            Storage.saveData(NEW_SAVE_FILE_FOR_COMPARISON, taskList, ui);
            TaskList newlySavedTaskList = Storage.loadData(NEW_SAVE_FILE_FOR_COMPARISON, ui);
            boolean isActualSaveEqualExpectedSave = true;
            System.out.println("original tasklist: ");
            System.out.println(taskList.toString());
            System.out.println("newly saved tasklist: ");
            System.out.println(newlySavedTaskList.toString());
            if (!newlySavedTaskList.toString().equals(taskList.toString())) {
                isActualSaveEqualExpectedSave = false;
            }
            assertTrue(isActualSaveEqualExpectedSave);
        } catch (Exception e) {
            fail(); // should not reach this line
        }
    }
}
