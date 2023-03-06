package seedu.dukeofbooks.data.loan;
import java.time.LocalDateTime;

public class Loan {
    private Person borrower;
    private Isbn isbn;
    private LocalDateTime loanStart;
    private LocalDateTime loanEnd;
    private boolean isReturned;

    public String LOAN_DATE_IS_AFTER_LOAN_START="Error: loan date after loan start date";

    public Person getBorrower() {
        return borrower;
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
    public void setLoanEnd(LocalDateTime loanEnd) {
        if (loanEnd.compareto(loanStart)<0) {
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
}
