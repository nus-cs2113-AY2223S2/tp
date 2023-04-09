package seedu.todolist.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.todolist.constants.Flags;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Parser;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

class UnmarkTaskCommandTest {
    private Parser parser = new Parser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<Flags, String> generateInputArguments(String id) {
        HashMap<Flags, String> args = new HashMap<>();
        args.put(Flags.COMMAND_UNMARK, id);
        return args;
    }

    @BeforeEach
    void setUp() {
        String sampleTask = "add something";
        testList = new TaskList();
        try {
            parser.parseCommand(sampleTask).execute(testList, null, ui);
        } catch (ToDoListException e) {
            fail("Failed to initialize test task list");
        }
    }

    @Test
    public void unmarkTask_emptyId_throwException() {
        try {
            Command testUnmark = new UnmarkTaskCommand(generateInputArguments(""));
            testUnmark.execute(testList, null, ui);
        } catch (ToDoListException e) {
            return;
        } catch (NullPointerException e) {
            return;
        }
        fail("An Unmark command was successfully constructed with missing id");
    }

    @Test
    public void unmarkTask_invalidId_throwsException() {
        final String[] invalidId = {"1000", "meh", "52.1"};
        for (String id : invalidId) {
            try {
                Command testUnmark = new UnmarkTaskCommand(generateInputArguments(id));
                testUnmark.execute(testList, null, ui);
            } catch (ToDoListException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
            fail("A Unmark command was successfully constructed with invalid id: " + id);
        }
    }
}
