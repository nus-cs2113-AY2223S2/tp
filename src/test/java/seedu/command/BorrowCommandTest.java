package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.dukeofbooks.command.BorrowCommand;
import seedu.dukeofbooks.command.CommandResult;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BorrowCommandTest {
    private static final LoanRecords loanRecords = new LoanRecords();
    private static final Book sampleBook = createBook("java coffee");
    private static final Book book2 = createBook("python snake");
    private static final Person person1 = createPerson("john");
    private static final Person person2 = createPerson("tan");

    private static Book createBook(String title) {
        try {
            return new Book("isbn", title, "a topic", "an author");
        } catch (IllegalValueException ive) {
            throw new RuntimeException();
        }
    }

    private static Person createPerson(String name) {
        try {
            return new Person(name);
        } catch (IllegalValueException ive) {
            throw new RuntimeException();
        }
    }

    @Test
    public void borrowCommand_success() {
        // prepare data
        loanRecords.clear();
        if (sampleBook.isBorrowed()) {
            sampleBook.returnItem();
        }

        // execution
        BorrowCommand borrowCommand = new BorrowCommand(loanRecords, person1, sampleBook);
        CommandResult result = borrowCommand.execute();

        // check borrow is borrowed
        assertTrue(sampleBook.isBorrowed());
        // check command result
        assertEquals("Borrow is successful.", result.feedbackToUser);
    }

    @Test
    public void borrowCommand_fail() {
        // prepare data
        loanRecords.clear();
        if (!sampleBook.isBorrowed()) {
            sampleBook.borrowItem();
        }

        // execution
        BorrowCommand borrowCommand = new BorrowCommand(loanRecords, person1, sampleBook);
        CommandResult result = borrowCommand.execute();

        // check book is still borrowed
        assertTrue(sampleBook.isBorrowed());
        // check command result
        assertEquals("This item is not borrowable.", result.feedbackToUser);
    }
}
