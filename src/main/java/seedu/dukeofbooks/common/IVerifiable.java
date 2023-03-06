package seedu.dukeofbooks.common;

public interface IVerifiable {
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract String toString();
}
