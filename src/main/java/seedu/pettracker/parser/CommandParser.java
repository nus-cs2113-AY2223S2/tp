package seedu.pettracker.parser;

import seedu.pettracker.commands.AddPetCommand;
import seedu.pettracker.commands.AddStatCommand;
import seedu.pettracker.commands.Command;
import seedu.pettracker.commands.ExitCommand;
import seedu.pettracker.commands.InvalidCommand;
import seedu.pettracker.commands.ListPetCommand;
import seedu.pettracker.commands.RemovePetCommand;
import seedu.pettracker.commands.RemoveStatCommand;
import seedu.pettracker.commands.AddTaskCommand;
import seedu.pettracker.commands.RemoveTaskCommand;
import seedu.pettracker.exceptions.UnknownKeywordException;

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
    final String KEYWORD_ADD_TASK = "add-task";
    final String KEYWORD_REMOVE_TASK = "remove-task";

    public CommandParser() {
    }

    public Command parseCommand(String commandString) {
        try {
            logger.log(Level.INFO, "Parser received: " + commandString + "\n");
            return newCommand(commandString);
        } catch (UnknownKeywordException e) {
            System.out.println("Unknown Keyword");
            return null;
        }
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
    public Command newCommand(String commandString) throws UnknownKeywordException {
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
        default:
            return new InvalidCommand();
        }
        return new InvalidCommand();
    }
}
