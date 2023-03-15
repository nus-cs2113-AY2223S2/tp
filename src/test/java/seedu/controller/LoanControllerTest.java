package seedu.controller;

import seedu.dukeofbooks.data.loan.LoanRecords;

public class LoanControllerTest {
    private static final LoanRecords loanRecords = new LoanRecords();

    // todo update test for static LoanController
//    private static Person createPerson(String name) {
//        try {
//            PersonName personName = new PersonName(name);
//            Phone phone = new Phone(12345678);
//            return new Person(personName, phone);
//        } catch (IllegalValueException ive) {
//            return null;
//        }
//    }
//
//    private static Book createBook(String bookName) {
//        try {
//            Title title = new Title(bookName);
//            Isbn isbn = new Isbn("1234567");
//            Topic topic = new Topic("Coffee");
//            Person author = new Person("authorA");
//            return new Book(isbn, title, topic, author);
//        } catch (IllegalValueException ive) {
//            return null;
//        }
//    }
//
//    @Test
//    public void testBorrowBook() {
//        Book book = createBook("Java Programming");
//        assertNotNull(book);
//        Person borrower = createPerson("BorrowerA");
//        assertNotNull(borrower);
//        assertFalse(book.isBorrowed());
//        assertDoesNotThrow(() -> loanController.borrowBook(borrower, book,
//                LocalDateTime.now()));
//        assertTrue(book.isBorrowed());
//    }
}
