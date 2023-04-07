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
import seedu.pettracker.commands.ScheduleCommand;
import seedu.pettracker.commands.HelpCommand;
import seedu.pettracker.commands.EditStatCommand;
import seedu.pettracker.commands.EditTaskCommand;

import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.IllegalArgException;
import seedu.pettracker.exceptions.UnknownKeywordException;
import seedu.pettracker.exceptions.EmptyStatException;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CommandParserTest {
    // newCommand() zero arg keywords
    @Test
    void parseExit() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("exit") instanceof ExitCommand)
        );
    }
    @Test
    void parseExitWithArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("exit 1"));
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
    void parseScheduleTasks() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("schedule") instanceof ScheduleCommand));
    }
    @Test
    void parseScheduleTasksWithArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("schedule 1"));
    }
    @Test
    void parseHelp() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("help") instanceof HelpCommand));
    }
    @Test
    void parseHelpWithArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("help 1"));
    }

    // newCommand() Pet keywords
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
    void parseAddStatNoStat() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyStatException.class, () -> cp.newCommand("add-stat name"));
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
    void parseRemoveStatNoStat() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("remove-stat name"));
    }

    // newCommand() edit-stat
    @Test
    void parseEditStat() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("edit-stat name stat 1") instanceof EditStatCommand));
    }
    @Test
    void parseEditStatNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("edit-stat"));
    }
    @Test
    void parseEditStatNoStat() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("edit-stat name"));
    }

    // newCommand() add-task
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
    void parseAddTaskWithDeadlineNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("add-task /by 2024-04-02"));
    }
    @Test
    void parseAddTaskWithDeadlineNoDate() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("add-task test /by"));
    }
    @Test
    void parseAddTaskWithDeadlineInvalidDate() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("add-task test /by 2024-04-32"));
    }

    // newCommand() remove-task
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
    void parseRemoveTaskInvalidInput() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("remove-task test"));
    }
    @Test
    void parseRemoveTaskInvalidIndex() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("remove-task 0"));
    }

    // newCommand() mark-task
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
    void parseMarkTaskInvalidInput() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("mark-task test"));
    }
    @Test
    void parseMarkTaskInvalidIndex() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("mark-task 0"));
    }

    // newCommand() unmark-task
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
    void parseUnmarkTaskInvalidInput() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("unmark-task test"));
    }
    @Test
    void parseUnmarkTaskInvalidIndex() {
        CommandParser cp = new CommandParser();
        assertThrows(IllegalArgException.class, () -> cp.newCommand("unmark-task 0"));
    }

    // newCommand() edit-task
    @Test
    void parseEditTask() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("edit-task 1 desc") instanceof EditTaskCommand));
    }
    @Test
    void parseEditTaskNoArgs() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("edit-task"));
    }
    @Test
    void parseEditTaskNoDesc() {
        CommandParser cp = new CommandParser();
        assertThrows(EmptyArgException.class, () -> cp.newCommand("edit-task 1"));
    }

    // newCommand() nonsense
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
