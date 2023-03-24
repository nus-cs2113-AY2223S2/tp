package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

//@@author clement559
public class AddTaskCommandTest {
    private static final String DATE_EXAMPLE = "06-04-2000";
    private static final String TIME_EXAMPLE = "23:59";
    private static final String REPEAT_EXAMPLE = "0";

    private TaskList testList = new TaskList();
    private Ui ui = new Ui();

    private HashMap<String, String> generateInputArguments(String description, String date, String time,
                                                           String repeatCount) {
        HashMap<String, String> args = new HashMap<>();
        args.put(AddTaskCommand.KEYWORD, description);
        args.put(Flags.DEADLINE.FLAG, date + " " + time);
        args.put(Flags.REPEAT.FLAG, repeatCount);
        return args;
    }

    @Test
    public void addTask_emptyDescription_throwsException() {
        HashMap<String, String> args = generateInputArguments("", DATE_EXAMPLE, TIME_EXAMPLE, REPEAT_EXAMPLE);
        try {
            Command testAdd = new AddTaskCommand(args);
            testAdd.execute(testList, ui);
        } catch (ToDoListException e) {
            return;
        }
        fail("An add command was successfully constructed with missing description");
    }

    @Test
    public void addTask_invalidDate_throwsException() {
        final String[] invalidDates = {"29/02/2031", "493430", "2013-12-22", "1023-43-22"};
        for (String date : invalidDates) {
            HashMap<String, String> args = generateInputArguments("0", date, TIME_EXAMPLE, REPEAT_EXAMPLE);
            try {
                Command testDelete = new AddTaskCommand(args);
                testDelete.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid date: " + date);
        }
    }

    @Test
    public void addTask_invalidTime_throwsException() {
        final String[] invalidTimes = {"1000", "493430", "9AM"};
        for (String time : invalidTimes) {
            HashMap<String, String> args = generateInputArguments("0", DATE_EXAMPLE, time, REPEAT_EXAMPLE);
            try {
                Command testDelete = new AddTaskCommand(args);
                testDelete.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid time: " + time);
        }
    }
}
