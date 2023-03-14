package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.data.loan.Loan;
import seedu.dukeofbooks.data.person.Person;

import java.util.ArrayList;

public class BorrowHistory {

    /**
     * Prints previously borrowed books
     */
    public static void checkHistory(Person person) {
        ArrayList<Loan> previousLoans = person.getBorrowedItems();

        if(previousLoans.size()==0) {
            System.out.println("You have not borrowed any book yet!");
        } else {
            System.out.println("These are the books you have borrowed thus far:");
            int count = 1;
            for(Loan loan:previousLoans) {
                BorrowableItem borrowedItem = loan.getBorrowedItem();
                System.out.println(count + ". " + borrowedItem.toString());
                count++;
            }
        }

    }
}
