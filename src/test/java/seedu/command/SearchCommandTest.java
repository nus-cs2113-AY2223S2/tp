package seedu.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.dukeofbooks.command.CommandResult;
import seedu.dukeofbooks.command.SearchCommand;
import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.DukeOfBooksException;
import seedu.dukeofbooks.data.inventory.Inventory;

import static seedu.dukeofbooks.common.Messages.BOOK_TITLE_NOT_FOUND;
import static seedu.dukeofbooks.common.Messages.BOOK_TOPIC_NOT_FOUND;
import static seedu.dukeofbooks.command.SearchCommand.RESULT_STRING;

public class SearchCommandTest {
    public Inventory inventory = new Inventory();
    public Book newBook;

    public void execute_validSearchTitle_success() {
        SearchCommand command = new SearchCommand("The only book",SearchCommand.TITLE_SEARCH);
        CommandResult result = command.execute();
        
        assertEquals(String.format(RESULT_STRING,newBook.toString()), result.feedbackToUser);
    }

    public void execute_invalidSearchTitle_fail() {
        SearchCommand command = new SearchCommand("The missing book",SearchCommand.TITLE_SEARCH);
        CommandResult result = command.execute();

        assertEquals(BOOK_TITLE_NOT_FOUND, result.feedbackToUser);
    }

    public void execute_validSearchTopic_success() {
        SearchCommand command = new SearchCommand("python",SearchCommand.TOPIC_SEARCH);
        CommandResult result = command.execute();
        
        assertEquals(String.format(RESULT_STRING,newBook.toString()), result.feedbackToUser);
    }

    public void execute_invalidSearchTopic_fail() {
        SearchCommand command = new SearchCommand("java",SearchCommand.TOPIC_SEARCH);
        CommandResult result = command.execute();

        assertEquals(BOOK_TOPIC_NOT_FOUND, result.feedbackToUser);
    }


    @Test
    public void execute_searchTitle() throws DukeOfBooksException {
        SearchController.setData(inventory);
        InventoryController.setData(inventory);
        this.newBook = new Book("1234567890123", "The only book", "python", "anon");
        InventoryController.addBook(this.newBook);

        execute_validSearchTitle_success();
        execute_invalidSearchTitle_fail();
    }

    @Test
    public void execute_searchTopic() throws DukeOfBooksException {
        SearchController.setData(inventory);
        InventoryController.setData(inventory);
        this.newBook = new Book("1234567890123", "The only book", "python", "anon");
        InventoryController.addBook(this.newBook);

        execute_validSearchTitle_success();
        execute_invalidSearchTitle_fail();
    }
}
