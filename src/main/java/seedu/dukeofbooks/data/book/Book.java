package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.common.IVerifiable;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.person.Person;

public class Book extends BorrowableItem implements IVerifiable {
    private Isbn isbn;
    private Title title;
    private Topic topic;
    private Person author;

    public Book(Isbn isbn, Title title, Topic topic, Person author) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.topic = topic;
        this.author = author;
    }

    public Book(String isbn, String title, String topic, String author) throws IllegalValueException {
        setIsbn(new Isbn(isbn));
        setTitle(new Title(title));
        setTopic(new Topic(topic));
        setAuthor(new Person(author));
    }
    
    public Isbn getIsbn() {
        return isbn;
    }

    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            return other instanceof Book && this.hasSameData((Book) other);
        }
    }

    public boolean hasSameData(Book book) {
        boolean sameAuthor = author.equals(book.getAuthor());
        boolean sameTitle = title.equals(book.getTitle());
        boolean sameTopic = topic.equals(book.getTopic());
        boolean sameIsbn = isbn.equals(book.getIsbn());
        return sameAuthor && sameTitle && sameTopic && sameIsbn;
    }

    public static String createISBN() {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(13);

        for (int i = 0; i < 13; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(alphaNumericString.length()
                * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Book\nISBN: %s, Title: %s, Author: %s, Topic: %s", isbn, title, author, topic);
    }
}
