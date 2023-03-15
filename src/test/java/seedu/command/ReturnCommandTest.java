package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.dukeofbooks.command.CommandResult;
import seedu.dukeofbooks.command.ReturnCommand;
import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.DuplicateActionException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.exception.LoanRecordNotFoundException;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReturnCommandTest {
    private static final LoanRecords loanRecords = new LoanRecords();
    private static final Book sampleBook = createBook("java coffee");
    private static final Person samplePerson = createPerson("john");
    private static final String SUCCESS_MSG = "Item has been returned!";
    private static final String FAIL_MSG = "Item is not borrowed!";

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

    @BeforeEach
    public void prepareData() {
        loanRecords.clear();
        LocalDateTime now = LocalDateTime.now();
        try {
            LoanController.borrowItem(loanRecords, samplePerson, sampleBook, now);
        } catch (DuplicateActionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void returnCommand_success() {
        assertTrue(sampleBook.isBorrowed());
        assertFalse(loanRecords.get(0).isReturned());

        ReturnCommand command = new ReturnCommand(loanRecords, samplePerson, sampleBook);
        CommandResult result = command.execute();

        assertTrue(loanRecords.get(0).isReturned());
        assertFalse(sampleBook.isBorrowed());
        assertEquals(SUCCESS_MSG, result.feedbackToUser);
    }

    @Test
    public void returnCommand_fail() {
        assertTrue(sampleBook.isBorrowed());
        assertFalse(loanRecords.get(0).isReturned());
        try {
            LoanController.returnItem(loanRecords, samplePerson, sampleBook);
        } catch (LoanRecordNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(loanRecords.get(0).isReturned());
        assertFalse(sampleBook.isBorrowed());

        ReturnCommand command = new ReturnCommand(loanRecords, samplePerson, sampleBook);
        CommandResult result = command.execute();

        assertTrue(loanRecords.get(0).isReturned());
        assertFalse(sampleBook.isBorrowed());
        assertEquals(FAIL_MSG, result.feedbackToUser);
    }
}
