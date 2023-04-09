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

class MarkTaskCommandTest {
    private Parser parser = new Parser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<Flags, String> generateInputArguments(String id) {
        HashMap<Flags, String> args = new HashMap<>();
        args.put(Flags.COMMAND_MARK, id);
        return args;
    }

    @BeforeEach
    void setUp() {
        String sampleTask = "add something -due 06-04-2030 23:59";
        testList = new TaskList();
        try {
            parser.parseCommand(sampleTask).execute(testList, null, ui);
        } catch (ToDoListException e) {
            fail("Failed to initialize test task list");
        }
    }

    @Test
    public void markTask_emptyId_throwException() {
        try {
            Command testMark = new MarkTaskCommand(generateInputArguments(""));
            testMark.execute(testList, null, ui);
        } catch (ToDoListException e) {
            return;
        } catch (NullPointerException e) {
            return;
        }
        fail("A mark command was successfully constructed with missing id");
    }

    @Test
    public void markTask_invalidId_throwsException() {
        final String[] invalidId = {"1000", "meh", "52.1"};
        for (String id : invalidId) {
            try {
                Command testMark = new MarkTaskCommand(generateInputArguments(id));
                testMark.execute(testList, null, ui);
            } catch (ToDoListException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
            fail("A mark command was successfully constructed with invalid id: " + id);
        }
    }
}
