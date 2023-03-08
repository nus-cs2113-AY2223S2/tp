package seedu.pettracker.parser;

import seedu.pettracker.commands.*;
import seedu.pettracker.exceptions.UnknownKeywordException;

public class CommandParser {
    final String KEYWORD_EXIT = "exit";
    final String KEYWORD_ADD_PET = "add-pet";
    final String KEYWORD_REMOVE_PET = "remove-pet";
    final String KEYWORD_LIST_PET = "list";
    final String KEYWORD_ADD_STAT = "add-stat";
    final String KEYWORD_REMOVE_STAT = "remove-stat";

    public CommandParser() {
    }

    public Command parseCommand(String commandString) {
        try {
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
        return commandString.split(" ", 2)[0];
    }

    /**
     * Separates the arguments from the rest of the string
     *
     * @param commandString User input string
     * @return Arguments
     */
    private static String parseArgs(String commandString) {
        return commandString.split(" ", 2)[1];
    }

    /**
     * Creates a new command object from the user input string
     *
     * @param commandString Initial String that the user typed in
     * @return new Command object
     */
    public Command newCommand(String commandString) throws UnknownKeywordException {
        Command command;
        //only 1 command as of now
        switch (parseKeyword(commandString)) {
        case KEYWORD_EXIT:
            command = new ExitCommand();
            break;
        case KEYWORD_ADD_PET:
            command = new AddPetCommand(parseArgs(commandString));
            break;
        case KEYWORD_REMOVE_PET:
            command = new RemovePetCommand(parseArgs(commandString));
            break;
        case KEYWORD_LIST_PET:
            command = new ListPetCommand();
            break;
        case KEYWORD_ADD_STAT:
            command = new AddStatCommand(parseArgs(commandString));
            break;
        case KEYWORD_REMOVE_STAT:
            command = new RemoveStatCommand(parseArgs(commandString));
            break;
        default:
            throw new UnknownKeywordException();
        }
        return command;
    }
}
