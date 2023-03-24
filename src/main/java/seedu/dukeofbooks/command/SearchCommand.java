package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String INVALID_ITEM_BORROWED = "ERROR: Item is not valid";
    public static final String INVALID_ACTION = "ERROR: Action only search by title or topic";
    public static final String TITLE_SEARCH = "title";
    public static final String TOPIC_SEARCH = "topic";
    public static final String COMBINED_SEARCH = "combined";
    public static final String RESULT_STRING = "Results: %s";

    private String item;
    private String action;

    public SearchCommand(String item, String action) {
        this.item = item;
        this.action = action;
    }

    @Override
    public CommandResult execute() {
        try {
            Book targetBook;
            switch(action){
            case TITLE_SEARCH:
                targetBook = SearchController.searchBookByTitle(item);
                break;
            case TOPIC_SEARCH:
                targetBook = SearchController.searchBookByTopic(item);
                break;
            default:
                throw new IllegalOperationException(INVALID_ACTION);
            }

            return new CommandResult(String.format(RESULT_STRING,targetBook.toString()));
        } catch (IllegalOperationException | IllegalValueException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
