//@@author clement559
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

public class EditDeadlineCommandTest {
    private static final String DATE_EXAMPLE = "06-04-2030";
    private static final String TIME_EXAMPLE = "23:59";
    private static final String REPEAT_EXAMPLE = "0";

    private Parser parser = new Parser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<Flags, String> generateInputArguments(String id, String date, String time, String repeatCount) {
        HashMap<Flags, String> args = new HashMap<>();
        args.put(Flags.COMMAND_ADD, id);
        args.put(Flags.DEADLINE, date + " " + time);
        args.put(Flags.REPEAT, repeatCount);
        return args;
    }

    @BeforeEach
    void setUp() {
        String sampleTask = "add something -due 02-02-2033 18:00 -rep 0";
        testList = new TaskList();
        try {
            parser.parseCommand(sampleTask).execute(testList, ui);
        } catch (ToDoListException e) {
            fail("Failed to initialize test task list");
        }
    }
    
    @Test
    public void editDeadline_invalidId_throwsException() {
        final String[] invalidIdes = {"", "]", "wq", "7.5", "-3"};
        for (String id : invalidIdes) {
            HashMap<Flags, String> args = generateInputArguments(id, DATE_EXAMPLE, TIME_EXAMPLE, REPEAT_EXAMPLE);
            try {
                Command testEdit = new EditDeadlineCommand(args);
                testEdit.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid id: " + id);
        }
    }

    @Test
    public void editDeadline_invalidDate_throwsException() {
        final String[] invalidDates = {"31/06/2032", "493430", "2013-12-22", "1023-43-22"};
        for (String date : invalidDates) {
            HashMap<Flags, String> args = generateInputArguments("0", date, TIME_EXAMPLE, REPEAT_EXAMPLE);
            try {
                Command testEdit = new EditDeadlineCommand(args);
                testEdit.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid date: " + date);
        }
    }

    @Test
    public void editDeadline_invalidTime_throwsException() {
        final String[] invalidTimes = {"1000", "493430", "9AM"};
        for (String time : invalidTimes) {
            HashMap<Flags, String> args = generateInputArguments("0", DATE_EXAMPLE, time, REPEAT_EXAMPLE);
            try {
                Command testEdit = new EditDeadlineCommand(args);
                testEdit.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid time: " + time);
        }
    }
}
