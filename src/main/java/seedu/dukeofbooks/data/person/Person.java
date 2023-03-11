package seedu.dukeofbooks.data.person;

import seedu.dukeofbooks.data.book.Book;

import java.util.ArrayList;
import java.util.Objects;

import seedu.dukeofbooks.data.exception.IllegalValueException;

public class Person {
    public static final int DEFAULT_PHONE_NUMBER = 10000000;
    private PersonName name;
    private Phone phone;

    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    /**
     * Assumption: Every field must be present and not null.
     */
    public Person(PersonName name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }
    public Person(String name) throws IllegalValueException {
        this.name = new PersonName(name);
        this.phone = new Phone(DEFAULT_PHONE_NUMBER);
    }

    /**
     * Copy constructor.
     */
    public Person(Person source) {
        this(source.getName(), source.getPhone());
    }

    public PersonName getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public ArrayList<Book> getBorrowHistory() {
        return borrowedBooks;
    }

    /**
     * Returns true if both persons have the same identity fields (name and
     * telephone).
     */
    public boolean isSamePerson(Person other) {
        return (other == this)
                || (other != null
                && other.getName().equals(this.getName())
                && other.getPhone().equals(this.getPhone()));
    }

    /**
     * Returns true if all data in this object is the same as that in another
     * (Note: interfaces cannot override .equals)
     */
    public boolean hasSameData(Person other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPhone().equals(this.getPhone()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    public String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ");
        builder.append(getPhone());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Person // instanceof handles nulls
                && this.hasSameData((Person) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return getAsTextShowAll();
    }

}
