package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.data.loan.Loan;
import seedu.dukeofbooks.data.person.Person;

import java.util.ArrayList;

public class BorrowHistory {

    // private static Logger logger = Logger.getLogger("checkHistoryLogger");

    /**
     * Prints previously borrowed books
     */
    public static String checkHistory(Person person) {
        ArrayList<Loan> previousLoans = person.getBorrowedItems();
        String output;

        // logger.log(Level.INFO, "Processing checkHistory class...");
        if (previousLoans.size() == 0) {
            output = "You have not borrowed any book yet!";
        } else {
            output = "These are the books you have borrowed thus far:\n";
            int count = 1;
            for (Loan loan : previousLoans) {
                BorrowableItem borrowedItem = loan.getBorrowedItem();
                if (borrowedItem == null) {
                    // logger.log(Level.WARNING, "Error with borrowed item.");
                }
                output = output + count + ". " + borrowedItem.toString();
                output = output + "\nStatus: " +
                        (borrowedItem.isBorrowed ? "borrowed" : "returned");
                output = output + "\nDue: " + loan.getLoanEnd().toLocalDate();
                if (count != previousLoans.size()) {
                    output += '\n';
                }
                count++;
            }
        }
        // logger.log(Level.INFO, "Finished processing checkHistory class!");
        return output;
    }
}

