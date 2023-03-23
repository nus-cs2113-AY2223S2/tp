package seedu.apollo.ui;

import seedu.apollo.command.WeekCommand;
import seedu.apollo.command.module.ShowModuleCommand;
import seedu.apollo.command.task.AddCommand;
import seedu.apollo.command.module.AddModuleCommand;
import seedu.apollo.command.Command;
import seedu.apollo.command.task.DateCommand;
import seedu.apollo.command.module.DeleteModuleCommand;
import seedu.apollo.command.utils.ExitCommand;
import seedu.apollo.command.task.FindCommand;
import seedu.apollo.command.utils.HelpCommand;
import seedu.apollo.command.task.ListCommand;
import seedu.apollo.command.module.ListModuleCommand;
import seedu.apollo.command.task.ModifyCommand;
import seedu.apollo.exception.module.EmptyAddModException;
import seedu.apollo.exception.module.EmptyDelModException;
import seedu.apollo.exception.module.EmptyShowModException;
import seedu.apollo.exception.utils.EmptyKeywordException;
import seedu.apollo.exception.task.EmptyTaskDescException;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.exception.task.InvalidDateTime;
import seedu.apollo.exception.task.InvalidDeadline;
import seedu.apollo.exception.task.InvalidEvent;
import seedu.apollo.exception.module.InvalidModule;
import seedu.apollo.module.ModuleList;

import java.rmi.UnexpectedException;
import java.util.Arrays;

/**
 * Parser class that makes sense of user commands or text.
 */
public class Parser {

    // User command words understood by Apollo
    public static final String COMMAND_EXIT_WORD = "bye";
    public static final String COMMAND_HELP_WORD = "help";
    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_WEEK_WORD = "week";
    public static final String COMMAND_DATE_WORD = "date";
    public static final String COMMAND_FIND_WORD = "find";
    public static final String COMMAND_MARK_WORD = "mark";
    public static final String COMMAND_UNMARK_WORD = "unmark";
    public static final String COMMAND_DELETE_WORD = "delete";
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_EVENT_WORD = "event";

    public static final String COMMAND_ADD_MODULE_WORD = "addmod";
    public static final String COMMAND_LIST_MODULE_WORD = "listmod";
    public static final String COMMAND_DELETE_MODULE_WORD = "delmod";
    public static final String COMMAND_SHOW_MODULE_DETAILS_WORD = "showmod";

    /**
     * Returns the corresponding Command to the user input.
     *
     * @param userCommand Command line input from user.
     * @param ui          Prints out error messages if command cannot be parsed.
     * @param size        Number of tasks saved in TaskList.
     * @return Corresponding Command class to user input.
     * @throws UnexpectedException If an unexpected error occurs.
     */
    public static Command getCommand(String userCommand, Ui ui, int size, ModuleList moduleData)
            throws UnexpectedException {
        final String[] split = userCommand.trim().split("\\s+", 2);
        try {
            return parseCommand(split, size, moduleData);
        } catch (IllegalCommandException e) {
            ui.printInvalidCommand();
        } catch (EmptyTaskDescException e) {
            ui.printEmptyDescription();
        } catch (EmptyKeywordException e) {
            ui.printEmptyKeyword();
        } catch (EmptyAddModException e) {
            ui.printEmptyAddMod();
        } catch (EmptyDelModException e) {
            ui.printEmptyDelMod();
        } catch (NumberFormatException e) {
            ui.printErrorForIdx(size);
        } catch (InvalidDeadline e) {
            ui.printInvalidDeadline();
        } catch (InvalidEvent e) {
            ui.printInvalidEvent();
        } catch (InvalidDateTime e) {
            ui.printInvalidDate();
        } catch (InvalidModule e) {
            ui.printInvalidModule();
        } catch (EmptyShowModException e) {
            ui.printEmptyShowModCode();
        }
        return null;
    }

    /**
     * Adds data from user input into the corresponding Command class that is returned.
     *
     * @param split Parsed user input split into command and parameter.
     * @param size  Number of tasks saved in TaskList.
     * @return Command class with data from user input.
     * @throws InvalidDateTime         If the input format for a date and time is wrong.
     * @throws EmptyKeywordException   If keyword is left empty (for Find command).
     * @throws NumberFormatException   If the index is left empty (for Mark, Delete commands).
     * @throws EmptyTaskDescException  If task description is left empty (for Add command).
     * @throws InvalidDeadline         If the input format for adding a deadline is wrong.
     * @throws InvalidEvent            If the input format for adding an event is wrong.
     * @throws IllegalCommandException If an unknown command is input by the user.
     * @throws UnexpectedException     If some unexpected error occurs.
     * @throws InvalidModule           If the module does not exist
     * @throws EmptyDelModException    If there is no input for module code
     * @throws EmptyAddModException    If there is no input for module code
     * @throws EmptyShowModException   If there is no input for module code
     */
    private static Command parseCommand(String[] split, int size, ModuleList moduleData)
            throws InvalidDateTime, EmptyKeywordException, EmptyTaskDescException, InvalidDeadline, InvalidEvent,
            IllegalCommandException, NumberFormatException, UnexpectedException, InvalidModule,
            EmptyAddModException, EmptyDelModException, EmptyShowModException {
        String command = split[0];
        switch (command) {
        case COMMAND_SHOW_MODULE_DETAILS_WORD:
            if (isEmptyParam(split)) {
                throw new EmptyShowModException();
            }
            return new ShowModuleCommand(split[1], moduleData);

        case COMMAND_LIST_MODULE_WORD:
            if (!isOneWord(split)) {
                throw new IllegalCommandException();
            }
            return new ListModuleCommand();

        case COMMAND_EXIT_WORD:
            if (!isOneWord(split)) {
                throw new IllegalCommandException();
            }
            return new ExitCommand();

        case COMMAND_HELP_WORD:
            if (!isOneWord(split)) {
                throw new IllegalCommandException();
            }
            return new HelpCommand();

        case COMMAND_LIST_WORD:
            if (!isOneWord(split)) {
                throw new IllegalCommandException();
            }
            return new ListCommand();

        case COMMAND_WEEK_WORD:
            return new WeekCommand();

        case COMMAND_DATE_WORD:
            if (isEmptyParam(split)) {
                throw new InvalidDateTime();
            }
            return new DateCommand(split[1]);

        case COMMAND_FIND_WORD:
            if (isEmptyParam(split)) {
                throw new EmptyKeywordException();
            }
            return new FindCommand(split[1]);

        case COMMAND_MARK_WORD:
        case COMMAND_UNMARK_WORD:
        case COMMAND_DELETE_WORD:
            if (isEmptyParam(split)) {
                throw new NumberFormatException();
            }
            return new ModifyCommand(command, split[1], size);

        case COMMAND_TODO_WORD:
        case COMMAND_DEADLINE_WORD:
        case COMMAND_EVENT_WORD:
            if (isEmptyParam(split)) {
                throw new EmptyTaskDescException();
            }
            return new AddCommand(command, split[1]);

        case COMMAND_ADD_MODULE_WORD:
            if (isEmptyParam(split)) {
                throw new EmptyAddModException();
            }
            return new AddModuleCommand(split[1], moduleData);

        case COMMAND_DELETE_MODULE_WORD:
            if (isEmptyParam(split)) {
                throw new EmptyDelModException();
            }
            String moduleCode = split[1];
            return new DeleteModuleCommand(moduleCode);

        default:
            throw new IllegalCommandException();
        }
    }

    /**
     * Checks if the user's input parameter is empty.
     *
     * @param split Parsed user input split into command and parameter.
     * @return {@code true} if the input parameter is empty, {@code false} otherwise.
     */
    private static Boolean isEmptyParam(String[] split) {
        return (split.length != 2);
    }

    /**
     * Checks if the user's input is only one word.
     *
     * @param split Parsed user input split into command and parameter.
     * @return {@code true} if the input is only one word, {@code false} otherwise.
     */
    private static Boolean isOneWord(String[] split) {
        return (split.length == 1);
    }


    /**
     * Separates a Deadline's input data into its description, and due date.
     *
     * @param param User input data describing the Deadline.
     * @return Parsed user input split into description and due date.
     * @throws InvalidDeadline If the user did not input the due date in the right format.
     */
    public static String[] parseDeadline(String param) throws InvalidDeadline {
        String[] split = param.trim().split("\\s/by\\s", 2);
        if (split.length != 2) {
            throw new InvalidDeadline();
        }
        return split;
    }

    /**
     * Separates an Event's input data into its description, start date, and end date.
     *
     * @param param User input data describing the Event.
     * @return Parsed user input split into description, start date, and end date.
     * @throws InvalidEvent If the user did not input the start or end date in the right format.
     */
    public static String[] parseEvent(String param) throws InvalidEvent {
        String[] split = param.trim().split("\\s/from\\s|\\s/to\\s", 3);
        String[] checkFromToOrder = Arrays.copyOfRange(param.trim().split("/"), 1, 3);
        if ((split.length != 3) || (!checkFromToOrder[0].startsWith("from") || !checkFromToOrder[1].startsWith("to"))) {
            throw new InvalidEvent();
        }
        return split;
    }

}
