package seedu.dukeofbooks.data.book;
import seedu.dukeofbooks.common.IVerifiable;
import seedu.dukeofbooks.data.person.Person;

public class Book implements IVerifiable {
    private Isbn isbn;
    private Title title;
    private Topic topic;
    private Person author;

    
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
        return other == this // short circuit if same object
                || (other instanceof Book // instanceof handles nulls
                && this.hasSameData((Book) other));
    }
    public boolean hasSameData(Book book) {
        boolean sameAuthor = author.equals(book.getAuthor());
        boolean sameTitle = title.equals(book.getTitle());
        boolean sameTopic = topic.equals(book.getTopic());
        boolean sameisbn = isbn.equals(book.getIsbn());
        return sameAuthor && sameTitle && sameTopic && sameisbn;
    }
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
    @Override
    public String toString() {
        return String.format("Book\nISBN: %s, Title: %s, Author: %s, Topic: %s", isbn,title,author,topic);
    }
}
