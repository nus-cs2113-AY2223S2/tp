package seedu.dukeofbooks.controller;

import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.DuplicateActionException;
import seedu.dukeofbooks.data.exception.IllegalDateException;
import seedu.dukeofbooks.data.exception.LoanRecordNotFoundException;
import seedu.dukeofbooks.data.loan.Loan;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;

import java.time.LocalDateTime;

public class LoanController {
    private static final String AVAILABLE_STATUS_FORMAT =
            "Status: Not borrowed";
    private static final String BORROWED_STATUS_FORMAT =
            "Status: Borrowed (borrower: %s, due: %s)";
    private static final String OVERDUE_STATUS_FORMAT =
            "Status: Overdue (borrower: %s, due: %s)";
    private static final int DEFAULT_RENEW_DAYS = 30;

    public static void borrowItem(LoanRecords loanRecords, Person borrower,
                                  BorrowableItem toBorrow, LocalDateTime borrowTime)
            throws DuplicateActionException {
        if (toBorrow.isBorrowed()) {
            throw new DuplicateActionException("Book is not available!");
        }
        LocalDateTime dueTime = borrowTime.plusDays(DEFAULT_RENEW_DAYS);
        Loan newLoan = new Loan(toBorrow, borrower, borrowTime, dueTime);

        loanRecords.add(newLoan);
        toBorrow.borrowItem();
        borrower.addLoan(newLoan);
    }

    public static void returnItem(LoanRecords loanRecords, Person borrower,
                                  BorrowableItem toReturn)
            throws LoanRecordNotFoundException {
        if (!toReturn.isBorrowed()) {
            throw new LoanRecordNotFoundException("Book is not borrowed!");
        }

        Loan loan = loanRecords.getLastActiveLoan(borrower);
        if (loan == null) {
            throw new LoanRecordNotFoundException("Cannot find an active loan!");
        }

        loan.setReturned(true);
        toReturn.returnItem();
    }

    public static void renewItem(LoanRecords loanRecords, Person person,
                                 BorrowableItem toRenew)
            throws LoanRecordNotFoundException {
        LocalDateTime newDue = LocalDateTime.now().plusDays(DEFAULT_RENEW_DAYS);
        try {
            renewItem(loanRecords, person, toRenew, newDue);
        } catch (IllegalDateException ide) {
            assert false;
        }
    }

    public static void renewItem(LoanRecords loanRecords, Person person,
                                 BorrowableItem toRenew, LocalDateTime newDue)
            throws IllegalDateException, LoanRecordNotFoundException {
        Loan loan = loanRecords.getLastActiveLoan(person, toRenew);
        if (loan == null) {
            throw new LoanRecordNotFoundException("Cannot find an active loan!");
        }
        if (!loan.isReturned()) {
            loan.setLoanEnd(newDue);
        }

    }
}
