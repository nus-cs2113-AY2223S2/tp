package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

import static org.junit.jupiter.api.Assertions.fail;

class DeleteCommandTest {
    private static TaskList testList = new TaskList();
    private final String KEYWORD = "delete";
    private final String[] sampleTask = {"add", "something", "-d", "06-04-2000", "23:59"};
    private Ui ui = new Ui();

    @Test
    public void deleteTask_emptyIndex () {
        final String emptyIndex = "";
        String[] testString = {KEYWORD, emptyIndex};
        assertConstructingEmptyIndexDeleteCmd(testString);
    }

    @Test
    public void deleteTask_invalidIndex () {
        final String[] invalidIndex = {"1000", "-1"};
        for (String index : invalidIndex) {
            String[] testString = {KEYWORD, index};
            assertConstructingInvalidIndex(testString);
        }
    }

    private void assertConstructingEmptyIndexDeleteCmd(String[] testString){
        Command testAdd = new AddTaskCommand(sampleTask);
        testAdd.execute(testList, ui);
        try {
            Command testDelete = new DeleteCommand(testString, testList);
            testDelete.execute(testList, ui);
        } catch (NullPointerException e) {
            return;
        }
        String error = String.format("A delete command was successfully constructed with invalid input: %s",
                testString[1]);
        fail(error);
    }

    private void assertConstructingInvalidIndex(String[] testString) {
        Command testAdd = new AddTaskCommand(sampleTask);
        testAdd.execute(testList, ui);
        try {
            Command testDelete = new DeleteCommand(testString, testList);
            testDelete.execute(testList, ui);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        String error = String.format("A delete command was successfully constructed with invalid input: %s",
                testString[1]);
        fail(error);
    }
}