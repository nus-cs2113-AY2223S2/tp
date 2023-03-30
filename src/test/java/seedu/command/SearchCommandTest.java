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

public class SearchCommandTest {
    public static final String VALID_REPONSE_STRING = "The Search Results:\n"+
            "===================================================\n"+
            "No.1\n"+
            "ISBN: 1234567890123\n"+
            "Title: The only book\n"+
            "Author: anon\n"+
            "Topic: python\n";
    public static final String VALID_BOOK_TITLE = "The only book";
    public static final String VALID_BOOK_ISBN = "1234567890123";
    public static final String VALID_BOOK_AUTHOR = "anon";
    public static final String VALID_BOOK_TOPIC = "python";
    
    public Inventory inventory = new Inventory();
    public Book newBook;


    public void execute_validSearchTitle_success() {
        SearchCommand command = new SearchCommand("book",SearchCommand.TITLE_SEARCH);
        CommandResult result = command.execute();
        
        assertEquals(VALID_REPONSE_STRING, result.feedbackToUser);
    }

    public void execute_invalidSearchTitle_fail() {
        SearchCommand command = new SearchCommand("The missing book",SearchCommand.TITLE_SEARCH);
        CommandResult result = command.execute();

        assertEquals(BOOK_TITLE_NOT_FOUND, result.feedbackToUser);
    }

    public void execute_validSearchTopic_success() {
        SearchCommand command = new SearchCommand(VALID_BOOK_TOPIC,SearchCommand.TOPIC_SEARCH);
        CommandResult result = command.execute();
        
        assertEquals(VALID_REPONSE_STRING, result.feedbackToUser);
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
        this.newBook = new Book(VALID_BOOK_ISBN, VALID_BOOK_TITLE, VALID_BOOK_TOPIC, VALID_BOOK_AUTHOR);
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

        execute_validSearchTopic_success();
        execute_invalidSearchTopic_fail();
    }
}
