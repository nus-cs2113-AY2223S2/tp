package seedu.dukeofbooks.parser;

import static seedu.dukeofbooks.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dukeofbooks.command.BorrowCommand;
import seedu.dukeofbooks.command.Command;
import seedu.dukeofbooks.command.ExitCommand;
import seedu.dukeofbooks.command.HistoryCommand;
import seedu.dukeofbooks.command.IncorrectCommand;
import seedu.dukeofbooks.command.InventoryCommand;
import seedu.dukeofbooks.command.ListCommand;
import seedu.dukeofbooks.command.RenewCommand;
import seedu.dukeofbooks.command.ReturnCommand;
import seedu.dukeofbooks.command.SearchCommand;
import seedu.dukeofbooks.data.person.Person;

public class Parser {
    public Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2); // split the input into command and arguments
        if (words.length == 0) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {

            case InventoryCommand.COMMAND_WORD:
                return prepareInventoryCommand(arguments);
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case RenewCommand.COMMAND_WORD:
                return prepareRenewCommand(arguments);
            case BorrowCommand.COMMAND_WORD:
                return prepareBorrowCommand(arguments);
           //Commented out first to avoid giving error, HistoryCommand needs to take a person param
                // case HistoryCommand.COMMAND_WORD:
            //     return HistoryCommand();
            case ReturnCommand.COMMAND_WORD:
                return prepareReturnCommand(arguments);
            case SearchCommand.COMMAND_WORD:
                return prepareSearchCommand(arguments);
            default:
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }
    }

    private Command prepareInventoryCommand(String arguments) {
        String[] parts = arguments.split("-");
        if (parts.length != 1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new InventoryCommand(parts[0].trim());
    }

    private Command prepareRenewCommand(String arguments) {
        String[] parts = arguments.split("-title ");
        if (parts.length != 1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new RenewCommand(parts[0].trim());
    }
    
    private Command prepareBorrowCommand(String arguments) {
        String[] parts = arguments.split("-title ");
        if (parts.length != 1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new BorrowCommand(parts[0].trim());
    }
    
    private Command prepareReturnCommand(String arguments) {
        String[] parts = arguments.split("-title ");
        if (parts.length != 1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new ReturnCommand(parts[0].trim());
    }
    
    private Command prepareSearchCommand(String arguments) {
        String[] parts = arguments.split("-title ");
        if (parts.length!=1){
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new SearchCommand(parts[0].trim());
    }
}
