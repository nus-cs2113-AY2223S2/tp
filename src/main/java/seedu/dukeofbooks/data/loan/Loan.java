package seedu.dukeofbooks.data.loan;
import java.time.LocalDateTime;

import seedu.dukeofbooks.common.IVerifiable;
import seedu.dukeofbooks.data.book.Isbn;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.exception.IllegalDateException;

public class Loan implements IVerifiable {
    public static final String LOAN_DATE_IS_AFTER_LOAN_START="Error: loan date after loan start date";
    private Person borrower;
    private Isbn isbn;
    private LocalDateTime loanStart;
    private LocalDateTime loanEnd;
    private boolean isReturned;

    public Person getBorrower() {
        return borrower;
    }
    public Isbn getISBN() {
        return isbn;
    }
    public void setISBN(Isbn newISBN) {
        isbn = newISBN;
    }
    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }
    public LocalDateTime getLoanStart() {
        return loanStart;
    }
    public void setLoanStart(LocalDateTime loanStart) {
        this.loanStart = loanStart;
    }
    public LocalDateTime getLoanEnd() {
        return loanEnd;
    }
    public void setLoanEnd(LocalDateTime loanEnd) throws IllegalDateException {
        if (loanEnd.compareTo(loanStart)<0) {
            // Impossible to return before start date
            // Throw exception
            throw new IllegalDateException(LOAN_DATE_IS_AFTER_LOAN_START);
        }
        this.loanEnd = loanEnd;
    }
    public boolean isReturned() {
        return isReturned;
    }
    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } 
        else {
            return other instanceof Loan && this.hasSameData((Loan) other);
        }
    }
    private boolean hasSameData(Loan other) {
        boolean sameBorrower = other.getBorrower() == borrower;
        boolean sameIsbn = other.getISBN().equals(isbn);
        boolean sameStart = other.getLoanStart().equals(loanStart);
        boolean sameEnd = other.getLoanEnd().equals(loanEnd);
        boolean sameReturn = other.isReturned() == isReturned;
        return sameBorrower && sameIsbn && sameStart && sameEnd && sameReturn;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public String toString() {
        return String.format("Loan\nBorrower: %s, ISBN: %s, Start date: %s, End date: %s",
                borrower,isbn,loanStart,loanEnd);
    }
}
