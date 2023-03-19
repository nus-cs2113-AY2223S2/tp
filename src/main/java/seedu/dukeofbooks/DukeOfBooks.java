package seedu.dukeofbooks;

import seedu.dukeofbooks.command.Command;
import seedu.dukeofbooks.command.CommandResult;
import seedu.dukeofbooks.command.ExitCommand;
import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.book.Isbn;
import seedu.dukeofbooks.data.book.Title;
import seedu.dukeofbooks.data.book.Topic;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.person.PersonName;
import seedu.dukeofbooks.data.person.Phone;
import seedu.dukeofbooks.parser.Parser;
import seedu.dukeofbooks.ui.TextUi;

/**
 * Main file to run
 */
public class DukeOfBooks {

    public static final String VERSION = "DukeOfBooks - Version 1.0";
    private LoanRecords allLoanRecords;
    private InventoryController inventoryController = new InventoryController();
    private SearchController searchController = new SearchController();
    private Person currentUser;
    private TextUi ui;

    public static void main(String[] args) {
        // System.out.println("Hello, world!");

        new DukeOfBooks().run();
    }

    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        this.allLoanRecords = new LoanRecords();
        try {
            this.currentUser = new Person("a user");
            Isbn isbn = new Isbn("testIsbn");
            Title title = new Title("testTitle");
            Topic topic = new Topic("testTopic");
            PersonName authorName = new PersonName("Author");
            Phone authorPhone = new Phone(87654321);
            Person author = new Person(authorName, authorPhone);
            Book book = new Book(isbn, title, topic, author);
            Inventory inventory = new Inventory();
            InventoryController.setData(inventory);
            InventoryController.addBook(book);
            SearchController.setData(inventory);
        } catch (IllegalValueException | IllegalOperationException ive) {
            ive.printStackTrace();
        }
        this.ui = new TextUi();
        ui.showWelcomeMessage(VERSION);
    }

    private void exit() {
        ui.showExitMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser(currentUser, allLoanRecords, searchController).parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            // command.setData(#placeholder);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
