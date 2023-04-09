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

class EditEmailCommandTest {
    private static final String EMAIL_EXAMPLE = "username@email.com";

    private Parser parser = new Parser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<Flags, String> generateInputArguments(String id, String email) {
        HashMap<Flags, String> args = new HashMap<>();
        args.put(Flags.COMMAND_EDIT_EMAIL, id);
        args.put(Flags.EDIT, email);
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
    public void editEmail_invalidId_throwsException() {
        final String[] invalidIdes = {"1000", "meh", "52.1"};
        for (String id : invalidIdes) {
            HashMap<Flags, String> args = generateInputArguments(id, EMAIL_EXAMPLE);
            try {
                Command testEdit = new EditEmailCommand(args);
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
    public void editEmail_invalidEmail_throwsException() {
        final String[] invalidEmails = {"username_email.com", "username@email", "username@email..com"};
        for (String email : invalidEmails) {
            HashMap<Flags, String> args = generateInputArguments("1", email);
            try {
                Command testEdit = new EditEmailCommand(args);
                testEdit.execute(testList, null, ui);
            } catch (ToDoListException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid email: " + email);
        }
    }
}
