package seedu.dukeofbooks.data.book;

public abstract class BorrowableItem {
    protected boolean isBorrowed;

    public BorrowableItem() {
        isBorrowed = false;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowItem() {
        assert !isBorrowed;
        isBorrowed = true;
    }

    public void returnItem() {
        assert isBorrowed;
        isBorrowed = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof BorrowableItem)) {
            return false;
        } else {
            return isBorrowed == ((BorrowableItem) obj).isBorrowed;
        }
    }

    @Override
    public String toString() {
        return "Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}
