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

class EditPriorityCommandTest {
    private static final String PRIORITY_EXAMPLE = "3";
    private Parser parser = new Parser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<Flags, String> generateInputArguments(String id, String priority) {
        HashMap<Flags, String> args = new HashMap<>();
        args.put(Flags.COMMAND_EDIT_PRIORITY, id);
        args.put(Flags.EDIT, priority);
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
    public void editPriority_invalidId_throwsException() {
        final String[] invalidIdes = {"1000", "meh", "52.1"};
        for (String id : invalidIdes) {
            HashMap<Flags, String> args = generateInputArguments(id, PRIORITY_EXAMPLE);
            try {
                Command testEdit = new EditPriorityCommand(args);
                testEdit.execute(testList, null, ui);
            } catch (ToDoListException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid id: " + id);
        }
    }

    @Test
    public void editPriority_invalidPriority_throwsException() {
        final String[] invalidPriorityLevels = {"{", "4", "-1", "High"};
        for (String priority : invalidPriorityLevels) {
            HashMap<Flags, String> args = generateInputArguments("1", priority);
            try {
                Command testEdit = new EditPriorityCommand(args);
                testEdit.execute(testList, null, ui);
            } catch (ToDoListException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid priority: " + priority);
        }
    }
}
