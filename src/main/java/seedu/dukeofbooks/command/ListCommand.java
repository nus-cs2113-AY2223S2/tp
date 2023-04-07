package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.ListController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalValueException;

import java.util.ArrayList;

import static seedu.dukeofbooks.common.Messages.DIVIDER;
import static seedu.dukeofbooks.common.Messages.NEW_LINE;

public class ListCommand extends UserCommand {
    public static final String COMMAND_WORD = "list";
    public static final String NUMBER_INDEX_STRING = "No.";
    public static final String NO_BOOKS_IN_LIBRARY = "There are no books in the library.";
    public static final String RESULT_STRING = "Books in Library:\n";

    public ListCommand() {}

    @Override
    public CommandResult execute() {
        try {
            ArrayList<Book> listedBooks = ListController.listBooks();
            return new CommandResult(processBooks(listedBooks));
        } catch (IllegalValueException e) {
            return new CommandResult(e.getMessage());
        }
    }

    private String processBooks(ArrayList<Book> listedBooks) throws IllegalValueException {
        String returnString = "";
        returnString = returnString.concat(RESULT_STRING);
        if (listedBooks.size() == 0) {
            returnString = returnString.concat(DIVIDER).concat(NEW_LINE);
            returnString = returnString.concat(NO_BOOKS_IN_LIBRARY).concat(NEW_LINE);
        } else {
            for (int i = 0; i < listedBooks.size(); i++) {
                Book book = listedBooks.get(i);
                returnString = returnString.concat(DIVIDER).concat(NEW_LINE);
                returnString = returnString.concat(NUMBER_INDEX_STRING).concat(Integer.toString(i+1)).concat(NEW_LINE);
                returnString = returnString.concat(book.toString()).concat(NEW_LINE);
            }
        }
        return returnString;
    }
}
