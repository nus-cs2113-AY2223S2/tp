package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.data.person.Person;

import java.util.ArrayList;

public class BookHistory {

    /**
     * Prints previously borrowed books
     */
    public void CheckHistory(Person person) {
        ArrayList<Book> books = person.getBorrowHistory();

        if(books.size()==0) {
            System.out.println("You have not borrowed any book yet!");
        } else {
            System.out.println("These are the books you have borrowed thus far:");
            for(Book book:books) {
                System.out.println(book.toString());
            }
        }

    }
}
