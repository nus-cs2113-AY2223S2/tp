package seedu.pettracker.parser;

import org.junit.jupiter.api.Test;
import seedu.pettracker.commands.AddPetCommand;
import seedu.pettracker.commands.AddStatCommand;
import seedu.pettracker.commands.ExitCommand;
import seedu.pettracker.commands.ListPetCommand;
import seedu.pettracker.commands.RemovePetCommand;
import seedu.pettracker.commands.RemoveStatCommand;
import seedu.pettracker.commands.AddTaskCommand;
import seedu.pettracker.commands.RemoveTaskCommand;
import seedu.pettracker.commands.ListTasksCommand;
import seedu.pettracker.commands.MarkTaskCommand;
import seedu.pettracker.commands.UnMarkTaskCommand;
import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.IllegalArgException;
import seedu.pettracker.exceptions.UnknownKeywordException;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CommandParserTest {

    // newCommand() non-parsed keywords
    @Test
    void parseExit() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("exit") instanceof ExitCommand)
        );
    }

    @Test
    void parseListPet() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("list") instanceof ListPetCommand));
    }

    @Test
    void parseListPetWithArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("list 1"));
    }

    @Test
    void parseAddPet() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("add-pet name") instanceof AddPetCommand));
    }

    @Test
    void parseAddPetNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyPetNameException.class, () -> cp.newCommand("add-pet"));
    }

    @Test
    void parseRemovePet() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("remove-pet name") instanceof RemovePetCommand));
    }

    @Test
    void parseRemovePetNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyPetNameException.class, () -> cp.newCommand("remove-pet"));
    }

    @Test
    void parseAddStat() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("add-stat name stat 1") instanceof AddStatCommand));
    }

    @Test
    void parseAddStatNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("add-stat"));
    }

    @Test
    void parseRemoveStat() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("remove-stat name stat") instanceof RemoveStatCommand));
    }

    @Test
    void parseRemoveStatNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("remove-stat"));
    }

    @Test
    void parseAddTask() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("add-task desc") instanceof AddTaskCommand));
    }

    @Test
    void parseAddTaskNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("add-task"));
    }

    @Test
    void parseAddTaskWithDeadline () {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("add-task test /by 2024-04-02") instanceof AddTaskCommand));
    }

    @Test
    void parseRemoveTask() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("remove-task 1") instanceof RemoveTaskCommand));
    }

    @Test
    void parseRemoveTaskNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("remove-task"));
    }

    @Test
    void parseListTasks() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("list-tasks") instanceof ListTasksCommand));
    }

    @Test
    void parseListTasksWithArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("list-tasks 1"));
    }

    @Test
    void parseMarkTask() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("mark-task 1") instanceof MarkTaskCommand));
    }

    @Test
    void parseMarkTaskNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("mark-task"));
    }

    @Test
    void parseUnmarkTask() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("unmark-task 1") instanceof UnMarkTaskCommand));
    }

    @Test
    void parseUnmarkTaskNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("unmark-task"));
    }

    @Test
    void parseNonsense() {
        CommandParser cp = new CommandParser();
        assertThrows(UnknownKeywordException.class, () -> cp.newCommand("nonsense"));
    }

    @Test
    void parseNonsenseWithArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(UnknownKeywordException.class, () -> cp.newCommand("nonsense 1"));
    }
    
}
