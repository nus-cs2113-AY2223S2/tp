package seedu.dukeofbooks.controller;

import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.DuplicateActionException;
import seedu.dukeofbooks.data.exception.IllegalDateException;
import seedu.dukeofbooks.data.exception.LoanRecordNotFoundException;
import seedu.dukeofbooks.data.loan.Loan;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;

import java.time.LocalDateTime;
import java.util.List;

public class LoanController {
    private static final String AVAILABLE_STATUS_FORMAT =
            "Status: Not borrowed";
    private static final String BORROWED_STATUS_FORMAT =
            "Status: Borrowed (borrower: %s, due: %s)";
    private static final String OVERDUE_STATUS_FORMAT =
            "Status: Overdue (borrower: %s, due: %s)";
    private static final int DEFAULT_RENEW_DAYS = 30;

    private final LoanRecords loanRecords;

    public LoanController(LoanRecords records) {
        loanRecords = records;
    }

    public void borrowBook(Person borrower, Book toBorrow,
                           LocalDateTime borrowTime)
            throws DuplicateActionException {
        if (toBorrow.isBorrowed()) {
            throw new DuplicateActionException("Book is not available!");
        }
        LocalDateTime dueTime = borrowTime.plusDays(DEFAULT_RENEW_DAYS);
        Loan newLoan = new Loan(toBorrow, borrower, borrowTime, dueTime);

        loanRecords.add(newLoan);
        toBorrow.borrowItem();
    }

    public void returnBook(Person borrower, Book toReturn)
            throws LoanRecordNotFoundException {
        if (!toReturn.isBorrowed()) {
            throw new LoanRecordNotFoundException("Book is not borrowed!");
        }

        List<Loan> records = loanRecords.findByPersonItem(borrower, toReturn);
        // TODO optimize find?
        for (Loan loan : records) {
            if (!loan.isReturned()) {
                loan.setReturned(true);
                toReturn.returnItem();
            }
        }
    }

    public void renewBook(Person person, Book book) {
        LocalDateTime newDue = LocalDateTime.now().plusDays(DEFAULT_RENEW_DAYS);
        try {
            renewBook(person, book, newDue);
        } catch (IllegalDateException ide) {
            throw new RuntimeException();
        }
    }

    public void renewBook(Person person, Book book, LocalDateTime newDue)
            throws IllegalDateException {
        List<Loan> records = loanRecords.findByPersonItem(person, book);
        // TODO optimize find?
        for (Loan loan : records) {
            if (!loan.isReturned()) {
                loan.setLoanEnd(newDue);
            }
        }
    }
}
