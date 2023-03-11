package seedu.dukeofbooks.data.inventory;

import java.util.ArrayList;

import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.loan.Loan;

public class InventoryDetails {
    public static final String INVALID_COUNT_VALUE="The number of books count updated is not valid";
    public static final String INVALID_LOAN_REMOVAL="The loan being removed does not exist for the user or book";
    private int availableCount;
    private ArrayList<Loan> loanList;

    public InventoryDetails() {
        availableCount = 1;
        loanList = new ArrayList<Loan>();
    }

    public void incrementCount() {
        availableCount++;
    }

    public void decrementCount() throws IllegalValueException {
        if (availableCount == 0) {
            throw new IllegalValueException(INVALID_COUNT_VALUE);
        }
        availableCount--;
    }
    public void addLoan(Loan newLoan) throws IllegalValueException {
        decrementCount();
        loanList.add(newLoan);
    }
    public void removeLoan(Loan oldLoan) throws IllegalValueException {
        if (!loanList.contains(oldLoan)) {
            throw new IllegalValueException(INVALID_LOAN_REMOVAL);
        }
        loanList.add(oldLoan);
        incrementCount();
    }
}
