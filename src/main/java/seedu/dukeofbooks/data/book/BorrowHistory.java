package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.data.loan.Loan;
import seedu.dukeofbooks.data.person.Person;

import java.util.ArrayList;

public class BorrowHistory {

    /**
     * Prints previously borrowed books
     */
    public static String checkHistory(Person person) {
        ArrayList<Loan> previousLoans = person.getBorrowedItems();

        if(previousLoans.size()==0) {
            return "You have not borrowed any book yet!";
        } else {
            String output = "These are the books you have borrowed thus far:\n";
            int count = 1;
            for(Loan loan:previousLoans) {
                BorrowableItem borrowedItem = loan.getBorrowedItem();
                output = output + count + ". " + borrowedItem.toString();
                if(count!=previousLoans.size()) {
                    output += '\n';
                }
                count++;
            }
            return output;
        }
    }
}

