package seedu.todolist.logic.command;

import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Parser;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

//@@author RuiShengGit
public class DeleteTaskCommandTest {
    private Parser parser = new Parser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<String, String> generateInputArguments(String index) {
        HashMap<String, String> args = new HashMap<>();
        args.put(AddTaskCommand.KEYWORD, index);
        return args;
    }

    @BeforeEach
    void setUp() {
        String sampleTask = "add something -due 06-04-2000 23:59 -rep 0";
        testList = new TaskList();
        try {
            parser.parseCommand(sampleTask).execute(testList, ui);
        } catch (ToDoListException e) {
            fail("Failed to initialize test task list");
        }
    }

    @Test
    public void deleteTask_emptyIndex_throwsException() {
        try {
            Command testDelete = new DeleteTaskCommand(generateInputArguments(""));
            testDelete.execute(testList, ui);
        } catch (ToDoListException e) {
            return;
        }
        fail("A delete command was successfully constructed with missing index");
    }

    @Test
    public void deleteTask_invalidIndex_throwsException() {
        final String[] invalidIndex = {"1000", "-1", "52.1"};
        for (String index : invalidIndex) {
            try {
                Command testDelete = new DeleteTaskCommand(generateInputArguments(index));
                testDelete.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("A delete command was successfully constructed with invalid index: " + index);
        }
    }
}
