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
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;

public class Parser {
    // todo set data for search controller
    private final SearchController searchController;
    // todo set current logged in user
    private final Person currentUser;
    // todo set loan records
    private final LoanRecords loanRecords;

    public Parser(Person user, LoanRecords loanRecords, SearchController searchController) {
        this.currentUser = user;
        this.loanRecords = loanRecords;
        this.searchController = searchController;
    }

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
         case HistoryCommand.COMMAND_WORD:
             return new HistoryCommand(currentUser);
        case ReturnCommand.COMMAND_WORD:
            return prepareReturnCommand(arguments);
        case SearchCommand.COMMAND_WORD:
            return prepareSearchCommand(arguments);
        default:
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private Command prepareInventoryCommand(String arguments) {
//        String[] parts = arguments.split("-");
//        if (parts.length != 2) {
//            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
//        }
//        try {
//            String action = parts[0].trim();
//            String title = parts[1].trim();
//            String topic = parts
//            BorrowableItem item = searchController.searchBookByTitle(parts[0]);
//            return new InventoryCommand(item);
//        } catch (IllegalValueException ive) {
//            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
//        }
        return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    private Command prepareRenewCommand(String arguments) {
        String[] parts = arguments.split(" ");
        int titleIndex = -1;
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].equals("-title")) {
                titleIndex = i;
                break;
            }
        }
        if (titleIndex == -1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = titleIndex + 1; i < parts.length; ++i) {
                sb.append(parts[i]).append(" ");
            }
            String title = sb.toString().trim();
            BorrowableItem toRenew = searchController.searchBookByTitle(title);
            return new RenewCommand(loanRecords, currentUser, toRenew);
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
    
    private Command prepareBorrowCommand(String arguments) {
        String[] parts = arguments.split(" ");
        int titleIndex = -1;
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].equals("-title")) {
                titleIndex = i;
                break;
            }
        }
        if (titleIndex == -1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = titleIndex + 1; i < parts.length; ++i) {
                sb.append(parts[i]).append(" ");
            }
            String title = sb.toString().trim();
            BorrowableItem toBorrow = searchController.searchBookByTitle(title);
            return new BorrowCommand(loanRecords, currentUser, toBorrow);
        } catch (IllegalValueException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
    
    private Command prepareReturnCommand(String arguments) {
        String[] parts = arguments.split(" ");
        int titleIndex = -1;
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].equals("-title")) {
                titleIndex = i;
                break;
            }
        }
        if (titleIndex == -1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = titleIndex + 1; i < parts.length; ++i) {
                sb.append(parts[i]).append(" ");
            }
            String title = sb.toString().trim();
            BorrowableItem toReturn = searchController.searchBookByTitle(title);
            return new ReturnCommand(loanRecords, currentUser, toReturn);
        } catch (IllegalValueException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
    
    private Command prepareSearchCommand(String arguments) {
//        String[] parts = arguments.split("-title ");
//        if (parts.length!=1){
//            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
//        }
//        return new SearchCommand(parts[0].trim());
        return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
    }
}
