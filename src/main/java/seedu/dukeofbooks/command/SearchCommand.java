package seedu.dukeofbooks.command;

import java.util.ArrayList;

import seedu.dukeofbooks.common.Messages;
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import static seedu.dukeofbooks.common.Messages.DIVIDER;
import static seedu.dukeofbooks.common.Messages.NEW_LINE;

public class SearchCommand extends UserCommand {
    public static final String COMMAND_WORD = "search";
    public static final String NUMBER_INDEX_STRING = "No.";
    public static final String INVALID_ITEM_BORROWED = "ERROR: Item is not valid";
    public static final String INVALID_ACTION = "ERROR: Action only search by title or topic";
    public static final String INCORRECT_SYNTAX = "INVALID SYNTAX\nExpected: search -title TITLE -topic TOPIC";
    public static final String TITLE_SEARCH = "title";
    public static final String TOPIC_SEARCH = "topic";
    public static final String COMBINED_SEARCH = "combined";
    public static final String RESULT_STRING = "The Search Results:\n";

    private final String item;
    private final String action;

    public SearchCommand(String item, String action) {
        this.item = item;
        this.action = action;
    }

    @Override
    public CommandResult execute() {
        try {
            ArrayList<Book> books;
            switch(action){
            case TITLE_SEARCH:
                books = SearchController.searchBooksByTitle(item);
                break;
            case TOPIC_SEARCH:
                books = SearchController.searchBooksByTopic(item);
                break;
            case COMBINED_SEARCH:
                // Split the item string to get title, topic


                books = SearchController.searchBooksByTitle(item.split(Messages.NEW_LINE,2)[0]);
                ArrayList<Book> altBooks = SearchController.searchBooksByTopic(item.split(Messages.NEW_LINE,2)[1]);
                books = intersection(books,altBooks);
                break;
            default:
                throw new IllegalOperationException(INVALID_ACTION);
            }

            return new CommandResult(processBooks(books));
        } catch (IllegalOperationException | IllegalValueException e) {
            return new CommandResult(e.getMessage());
        }
    }

    private String processBooks(ArrayList<Book> books) throws IllegalValueException {
        String returnString = "";
        if (books == null){
            throw new IllegalValueException(INVALID_ITEM_BORROWED);
        }

        returnString = returnString.concat(RESULT_STRING);
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            returnString = returnString.concat(DIVIDER).concat(NEW_LINE);
            returnString = returnString.concat(NUMBER_INDEX_STRING).concat(Integer.toString(i+1)).concat(NEW_LINE);
            returnString = returnString.concat(book.toString()).concat(NEW_LINE);
        }

        return returnString;
    }

    public <T> ArrayList<T> intersection(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
}
