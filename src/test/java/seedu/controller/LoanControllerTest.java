package seedu.controller;

import org.junit.jupiter.api.Test;
import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.book.Isbn;
import seedu.dukeofbooks.data.book.Title;
import seedu.dukeofbooks.data.book.Topic;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.person.PersonName;
import seedu.dukeofbooks.data.person.Phone;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LoanControllerTest {
    private static Person createPerson(String name) {
        try {
            PersonName personName = new PersonName(name);
            Phone phone = new Phone(12345678);
            return new Person(personName, phone);
        } catch (IllegalValueException ive) {
            return null;
        }
    }

    private static Book createBook(String bookName) {
        try {
            Title title = new Title(bookName);
            Isbn isbn = new Isbn("1234567");
            Topic topic = new Topic("Coffee");
            Person author = new Person("authorA");
            return new Book(isbn, title, topic, author);
        } catch (IllegalValueException ive) {
            return null;
        }
    }
    private static final LoanRecords loanRecords = new LoanRecords();
    private static final LoanController loanController =
            new LoanController(loanRecords);

    @Test
    public void testBorrowBook() {
        Book book = createBook("Java Programming");
        assertNotNull(book);
        Person borrower = createPerson("BorrowerA");
        assertNotNull(borrower);
        assertFalse(book.isBorrowed());
        assertDoesNotThrow(() -> loanController.borrowBook(borrower, book,
                LocalDateTime.now()));
        assertTrue(book.isBorrowed());
    }
}
