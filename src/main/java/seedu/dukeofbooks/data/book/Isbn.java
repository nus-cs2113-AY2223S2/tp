package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.common.IVerifiable;

public class Isbn implements IVerifiable {
    private String isbn;
    public Isbn(String isbn) {
        setISBN(isbn);;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            return other instanceof Isbn && this.hasSameData((Isbn) other);
        }
    }
    public boolean equals(String other) {
        return other.equals(isbn);
    }

    private boolean hasSameData(Isbn other) {
        return isbn == other.getISBN();
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    public String getStringValue() {
        return isbn;
    }

    @Override
    public String toString() {
        return String.format("Isbn: %S", isbn);
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }
    
}
