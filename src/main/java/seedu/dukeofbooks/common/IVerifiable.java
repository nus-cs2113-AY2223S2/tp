package seedu.dukeofbooks.common;

public interface IVerifiable {
    boolean equals(Object other);
    int hashCode();
    String toString();
}
