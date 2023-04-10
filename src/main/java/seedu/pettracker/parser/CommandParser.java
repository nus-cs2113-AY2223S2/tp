package seedu.pettracker.parser;

import seedu.pettracker.commands.Command;
import seedu.pettracker.commands.InvalidCommand;

import seedu.pettracker.exceptions.UnknownKeywordException;
import seedu.pettracker.exceptions.EmptyArgException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<keyword>\\S+)(?<arguments>.*)");
    final String KEYWORD_EXIT = "exit";
    final String KEYWORD_ADD_PET = "add-pet";
    final String KEYWORD_REMOVE_PET = "remove-pet";
    final String KEYWORD_LIST_PET = "list";
    final String KEYWORD_ADD_STAT = "add-stat";
    final String KEYWORD_REMOVE_STAT = "remove-stat";
    final String KEYWORD_EDIT_STAT = "edit-stat";
    final String KEYWORD_EDIT_TASK = "edit-task";
    final String KEYWORD_ADD_TASK = "add-task";
    final String KEYWORD_REMOVE_TASK = "remove-task";
    final String KEYWORD_LIST_TASKS = "list-tasks";
    final String KEYWORD_MARK_TASK = "mark-task";
    final String KEYWORD_UNMARK_TASK = "unmark-task";
    final String KEYWORD_SCHEDULE_TASKS = "schedule";
    final String KEYWORD_HELP = "help";
    final String UNKNOWN_KEYWORD_MESSAGE = "Please enter a valid command!";
    final String EMPTY_CMD_MESSAGE = "Please enter a command!";

    public CommandParser() {
    }

    public Command parseCommand(String commandString) {
        try {
            return newCommand(commandString);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Creates a new command object from the user input string
     *
     * @param commandString Initial String that the user typed in
     * @return new Command object
     */
    public Command newCommand(String commandString) throws Exception {
        final Matcher matcher = COMMAND_FORMAT.matcher(commandString.trim());
        if (!matcher.matches()) {
            throw new EmptyArgException(EMPTY_CMD_MESSAGE);
        }

        final String keyword = matcher.group("keyword");
        assert keyword != null;
        final String arguments = matcher.group("arguments").trim();

        switch (keyword.toLowerCase()) {
        case KEYWORD_EXIT:
            return new ExitParser().parse(arguments);
        case KEYWORD_ADD_PET:
            return new AddPetParser().parse(arguments);
        case KEYWORD_REMOVE_PET:
            return new RemovePetParser().parse(arguments);
        case KEYWORD_LIST_PET:
            return new ListPetParser().parse(arguments);
        case KEYWORD_ADD_STAT:
            return new AddStatParser().parse(arguments);
        case KEYWORD_REMOVE_STAT:
            return new RemoveStatParser().parse(arguments);
        case KEYWORD_EDIT_STAT:
            return new EditStatParser().parse(arguments);
        case KEYWORD_EDIT_TASK:
            return new EditTaskParser().parse(arguments);
        case KEYWORD_ADD_TASK:
            return new AddTaskParser().parse(arguments);
        case KEYWORD_REMOVE_TASK:
            return new RemoveTaskParser().parse(arguments);
        case KEYWORD_LIST_TASKS:
            return new ListTasksParser().parse(arguments);
        case KEYWORD_MARK_TASK:
            return new MarkTaskParser().parse(arguments);
        case KEYWORD_UNMARK_TASK:
            return new UnMarkTaskParser().parse(arguments);
        case KEYWORD_SCHEDULE_TASKS: 
            return new ScheduleParser().parse(arguments);
        case KEYWORD_HELP:
            return new HelpParser().parse(arguments);
        default:
            throw new UnknownKeywordException(UNKNOWN_KEYWORD_MESSAGE);
        }
    }
}
