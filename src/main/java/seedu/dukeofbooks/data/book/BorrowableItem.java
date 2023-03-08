package seedu.dukeofbooks.data.book;

import java.time.LocalDate;

import seedu.dukeofbooks.data.exception.DukeOfBooksException;
import seedu.dukeofbooks.data.exception.DuplicateActionException;
import seedu.dukeofbooks.data.person.Person;


public abstract class BorrowableItem {
    private static final String AVAILABLE_STATUS_FORMAT =
            "Status: Not borrowed";
    private static final String BORROWED_STATUS_FORMAT =
            "Status: Borrowed (borrower: %s, due: %s)";
    private static final String OVERDUE_STATUS_FORMAT =
            "Status: Overdue (borrower: %s, due: %s)";

    private static final int DEFAULT_RENEW_DAYS = 30;
    protected boolean isBorrowed;
    protected LocalDate borrowDate;
    protected LocalDate dueDate;
    protected Person borrower;

    public BorrowableItem() {
        isBorrowed = false;
        borrowDate = null;
        dueDate = null;
        borrower = null;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    LocalDate getBorrowDate() {
        return borrowDate;
    }

    LocalDate getDueDate() {
        return dueDate;
    }

    Person getBorrower() {
        return borrower;
    }


    public boolean isOverdue() {
        return isOverdue(LocalDate.now());
    }

    public boolean isOverdue(LocalDate toCheck) {
        return toCheck.isAfter(dueDate);
    }

    public void borrowBook(Person borrower, LocalDate borrowDate)
            throws DuplicateActionException {
        if (isBorrowed) {
            throw new DuplicateActionException("Cannot borrow a borrowed book");
        }
        isBorrowed = true;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(DEFAULT_RENEW_DAYS);
    }

    public void returnBook() throws DuplicateActionException {
        if (!isBorrowed) {
            throw new DuplicateActionException(
                    "Cannot borrow a not borrowed book");
        }
        isBorrowed = false;
        this.borrower = null;
        this.borrowDate = null;
        this.dueDate = null;
    }

    public void renewBook() throws DukeOfBooksException {
        if (!isBorrowed) {
            throw new DukeOfBooksException("Cannot renew a not borrowed book.");
        }
        dueDate = dueDate.plusDays(DEFAULT_RENEW_DAYS);
    }

    public String checkStatus() {
        if (!isBorrowed) {
            return String.format(AVAILABLE_STATUS_FORMAT);
        } else if (isOverdue()){
            return String.format(OVERDUE_STATUS_FORMAT, borrower.toString(),
                    dueDate.toString());
        } else {
            return String.format(BORROWED_STATUS_FORMAT, borrower.toString(),
                    dueDate.toString());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof BorrowableItem)) {
            return false;
        } else {
            boolean isSameStatus =
                    isBorrowed == ((BorrowableItem) obj).isBorrowed;
            boolean isSameBorrower =
                    borrower.equals(((BorrowableItem) obj).borrower);
            boolean isSameDueDate =
                    dueDate.equals(((BorrowableItem) obj).dueDate);
            boolean isSameBorrowDate =
                    borrowDate.equals(((BorrowableItem) obj).borrowDate);
            return isSameStatus && isSameBorrower && isSameBorrowDate
                    && isSameDueDate;
        }
    }

    @Override
    public String toString() {
        return checkStatus();
    }
}
