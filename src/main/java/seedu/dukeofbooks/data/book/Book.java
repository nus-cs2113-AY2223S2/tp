package seedu.dukeofbooks.data.book;
import seedu.dukeofbooks.data.person.Person;

public class Book {
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

}
