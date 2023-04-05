//@@author RuiShengGit
package seedu.todolist.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.todolist.constants.Flags;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Parser;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class DeleteTaskCommandTest {
    private Parser parser = new Parser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<Flags, String> generateInputArguments(String id) {
        HashMap<Flags, String> args = new HashMap<>();
        args.put(Flags.COMMAND_ADD, id);
        return args;
    }

    @BeforeEach
    void setUp() {
        String sampleTask = "add something -due 06-04-2030 23:59";
        testList = new TaskList();
        try {
            parser.parseCommand(sampleTask).execute(testList, ui);
        } catch (ToDoListException e) {
            fail("Failed to initialize test task list");
        }
    }

    @Test
    public void deleteTask_emptyId_throwsException() {
        try {
            Command testDelete = new DeleteTaskCommand(generateInputArguments(""));
            testDelete.execute(testList, ui);
        } catch (ToDoListException e) {
            return;
        } catch (NullPointerException e) {
            return;
        }
        fail("A delete command was successfully constructed with missing id");
    }

    @Test
    public void deleteTask_invalidId_throwsException() {
        final String[] invalidId = {"1000", "-1", "52.1"};
        for (String id : invalidId) {
            try {
                Command testDelete = new DeleteTaskCommand(generateInputArguments(id));
                testDelete.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
            fail("A delete command was successfully constructed with invalid id: " + id);
        }
    }
}
