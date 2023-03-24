package seedu.pettracker.parser;

import seedu.pettracker.commands.*;
//import seedu.pettracker.exceptions.UnknownKeywordException;

import java.util.logging.Logger;
import java.util.logging.Level;

public class CommandParser {
    private static final Logger logger = Logger.getLogger("CommandLogger");
    final String KEYWORD_EXIT = "exit";
    final String KEYWORD_ADD_PET = "add-pet";
    final String KEYWORD_REMOVE_PET = "remove-pet";
    final String KEYWORD_LIST_PET = "list";
    final String KEYWORD_ADD_STAT = "add-stat";
    final String KEYWORD_REMOVE_STAT = "remove-stat";
    final String KEYWORD_EDIT_STAT = "edit-stat";
    final String KEYWORD_ADD_TASK = "add-task";
    final String KEYWORD_REMOVE_TASK = "remove-task";
    final String KEYWORD_LIST_TASKS = "list-tasks";
    final String KEYWORD_MARK_TASK = "mark-task";
    final String KEYWORD_UNMARK_TASK = "unmark-task";

    public CommandParser() {
    }

    public Command parseCommand(String commandString) {
        logger.log(Level.INFO, "Parser received: " + commandString + "\n");
        return newCommand(commandString);
    }

    /**
     * Separates the command keyword from the rest of the string
     *
     * @param commandString User input string
     * @return Command keyword
     */
    private static String parseKeyword(String commandString) {
        logger.log(Level.INFO, "Parsing keyword: " + commandString.split(" ", 2)[0] + "\n");
        assert commandString.split(" ", 2).length > 0 : "No keyword";
        return commandString.split(" ", 2)[0];
    }

    /**
     * Separates the arguments from the rest of the string
     *
     * @param commandString User input string
     * @return Arguments
     */
    private static String parseArgs(String commandString) throws ArrayIndexOutOfBoundsException {
        assert commandString.split(" ", 2).length > 1 : "No arguments";
        return commandString.split(" ", 2)[1];
    }

    /**
     * Creates a new command object from the user input string
     *
     * @param commandString Initial String that the user typed in
     * @return new Command object
     */
    public Command newCommand(String commandString) {
        switch (parseKeyword(commandString)) {
        case KEYWORD_EXIT:
            return new ExitCommand();
        case KEYWORD_ADD_PET:
            try {
                return new AddPetCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_REMOVE_PET:
            try {
                return new RemovePetCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_LIST_PET:
            return new ListPetCommand();
        case KEYWORD_ADD_STAT:
            try {
                return new AddStatCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_REMOVE_STAT:
            try {
                return new RemoveStatCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_EDIT_STAT:
            try {
                return new EditStatCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_ADD_TASK:
            try {
                return new AddTaskCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_REMOVE_TASK:
            try {
                return new RemoveTaskCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_LIST_TASKS:
            return new ListTasksCommand();
        case KEYWORD_MARK_TASK:
            try {
                return new MarkTaskCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        case KEYWORD_UNMARK_TASK:
            try {
                return new UnMarkTaskCommand(parseArgs(commandString));
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.INFO,"bounds error");
                break;
            }
        default:
            return new InvalidCommand();
        }
        return new InvalidCommand();
    }
}
