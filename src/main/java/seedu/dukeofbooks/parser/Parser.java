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
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;
import static seedu.dukeofbooks.common.Messages.TITLE_ARG;
import static seedu.dukeofbooks.common.Messages.TOPIC_ARG;
import static seedu.dukeofbooks.common.Messages.AUTHOR_ARG;
import static seedu.dukeofbooks.common.Messages.ACTION_ARG;
import static seedu.dukeofbooks.common.Messages.ISBN_ARG;
import static seedu.dukeofbooks.common.Messages.SPACE_CHAR;

public class Parser {
    private final Person currentUser;
    // todo set loan records
    private final LoanRecords loanRecords;

    public Parser(Person user, LoanRecords loanRecords, SearchController searchController) {
        this.currentUser = user;
        this.loanRecords = loanRecords;
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
        String[] parts = arguments.split(" ");
        int titleIndex = -1;
        int topicIndex = -1;
        int authorIndex = -1;
        int actionIndex = -1;
        int isbnIndex = -1;
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].equals(TITLE_ARG)) {
                titleIndex = i;
            }
            if (parts[i].equals(TOPIC_ARG)) {
                topicIndex = i;
            }
            if (parts[i].equals(AUTHOR_ARG)) {
                authorIndex = i;
            }
            if (parts[i].equals(ACTION_ARG)) {
                actionIndex = i;
            }
            if (parts[i].equals(ISBN_ARG)) {
                isbnIndex = i;
            }
        }

        if (titleIndex == -1 && topicIndex == -1 && authorIndex == -1
                && actionIndex == -1 && actionIndex == -1) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        try {
            String title = getWord(titleIndex, parts);
            String topic = getWord(topicIndex, parts);
            String author = getWord(authorIndex, parts);
            String action = getWord(actionIndex, parts);
            String isbn = getWord(isbnIndex, parts);
            // String isbn = Book.createISBN();
            Book target =  new Book(isbn, title, topic, author);

            if (action.equals(InventoryCommand.ADD_WORD)) {
                return new InventoryCommand(target, InventoryCommand.ADD_WORD);
            } else {
                return new InventoryCommand(target, InventoryCommand.DELETE_WORD);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    private Command prepareRenewCommand(String arguments) {
        String[] parts = arguments.split(" ");
        int titleIndex = -1;
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].equals(TITLE_ARG)) {
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
            BorrowableItem toRenew = SearchController.searchBookByTitle(title);
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
            BorrowableItem toBorrow = SearchController.searchBookByTitle(title);
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
            BorrowableItem toReturn = SearchController.searchBookByTitle(title);
            return new ReturnCommand(loanRecords, currentUser, toReturn);
        } catch (IllegalValueException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
    
    private Command prepareSearchCommand(String arguments) {
        String[] parts = arguments.split(SPACE_CHAR);
        int titleIndex = -1;
        int topicIndex = -1;
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].equals(TITLE_ARG)) {
                titleIndex = i;
            }
            if (parts[i].equals(TOPIC_ARG)) {
                topicIndex = i;
            }
        }

        if ((titleIndex != -1) && (topicIndex!=-1)) {
            String title = getWord(titleIndex, parts);
            String topic = getWord(topicIndex, parts);
            return new SearchCommand(title,SearchCommand.COMBINED_SEARCH);
        } else if (titleIndex != -1) {
            String title = getWord(titleIndex, parts);
            return new SearchCommand(title,SearchCommand.TITLE_SEARCH);
        } else if (topicIndex != -1) {
            String topic = getWord(topicIndex, parts);
            return new SearchCommand(topic,SearchCommand.TOPIC_SEARCH);
        } else {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private String getWord(int index, String[] parts) {
        StringBuilder sb = new StringBuilder();

        for (int i = index + 1; i < parts.length; ++i) {
            if (checkIfArg(parts[i])) {
                return sb.toString().trim();
            }
            sb.append(parts[i]).append(SPACE_CHAR);
        }
        return sb.toString().trim();
    }

    private boolean checkIfArg(String word) {
        if (word.startsWith("-")){
            return true;
        }
        return false;
    }
}
