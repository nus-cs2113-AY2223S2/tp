package seedu.apollo.command.task;

import org.junit.jupiter.api.Test;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.Command;

import seedu.apollo.command.utils.specifichelpcommand.AddModHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.DateHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.DeadlineHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.DeleteHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.DeleteModHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.EventHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.ExitHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.FindHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.ListHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.ListModuleHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.MarkHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.ShowModHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.SpecifiedAidHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.TodoHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.UnmarkHelpCommand;
import seedu.apollo.command.utils.specifichelpcommand.WeekHelpCommand;

import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Parser;
import seedu.apollo.ui.Ui;

import java.io.FileNotFoundException;
import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SpecificHelpCommandTest {
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList moduleList = new ModuleList();
    ModuleList allModules = storage.loadModuleData();
    Calendar calendar = new Calendar();
    int size = 1;

    public SpecificHelpCommandTest() throws FileNotFoundException {

    }

    //@@author PoobalanAatmikaLakshmi
    @Test
    void parseHelpCommand_invalidCommand_expectException() throws IllegalArgumentException, UnexpectedException {
        String userCommand = "help draw";
        assertNull(Parser.getCommand(userCommand, ui, size, null));
    }

    @Test
    void parseHelpCommand_trailingArgument_expectException() throws IllegalArgumentException, UnexpectedException {
        String userCommand = "help showmod hello";
        assertNull(Parser.getCommand(userCommand, ui, size, null));
    }
    //@@author

    @Test
    void parseHelpCommand_listHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help list";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(ListHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_todoHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help todo";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(TodoHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_eventHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help event";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(EventHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_deadlineHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help deadline";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(DeadlineHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_markHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help mark";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(MarkHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_unMarkHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help unmark";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(UnmarkHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_deleteHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help delete";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(DeleteHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_findHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help find";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(FindHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));

    }

    @Test
    void parseHelpCommand_dateHelpCommand_expectNoException() throws UnexpectedException {
        String userCommand = "help date";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(DateHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void parseHelpCommand_showmodHelpCommand_expectNoException() throws UnexpectedException{
        String userCommand = "help showmod";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(ShowModHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void parseHelpCommand_listModuleHelpCommand_expectNoException() throws UnexpectedException{
        String userCommand = "help listmod";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(ListModuleHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void parseHelpCommand_weekHelpCommand_expectNoException() throws UnexpectedException{
        String userCommand = "help week";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(WeekHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void parseHelpCommand_addModuleHelpCommand_expectNoException() throws UnexpectedException{
        String userCommand = "help addmod";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(AddModHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void parseHelpCommand_deleteModuleHelpCommand_expectNoException() throws UnexpectedException{
        String userCommand = "help delmod";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(DeleteModHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void parseHelpCommand_specificHelpCommand_expectNoException() throws UnexpectedException{
        String userCommand = "help help";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(SpecifiedAidHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

    @Test
    void exitHelpCommand_specificHelpCommand_expectNoException() throws UnexpectedException{
        String userCommand = "help bye";
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assert newCommand != null;
        assertEquals(ExitHelpCommand.class, newCommand.getClass());
        assertDoesNotThrow(() ->
                newCommand.execute(taskList, ui, storage, moduleList, allModules, calendar));
    }

}

