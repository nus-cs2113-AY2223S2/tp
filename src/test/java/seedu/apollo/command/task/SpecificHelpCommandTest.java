package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.Command;
import seedu.apollo.command.utils.specifichelpcommand.DateHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.DeadlineHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.DeleteHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.EventHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.FindHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.ListHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.MarkHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.TodoHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.UnmarkHelpCommand;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Parser;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;
import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpecificHelpCommandTest {
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = storage.loadModuleData();
    Calendar calendar = new Calendar();

    public SpecificHelpCommandTest() throws FileNotFoundException {

    }

    @Test
    void parseHelpCommand_invalidCommand_expectException() throws IllegalArgumentException {
        String userCommand = "help draw";
        assertThrows(IllegalArgumentException.class, () -> Parser.chooseHelpCommand(userCommand));
    }


    @Test
    void parseHelpCommand_listHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help list";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(ListHelpCommand.class, newCommand.getClass());
        ListHelpCommand newListHelpCommand = new ListHelpCommand();
        assertDoesNotThrow(() ->
                newListHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_todoHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help todo";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(TodoHelpCommand.class, newCommand.getClass());
        TodoHelpCommand newTodoHelpCommand = new TodoHelpCommand();
        assertDoesNotThrow(() ->
                newTodoHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_eventHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help event";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(EventHelpCommand.class, newCommand.getClass());
        EventHelpCommand newEventHelpCommand = new EventHelpCommand();
        assertDoesNotThrow(() ->
                newEventHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_deadlineHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help deadline";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(DeadlineHelpCommand.class, newCommand.getClass());
        DeadlineHelpCommand newDeadlineHelpCommand = new DeadlineHelpCommand();
        assertDoesNotThrow(() ->
                newDeadlineHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_markHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help mark";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(MarkHelpCommand.class, newCommand.getClass());
        MarkHelpCommand newMarkHelpCommand = new MarkHelpCommand();
        assertDoesNotThrow(() ->
                newMarkHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_unMarkHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help unmark";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(UnmarkHelpCommand.class, newCommand.getClass());
        UnmarkHelpCommand newUnmarkHelpCommand = new UnmarkHelpCommand();
        assertDoesNotThrow(() ->
                newUnmarkHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_deleteHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help delete";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(DeleteHelpCommand.class, newCommand.getClass());
        DeleteHelpCommand newDeleteHelpCommand = new DeleteHelpCommand();
        assertDoesNotThrow(() ->
                newDeleteHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_findHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help find";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(FindHelpCommand.class, newCommand.getClass());
        FindHelpCommand newFindHelpCommand = new FindHelpCommand();
        assertDoesNotThrow(() ->
                newFindHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_dateHelpCommand_expectNoException() throws UnexpectedException {


        String userCommand = "help date";
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertEquals(DateHelpCommand.class, newCommand.getClass());
        DateHelpCommand newDateHelpCommand = new DateHelpCommand();
        assertDoesNotThrow(() ->
                newDateHelpCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }


}
